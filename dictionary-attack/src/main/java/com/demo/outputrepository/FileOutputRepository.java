package com.demo.outputrepository;

import com.demo.entity.OutputEntity;
import com.demo.util.CliOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Saves an entity into local file.
 */
@Component
public class FileOutputRepository implements OutputRepository {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileOutputRepository.class);
    private String fileName;
    private FileOutputStream fos;

    @Autowired
    public FileOutputRepository(@Value("${file-output-repository.filename}") String fileName)
            throws FileNotFoundException {
        this.fileName = fileName;
    }

    @Override
    public void write(OutputEntity outputEntity) {
        if (fos == null) {
            try {
                CliOptions cliOptions = CliOptions.getInstance();
                fos = new FileOutputStream(new File(cliOptions.getOutputFileName()), true /* append */);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(String.format("File [{}] is not writable", fileName), e);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(outputEntity.getLoginId())
                .append(" ")
                .append(outputEntity.getEncryptedPassword())
                .append(" ")
                .append(outputEntity.getPlaintextPassword())
                .append(System.lineSeparator());
        try {
            fos.write(sb.toString().getBytes());
        } catch (IOException e) {
            LOGGER.error("Error", e);
        }
    }
}

package com.demo.outputrepository;

import com.demo.entity.OutputEntity;
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
    private FileOutputStream fos;

    @Autowired
    public FileOutputRepository(@Value("${file-output-repository.filename}") String permanentStore)
            throws FileNotFoundException {
        fos = new FileOutputStream(new File(permanentStore), true /* append */);
    }

    @Override
    public void write(OutputEntity outputEntity) {
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

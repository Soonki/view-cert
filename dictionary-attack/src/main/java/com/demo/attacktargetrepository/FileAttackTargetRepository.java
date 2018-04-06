package com.demo.attacktargetrepository;

import com.demo.entity.AttackTargetEntity;
import com.demo.util.CliOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Returns a list of AttachTargetEntity with pairs of login ID and SHA1-hashed password.
 */
@Component
public class FileAttackTargetRepository implements AttackTargetRepository {
    private String fileName;
    private LineIterator it;

    @Autowired
    public FileAttackTargetRepository(@Value("${file-input-scanner.filename}") String fileName) throws IOException {
        this.fileName = fileName;
    }

    @Override
    public Iterator<AttackTargetEntity> iterator() {
        try {
            CliOptions cliOptions = CliOptions.getInstance();
            File cliFile = new File(cliOptions.getInputFileName());
            if (cliFile.exists()) {
                it = FileUtils.lineIterator(cliFile);
            } else {
                it = FileUtils.lineIterator(getFileFromClasspath(fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Iterator<AttackTargetEntity>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public AttackTargetEntity next() {
                String line = it.next();
                return toInputEntry(line);
            }

            @Override
            public void remove() {
                // no code
            }
        };
    }

    private File getFileFromClasspath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    private AttackTargetEntity toInputEntry(String line) {
        String[] arr = line.split("\\s+");
        return new AttackTargetEntity(arr[0], arr[1]);
    }
}

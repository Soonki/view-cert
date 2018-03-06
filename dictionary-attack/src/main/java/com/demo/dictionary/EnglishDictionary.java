package com.demo.dictionary;

import com.demo.entity.ChallengeEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Provides a list of English words from a local dictionary file.
 */
@Component
public class EnglishDictionary implements Dictionary {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnglishDictionary.class);
    private LineIterator it;

    public EnglishDictionary(LineIterator it) {
        this.it = it;
    }

    @Autowired
    public EnglishDictionary(@Value("${english-dictionary.filename}") String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            it = FileUtils.lineIterator(file);
        } catch (IOException e) {
            LOGGER.error("Error loading dictionary", e);
        }
    }

    @Override
    public Iterator<ChallengeEntity> iterator() {
        return new Iterator<ChallengeEntity>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public ChallengeEntity next() {
                return new ChallengeEntity(it.next().trim());
            }

            @Override
            public void remove() {
                // no use
            }
        };
    }
}

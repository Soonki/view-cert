package com.demo.dictionary;

import com.demo.entity.ChallengeEntity;

import java.util.Iterator;

/**
 * Provides a list of words.
 */
public interface Dictionary {
    Iterator<ChallengeEntity> iterator();
}

package com.demo.outputrepository;

import com.demo.entity.OutputEntity;

/**
 * Provides storage for store discovered passwords.
 */
public interface OutputRepository {
    void write(OutputEntity outputEntity);
}

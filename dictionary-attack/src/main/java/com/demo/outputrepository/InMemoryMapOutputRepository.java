package com.demo.outputrepository;

import com.demo.entity.OutputEntity;

import java.util.HashMap;

/**
 * Saves an entity into in-memory Java map (used in unit tests).
 */
public class InMemoryMapOutputRepository implements OutputRepository {
    private HashMap map = new HashMap();

    @Override
    public void write(OutputEntity outputEntity) {
        map.put(outputEntity.getLoginId(), outputEntity);
    }
}

package com.demo.attacktargetrepository;

import com.demo.entity.AttackTargetEntity;

import java.util.Iterator;

/**
 * Returns a list of AttackTargetEntity.
 */
public interface AttackTargetRepository {
    Iterator<AttackTargetEntity> iterator();
}

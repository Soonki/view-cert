package com.demo.challenger;

import com.demo.entity.AttackTargetEntity;
import com.demo.entity.ChallengeEntity;

/**
 * Verifies a credential against target entity.
 */
public interface Challenger {
    boolean challenge(AttackTargetEntity entity, ChallengeEntity challengeEntity);
}

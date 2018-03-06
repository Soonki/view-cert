package com.demo.challenger;

import com.demo.entity.AttackTargetEntity;
import com.demo.entity.ChallengeEntity;

/**
 * Verifies the credential on ChallengeEntity against Facebook online (TBD).
 */
public class FacebookOnlineChallenger implements Challenger {
    @Override
    public boolean challenge(AttackTargetEntity entity, ChallengeEntity challengeEntity) {
        // TBD 3/4/2018
        return false;
    }
}

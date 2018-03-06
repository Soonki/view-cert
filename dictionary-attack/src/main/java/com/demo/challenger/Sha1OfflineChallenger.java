package com.demo.challenger;

import com.demo.entity.AttackTargetEntity;
import com.demo.entity.ChallengeEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * Verifies the credential against the SHA-1 encrypted password on local file.
 */
@Component
public class Sha1OfflineChallenger implements Challenger {
    @Override
    public boolean challenge(AttackTargetEntity attackTargetEntity, ChallengeEntity challengeEntity) {
        String target = attackTargetEntity.getEncryptedPassword();
        String trial = DigestUtils.sha1Hex(challengeEntity.getPlaintextPassword());
        return target.equals(trial);
    }
}

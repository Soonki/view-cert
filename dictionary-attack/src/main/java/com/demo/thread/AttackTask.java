package com.demo.thread;

import com.demo.challenger.Challenger;
import com.demo.dictionary.Dictionary;
import com.demo.entity.AttackTargetEntity;
import com.demo.entity.ChallengeEntity;
import com.demo.entity.OutputEntity;
import com.demo.outputrepository.OutputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Iterator;

/**
 * Attacks a user.
 */
public class AttackTask implements Runnable {
    public static final Logger LOGGER = LoggerFactory.getLogger(AttackTask.class);
    private final AttackTargetEntity attackTargetEntity;
    private final Dictionary dictionary;
    private final Challenger challenger;
    private final OutputRepository outputRepository;

    public AttackTask(AttackTargetEntity entry,
                      Challenger challenger,
                      Dictionary dictionary,
                      OutputRepository outputRepository) {
        this.attackTargetEntity = entry;
        this.dictionary = dictionary;
        this.challenger = challenger;
        this.outputRepository = outputRepository;
        LOGGER.info("Using dictionary: " + dictionary);
    }

    @Override
    public void run() {
        Iterator<ChallengeEntity> it = dictionary.iterator();
        int total = 0;
        boolean discovered = false;
        StopWatch stopWatch = new StopWatch(this.toString());
        stopWatch.start();
        while (it.hasNext()) {
            ChallengeEntity challengeEntity = it.next();
            total++;
            if (challenger.challenge(attackTargetEntity, challengeEntity)) {
                LOGGER.info("Found password for [{}] => [{}]", attackTargetEntity.getLoginId(), challengeEntity.getPlaintextPassword());
                outputRepository.write(buildOutputEntity(attackTargetEntity, challengeEntity));
                discovered = true;
                break;
            }
        }
        stopWatch.stop();
        LOGGER.info("{}, # of challenges = {}, discovered = {}", stopWatch.shortSummary(), total, discovered);
    }

    private OutputEntity buildOutputEntity(AttackTargetEntity attackTargetEntity, ChallengeEntity challengeEntity) {
        OutputEntity outputEntity = new OutputEntity();
        outputEntity.setLoginId(attackTargetEntity.getLoginId());
        outputEntity.setEncryptedPassword(attackTargetEntity.getEncryptedPassword());
        outputEntity.setPlaintextPassword(challengeEntity.getPlaintextPassword());
        return outputEntity;
    }

    @Override
    public String toString() {
        return String.format("AttackTask[login=%s]", attackTargetEntity.getLoginId());
    }
}

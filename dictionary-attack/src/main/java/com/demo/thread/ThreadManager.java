package com.demo.thread;

import com.demo.attacktargetrepository.AttackTargetRepository;
import com.demo.challenger.Challenger;
import com.demo.dictionary.Dictionary;
import com.demo.dictionary.BruteforcePermutationDictionary;
import com.demo.dictionary.EnglishDictionary;
import com.demo.entity.AttackTargetEntity;
import com.demo.outputrepository.OutputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Attacks all users in AttackTargetRepository in parallel.
 */
@Component
public class ThreadManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadManager.class);
    private final AttackTargetRepository attackTargetRepository;
    private final OutputRepository outputRepository;
    private final Challenger challenger;

    @Value("${bruteforce-permutation-dictionary.min-len}")
    Integer minLen;

    @Value("${bruteforce-permutation-dictionary.max-len}")
    Integer maxLen;

    @Value("${bruteforce-permutation-dictionary.allowed-characters}")
    String allowedCharacters;

    @Value("${english-dictionary.filename}")
    String englishDictionaryFileName;

    @Autowired
    public ThreadManager(AttackTargetRepository attackTargetRepository,
                         OutputRepository outputRepository,
                         Challenger challenger) {
        this.attackTargetRepository = attackTargetRepository;
        this.outputRepository = outputRepository;
        this.challenger = challenger;
    }

    public void start(ThreadPoolTaskExecutor taskExecutor) {
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        Iterator<AttackTargetEntity> it = attackTargetRepository.iterator();
        while (it.hasNext()) {
            AttackTargetEntity entry = it.next();
            // Dictionary dictionary = new BruteforcePermutationDictionary(minLen, maxLen, allowedCharacters);
            Dictionary dictionary = new EnglishDictionary(englishDictionaryFileName);
            taskExecutor.execute(new AttackTask(entry, challenger, dictionary, outputRepository));
        }
        safeShutdown(taskExecutor);
    }

    private void safeShutdown(ThreadPoolTaskExecutor taskExecutor) {
        while (taskExecutor.getActiveCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.warn("Interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        taskExecutor.shutdown();
    }
}

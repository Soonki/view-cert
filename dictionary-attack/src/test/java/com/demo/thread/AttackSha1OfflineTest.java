package com.demo.thread;

import com.demo.challenger.Challenger;
import com.demo.dictionary.Dictionary;
import com.demo.entity.AttackTargetEntity;
import com.demo.challenger.Sha1OfflineChallenger;
import com.demo.dictionary.BruteforcePermutationDictionary;
import com.demo.dictionary.EnglishDictionary;
import com.demo.outputrepository.InMemoryMapOutputRepository;
import com.demo.outputrepository.OutputRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AttackSha1OfflineTest {
    Challenger challenger = new Sha1OfflineChallenger();
    OutputRepository outputRepository = new InMemoryMapOutputRepository();

    // positive cases

    @Test
    public void plaintextPasswordHashedUsingSha1IsDiscoveredByEnglishDictionaryAttack() {
        // arrange
        Dictionary dictionary = new EnglishDictionary("sample-english-dictionary.txt");
        AttackTargetEntity attackTargetEntry = new AttackTargetEntity("user1", "e0c9035898dd52fc65c41454cec9c4d2611bfb37"); // password: 'aa'

        // act
        AttackTask task = new AttackTask(attackTargetEntry, challenger, dictionary, outputRepository);
        task.run();

        // assert
        assertEquals(1, ((Map) ReflectionTestUtils.getField(outputRepository, "map")).size());
    }

    @Test
    public void plaintextPasswordHashedUsingSha1IsDiscoveredByBruteforceDictionaryAttack() {
        // arrange
        Dictionary dictionary = new BruteforcePermutationDictionary(2, 4, "abc");
        AttackTargetEntity attackTargetEntry = new AttackTargetEntity("user1", "e0c9035898dd52fc65c41454cec9c4d2611bfb37"); // password: 'aa'

        // act
        AttackTask task = new AttackTask(attackTargetEntry, challenger, dictionary, outputRepository);
        task.run();

        // assert
        assertEquals(1, ((Map) ReflectionTestUtils.getField(outputRepository, "map")).size());
    }

    // negative cases
    @Test
    public void passwordContainsSpecialCharacterButDictionaryDidntIncludedThemInCharset_EXPECT_failure() {
        // arrange
        Dictionary dictionary = new BruteforcePermutationDictionary(2, 4, "abc");
        AttackTargetEntity attackTargetEntry = new AttackTargetEntity("user1", "e0c9035898dd52xfc65c41454cec9c4d2611bfb37");

        // act
        AttackTask task = new AttackTask(attackTargetEntry, challenger, dictionary, outputRepository);
        task.run();

        // assert
        assertEquals(0, ((Map) ReflectionTestUtils.getField(outputRepository, "map")).size());
    }
}
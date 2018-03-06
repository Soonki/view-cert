package com.demo.thread;

import com.demo.challenger.FacebookOnlineChallenger;
import com.demo.dictionary.Dictionary;
import com.demo.challenger.Challenger;
import com.demo.dictionary.EnglishDictionary;
import com.demo.entity.AttackTargetEntity;
import com.demo.entity.ChallengeEntity;
import com.demo.entity.OutputEntity;
import com.demo.outputrepository.InMemoryMapOutputRepository;
import com.demo.outputrepository.OutputRepository;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AttackFacebookTest {
    Challenger challenger = Mockito.mock(FacebookOnlineChallenger.class);
    OutputRepository outputRepository = new InMemoryMapOutputRepository();

    // positive cases

    @Test
    public void plaintextPasswordHashedUsingSha1IsDiscoveredByEnglishDictionaryAttack() {
        // arrange
        AttackTargetEntity user1 = new AttackTargetEntity("user1"); // uses simple password
        AttackTargetEntity user2 = new AttackTargetEntity("user2"); // uses more complicated password
        when(challenger.challenge(any(AttackTargetEntity.class), any(ChallengeEntity.class)))
                .thenAnswer(buildComplexAnswer());

        // act
        (new AttackTask(user1, challenger, buildNewDictionary(), outputRepository)).run();
        (new AttackTask(user2, challenger, buildNewDictionary(), outputRepository)).run();

        // assert
        Map<String, OutputEntity> map = (Map) ReflectionTestUtils.getField(outputRepository, "map");
        assertEquals(1, map.size());
        OutputEntity aResult = map.get("user1");
        assertEquals("secret1", aResult.getPlaintextPassword());
    }

    // Mock facebook response: user1/secret1 is accepted. All other combinations are rejected.
    private Answer<Boolean> buildComplexAnswer() {
        return new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                AttackTargetEntity targetEntity = (AttackTargetEntity) args[0];
                ChallengeEntity challengeEntity = (ChallengeEntity) args[1];
                return "user1".equals(targetEntity.getLoginId()) && "secret1".equals(challengeEntity.getPlaintextPassword());
            }
        };
    }

    // Mock passwords to try with.
    private Dictionary buildNewDictionary() {
        return new EnglishDictionary(new LineIterator(new InputStreamReader(new ByteArrayInputStream("secret1\nsecret2\n".getBytes()))));
    }
}

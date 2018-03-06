package com.demo.dictionary;

import com.demo.entity.ChallengeEntity;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BruteforcePermutationDictionaryTest {

    @Test
    public void iteratorWithNoChars() throws Exception {
        BruteforcePermutationDictionary singleLetterPasswords = new BruteforcePermutationDictionary(1, 1, "");
        Iterator<ChallengeEntity> it = singleLetterPasswords.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void generateOneSingleLetterPassword() throws Exception {
        BruteforcePermutationDictionary singleLetterPasswords = new BruteforcePermutationDictionary(1, 1, "a");
        Iterator<ChallengeEntity> it = singleLetterPasswords.iterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next().getPlaintextPassword());
        assertFalse(it.hasNext());
    }

    @Test
    public void generateThreeSingleLetterPasswords() throws Exception {
        BruteforcePermutationDictionary singleLetterPasswords = new BruteforcePermutationDictionary(1, 1, "abc");
        Iterator<ChallengeEntity> it = singleLetterPasswords.iterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("b", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("c", it.next().getPlaintextPassword());
        assertFalse(it.hasNext());
    }

    @Test
    public void generateNineTwoLetterPasswords() throws Exception {
        BruteforcePermutationDictionary singleLetterPasswords = new BruteforcePermutationDictionary(2, 2, "abc");
        Iterator<ChallengeEntity> it = singleLetterPasswords.iterator();
        assertTrue(it.hasNext());
        assertEquals("aa", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ab", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ac", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ba", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("bb", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("bc", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ca", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("cb", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("cc", it.next().getPlaintextPassword());
        assertFalse(it.hasNext());
    }

    @Test
    public void generateTwelveMaxTwoLetterPasswords() throws Exception {
        BruteforcePermutationDictionary singleLetterPasswords = new BruteforcePermutationDictionary(1, 2, "abc");
        Iterator<ChallengeEntity> it = singleLetterPasswords.iterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("b", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("c", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("aa", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ab", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ac", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ba", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("bb", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("bc", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("ca", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("cb", it.next().getPlaintextPassword());
        assertTrue(it.hasNext());
        assertEquals("cc", it.next().getPlaintextPassword());
        assertFalse(it.hasNext());
    }
}
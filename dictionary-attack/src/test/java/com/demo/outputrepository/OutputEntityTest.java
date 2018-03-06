package com.demo.outputrepository;

import com.demo.entity.OutputEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OutputEntityTest {

    @Test
    public void testConstructor() throws Exception {
        OutputEntity one = new OutputEntity("a", "b", "c");
        assertEquals("a", one.getLoginId());
        assertEquals("b", one.getEncryptedPassword());
        assertEquals("c", one.getPlaintextPassword());
    }

    @Test
    public void testGetterSetter() throws Exception {
        OutputEntity one = new OutputEntity();
        one.setLoginId("a");
        one.setEncryptedPassword("b");
        one.setPlaintextPassword("c");
        assertEquals("a", one.getLoginId());
        assertEquals("b", one.getEncryptedPassword());
        assertEquals("c", one.getPlaintextPassword());
    }
}
package com.demo.attacktargetrepository;

import com.demo.entity.AttackTargetEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TargetEntityTest {
    @Test
    public void testConstructor() throws Exception {
        AttackTargetEntity one = new AttackTargetEntity("a", "b");
        assertEquals("a", one.getLoginId());
        assertEquals("b", one.getEncryptedPassword());
    }

    @Test
    public void testGetterSetter() throws Exception {
        AttackTargetEntity one = new AttackTargetEntity();
        one.setLoginId("a");
        one.setEncryptedPassword("b");
        assertEquals("a", one.getLoginId());
        assertEquals("b", one.getEncryptedPassword());
    }
}
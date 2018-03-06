package com.demo.attacktargetrepository;

import com.demo.entity.AttackTargetEntity;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class FileAttackTargetRepositoryTest {
    @Test
    public void iteratorWithNoEntry() throws Exception {
        AttackTargetRepository ies = new FileAttackTargetRepository("testdata/FileInputEntryScannerTest/mock-input-with-no-record.txt");
        Iterator<AttackTargetEntity> it = ies.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorWith1Entry() throws Exception {
        AttackTargetRepository ies = new FileAttackTargetRepository("testdata/FileInputEntryScannerTest/mock-input-with-1-record.txt");
        Iterator<AttackTargetEntity> it = ies.iterator();
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorWith3Entries() throws Exception {
        AttackTargetRepository ies = new FileAttackTargetRepository("testdata/FileInputEntryScannerTest/mock-input-with-3-records.txt");
        Iterator<AttackTargetEntity> it = ies.iterator();
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
    }
}
package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.EntryDao;
import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EntryServiceImplTest {
    @MockBean
    private EntryDao entryDao;

    @Autowired
    private EntryServiceImpl entryServiceImpl;


    @Test
    void testAddEntry() {
        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        when(entryDao.save((Entry) any())).thenReturn(entry);

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setMonth(1);
        entry1.setYear(1);
        entryServiceImpl.addEntry(entry1);
        verify(entryDao).save((Entry) any());
        assertEquals(1, entry1.getId().intValue());
        assertEquals(1, entry1.getYear().intValue());
        assertEquals(1, entry1.getMonth().intValue());
        assertTrue(entryServiceImpl.getAllEntries().isEmpty());
    }



    @Test
    void testAddEntry2() {
        when(entryDao.save((Entry) any())).thenThrow(new NotFoundException("An error occurred"));

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        assertThrows(NotFoundException.class, () -> entryServiceImpl.addEntry(entry));
        verify(entryDao).save((Entry) any());
    }



    @Test
    void testGetEntry() throws NotFoundException {
        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        Optional<Entry> ofResult = Optional.of(entry);
        when(entryDao.findById((Integer) any())).thenReturn(ofResult);
        assertSame(entry, entryServiceImpl.getEntry(1));
        verify(entryDao).findById((Integer) any());
    }



    @Test
    void testGetEntry2() throws NotFoundException {
        when(entryDao.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> entryServiceImpl.getEntry(1));
        verify(entryDao).findById((Integer) any());
    }



    @Test
    void testGetEntry3() throws NotFoundException {
        when(entryDao.findById((Integer) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> entryServiceImpl.getEntry(1));
        verify(entryDao).findById((Integer) any());
    }



    @Test
    void testGetAllEntries() {
        ArrayList<Entry> entryList = new ArrayList<>();
        when(entryDao.findAllByOrderByYearAscMonthAsc()).thenReturn(entryList);
        List<Entry> actualAllEntries = entryServiceImpl.getAllEntries();
        assertSame(entryList, actualAllEntries);
        assertTrue(actualAllEntries.isEmpty());
        verify(entryDao).findAllByOrderByYearAscMonthAsc();
    }



    @Test
    void testGetAllEntries2() {
        when(entryDao.findAllByOrderByYearAscMonthAsc()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> entryServiceImpl.getAllEntries());
        verify(entryDao).findAllByOrderByYearAscMonthAsc();
    }



    @Test
    void testDeleteEntry() {
        doNothing().when(entryDao).deleteById((Integer) any());
        entryServiceImpl.deleteEntry(123);
        verify(entryDao).deleteById((Integer) any());
        assertTrue(entryServiceImpl.getAllEntries().isEmpty());
    }



    @Test
    void testDeleteEntry2() {
        doThrow(new NotFoundException("An error occurred")).when(entryDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> entryServiceImpl.deleteEntry(123));
        verify(entryDao).deleteById((Integer) any());
    }



    @Test
    void testEditEntry() {
        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        when(entryDao.save((Entry) any())).thenReturn(entry);

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setMonth(1);
        entry1.setYear(1);
        entryServiceImpl.editEntry(entry1);
        verify(entryDao).save((Entry) any());
        assertEquals(1, entry1.getId().intValue());
        assertEquals(1, entry1.getYear().intValue());
        assertEquals(1, entry1.getMonth().intValue());
        assertTrue(entryServiceImpl.getAllEntries().isEmpty());
    }



    @Test
    void testEditEntry2() {
        when(entryDao.save((Entry) any())).thenThrow(new NotFoundException("An error occurred"));

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        assertThrows(NotFoundException.class, () -> entryServiceImpl.editEntry(entry));
        verify(entryDao).save((Entry) any());
    }
}


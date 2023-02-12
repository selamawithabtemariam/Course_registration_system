package edu.mum.se.mumsched.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.service.exception.NotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class EntryControllerTest {


    @Test
    void testShowEntryPage() {

        EntryController entryController = new EntryController();
        entryController.showEntryPage(new ConcurrentModel());
    }


    @Test
    void testShowNewEntryForm() {

        EntryController entryController = new EntryController();
        assertEquals("entry-create", entryController.showNewEntryForm(new ConcurrentModel()));
    }


    @Test
    void testAddNewEntry() {

        EntryController entryController = new EntryController();

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        entryController.addNewEntry(entry);
    }


    @Test
    void testAddNewEntry2() {

        EntryController entryController = new EntryController();
        Entry entry = mock(Entry.class);
        doNothing().when(entry).setId((Integer) any());
        doNothing().when(entry).setMonth((Integer) any());
        doNothing().when(entry).setYear((Integer) any());
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        entryController.addNewEntry(entry);
    }


    @Test
    void testDeleteEntry() {

        (new EntryController()).deleteEntry("42");
    }


    @Test
    void testDeleteEntry2() {

        (new EntryController()).deleteEntry("foo");
    }


    @Test
    void testShowUpdateEntry() throws NotFoundException {

        EntryController entryController = new EntryController();
        entryController.showUpdateEntry("42", new ConcurrentModel());
    }


    @Test
    void testShowUpdateEntry2() throws NotFoundException {

        EntryController entryController = new EntryController();
        entryController.showUpdateEntry("foo", new ConcurrentModel());
    }


    @Test
    void testUpdateEntry() {

        EntryController entryController = new EntryController();
        ConcurrentModel model = new ConcurrentModel();

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        entryController.updateEntry(model, entry);
    }


    @Test
    void testUpdateEntry2() {

        EntryController entryController = new EntryController();
        ConcurrentModel model = new ConcurrentModel();
        Entry entry = mock(Entry.class);
        doNothing().when(entry).setId((Integer) any());
        doNothing().when(entry).setMonth((Integer) any());
        doNothing().when(entry).setYear((Integer) any());
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        entryController.updateEntry(model, entry);
    }
}


package edu.mum.se.mumsched.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.service.exception.NotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class BlockControllerTest {

    @Test
    void testShowBlockPage() {

        BlockController blockController = new BlockController();
        blockController.showBlockPage(new ConcurrentModel());
    }



    @Test
    void testShowNewBlockForm() {

        BlockController blockController = new BlockController();
        assertEquals("block-create", blockController.showNewBlockForm(new ConcurrentModel()));
    }


    @Test
    void testAddNewBlock() {

        BlockController blockController = new BlockController();
        blockController.addNewBlock(new Block());
    }


    @Test
    void testAddNewBlock2() {

        (new BlockController()).addNewBlock(mock(Block.class));
    }



    @Test
    void testDeleteEntry() {

        (new BlockController()).deleteEntry("42");
    }


    @Test
    void testDeleteEntry2() {

        (new BlockController()).deleteEntry("foo");
    }



    @Test
    void testShowUpdateEntry() throws NotFoundException {

        BlockController blockController = new BlockController();
        blockController.showUpdateEntry("42", new ConcurrentModel());
    }


    @Test
    void testShowUpdateEntry2() throws NotFoundException {

        BlockController blockController = new BlockController();
        blockController.showUpdateEntry("foo", new ConcurrentModel());
    }


    @Test
    void testUpdateEntry() {

        BlockController blockController = new BlockController();
        ConcurrentModel model = new ConcurrentModel();
        blockController.updateEntry(model, new Block());
    }


    @Test
    void testUpdateEntry2() {

        BlockController blockController = new BlockController();
        blockController.updateEntry(new ConcurrentModel(), mock(Block.class));
    }
}


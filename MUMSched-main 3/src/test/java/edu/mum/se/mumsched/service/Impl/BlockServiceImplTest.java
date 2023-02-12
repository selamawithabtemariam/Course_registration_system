package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.BlockDao;
import edu.mum.se.mumsched.domain.Block;
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

@ContextConfiguration(classes = {BlockServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BlockServiceImplTest {
    @MockBean
    private BlockDao blockDao;

    @Autowired
    private BlockServiceImpl blockServiceImpl;


    @Test
    void testAddBlock() {
        when(blockDao.save((Block) any())).thenReturn(new Block());
        blockServiceImpl.addBlock(new Block());
        verify(blockDao).save((Block) any());
        assertTrue(blockServiceImpl.getAllBlocks().isEmpty());
    }


    @Test
    void testAddBlock2() {
        when(blockDao.save((Block) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> blockServiceImpl.addBlock(new Block()));
        verify(blockDao).save((Block) any());
    }



    @Test
    void testGetBlock() throws NotFoundException {
        Block block = new Block();
        when(blockDao.findById((Integer) any())).thenReturn(Optional.of(block));
        assertSame(block, blockServiceImpl.getBlock(1));
        verify(blockDao).findById((Integer) any());
    }



    @Test
    void testGetBlock2() throws NotFoundException {
        when(blockDao.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> blockServiceImpl.getBlock(1));
        verify(blockDao).findById((Integer) any());
    }



    @Test
    void testGetBlock3() throws NotFoundException {
        when(blockDao.findById((Integer) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> blockServiceImpl.getBlock(1));
        verify(blockDao).findById((Integer) any());
    }



    @Test
    void testGetAllBlocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        when(blockDao.findAllByOrderByYearAscMonthAsc()).thenReturn(blockList);
        List<Block> actualAllBlocks = blockServiceImpl.getAllBlocks();
        assertSame(blockList, actualAllBlocks);
        assertTrue(actualAllBlocks.isEmpty());
        verify(blockDao).findAllByOrderByYearAscMonthAsc();
    }



    @Test
    void testGetAllBlocks2() {
        when(blockDao.findAllByOrderByYearAscMonthAsc()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> blockServiceImpl.getAllBlocks());
        verify(blockDao).findAllByOrderByYearAscMonthAsc();
    }



    @Test
    void testDeleteBlock() {
        doNothing().when(blockDao).deleteById((Integer) any());
        blockServiceImpl.deleteBlock(123);
        verify(blockDao).deleteById((Integer) any());
        assertTrue(blockServiceImpl.getAllBlocks().isEmpty());
    }



    @Test
    void testDeleteBlock2() {
        doThrow(new NotFoundException("An error occurred")).when(blockDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> blockServiceImpl.deleteBlock(123));
        verify(blockDao).deleteById((Integer) any());
    }



    @Test
    void testEditBlock() {
        when(blockDao.save((Block) any())).thenReturn(new Block());
        blockServiceImpl.editBlock(new Block());
        verify(blockDao).save((Block) any());
        assertTrue(blockServiceImpl.getAllBlocks().isEmpty());
    }



    @Test
    void testEditBlock2() {
        when(blockDao.save((Block) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> blockServiceImpl.editBlock(new Block()));
        verify(blockDao).save((Block) any());
    }
}


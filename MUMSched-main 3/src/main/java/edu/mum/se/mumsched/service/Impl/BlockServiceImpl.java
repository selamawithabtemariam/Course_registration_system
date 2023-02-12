package edu.mum.se.mumsched.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.mum.se.mumsched.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.mumsched.dao.BlockDao;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Service
public class BlockServiceImpl implements BlockService {
	@Autowired
	private BlockDao blockDao;

	@Override
	public void addBlock(Block block) {
		blockDao.save(block);
	}

	@Override
	public Block getBlock(Integer id) throws NotFoundException {
		Optional<Block> block = blockDao.findById(id);

		if (block.isEmpty())
			throw new NotFoundException("No block with id " + id + " found.");

		return block.get();
	}

	@Override
	public List<Block> getAllBlocks() {
		List<Block> list = blockDao.findAllByOrderByYearAscMonthAsc();
		return list;
	}

	@Override
	public void deleteBlock(Integer blockId) {
		blockDao.deleteById(blockId);
	}

	@Override
	public void editBlock(Block block) {
		addBlock(block);
	}
}

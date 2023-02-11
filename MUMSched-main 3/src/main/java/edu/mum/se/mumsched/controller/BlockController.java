package edu.mum.se.mumsched.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.service.BlockService;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Controller
@RequestMapping("/admin/block")
public class BlockController {
	
	@Autowired
	private BlockService blockService;
	
	@GetMapping("")
	public String showBlockPage(Model model) {
		List<Block> list = blockService.getAllBlocks();
		model.addAttribute("blocks", list);
		return "block-list";
	}
	
	@GetMapping("/add")
	public String showNewBlockForm(Model model) {
		model.addAttribute("newBlock", new Block());
		return "block-create";
	}
	
	@PostMapping("/add")
	public String addNewBlock(Block block) {
		blockService.addBlock(block);
		return "redirect:../block";
	}
	
	@DeleteMapping("/delete/{blockId}")
	@ResponseBody
	public String deleteEntry(@PathVariable("blockId") String id) {
		blockService.deleteBlock(Integer.parseInt(id));
		return "redirect:../../block";
	}
	
	@GetMapping("/edit/{blockId}")
	public String showUpdateEntry(@PathVariable("blockId") String id, Model model) throws NotFoundException {
		Block block = blockService.getBlock(Integer.parseInt(id));
		model.addAttribute("block", block);
		return "block-edit";
	}
	
	@PostMapping("/edit")
	public String updateEntry(Model model, Block block) {
		blockService.editBlock(block);
		return "redirect:../block";
	}

}

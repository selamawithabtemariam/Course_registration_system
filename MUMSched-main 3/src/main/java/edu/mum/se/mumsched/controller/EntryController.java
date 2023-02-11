package edu.mum.se.mumsched.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.service.EntryService;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Controller
@RequestMapping("/admin/entry")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@GetMapping("")
	public String showEntryPage(Model model) {
		List<Entry> list = entryService.getAllEntries();
		model.addAttribute("entries", list);
		return "entry-list";
	}
	
	@GetMapping("/add")
	public String showNewEntryForm(Model model) {
		model.addAttribute("newEntry", new Entry());
		return "entry-create";
	}
	
	@PostMapping("/add")
	public String addNewEntry(Entry entry) {
		entryService.addEntry(entry);
		return "redirect:../entry";
	}
	
	@DeleteMapping("/delete/{entryId}")
	@ResponseBody
	public String deleteEntry(@PathVariable("entryId") String id) {
		entryService.deleteEntry(Integer.parseInt(id));
		return "redirect:../../entry";
	}
	
	@GetMapping("/edit/{entryId}")
	public String showUpdateEntry(@PathVariable("entryId") String id, Model model) throws NotFoundException {
		Entry entry = entryService.getEntry(Integer.parseInt(id));
		model.addAttribute("entry", entry);
		return "entry-edit";
	}
	
	@PostMapping("/edit")
	public String updateEntry(Model model, Entry entry) {
		entryService.editEntry(entry);
		return "redirect:../entry";
	}
}

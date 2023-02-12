package edu.mum.se.mumsched.controller;

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

import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.service.FacultyService;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Controller
@RequestMapping("/admin/faculty")
public class FacultyController {
	
	
	@Autowired
	private FacultyService facultyService;
	
	@GetMapping("")
	public String showFacultyPage(Model model) {
		List<Faculty> list = facultyService.getAllFaculties();
		model.addAttribute("faculties", list);
		return "faculty-list";
	}
	
	@GetMapping("/add")
	public String showNewFacultyForm(Model model) {
		model.addAttribute("newFaculty", new Faculty());
		return "faculty-create";
	}
	
	@PostMapping("/add")
	public String addNewFaculty(Faculty faculty) {
		facultyService.addFaculty(faculty);
		return "redirect:../faculty";
	}
	
	@DeleteMapping("/delete/{facultyId}")
	@ResponseBody
	public String deleteFaculty(@PathVariable("facultyId") String id) {
		facultyService.deleteFaculty(Integer.parseInt(id));
		return "redirect:../../faculty";
	}
	
	@GetMapping("/edit/{facultyId}")
	public String showUpdateFaculty(@PathVariable("facultyId") String id, Model model) throws NotFoundException {
		Faculty faculty = facultyService.getFaculty(Integer.parseInt(id));
		model.addAttribute("faculty", faculty);
		return "faculty-edit";
	}
	
	@PostMapping("/edit")
	public String updateFaculty(Model model, Faculty faculty) {
		facultyService.editFaculty(faculty);
		return "redirect:../faculty";
	}

}

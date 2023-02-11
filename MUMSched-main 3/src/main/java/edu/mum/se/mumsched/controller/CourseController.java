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

import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.service.CourseService;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("")
	public String showCoursePage(Model model) {
		List<Course> list = courseService.getAllCourses();
		model.addAttribute("courses", list);
		return "course-list";
	}
	
	@GetMapping("/add")
	public String showNewCourseForm(Model model) { //model UI ruu medeelel yawuulah, 
		model.addAttribute("newCourse", new Course());
		return "course-create";
	}
	
	@PostMapping("/add")
	public String addNewCourse(Course course) {
		courseService.addCourse(course);
		return "redirect:../course";
	}
	
	@DeleteMapping("/delete/{courseId}")
	@ResponseBody
	public String deleteCourse(@PathVariable("courseId") String id) throws NotFoundException {
		courseService.deleteCourse(id);
		return "redirect:../../course";
	}
	
	@GetMapping("/edit/{courseId}")
	public String showUpdateCourse(@PathVariable("courseId") String id, Model model) throws NotFoundException {
		Course course = courseService.getCourse(Integer.parseInt(id));
		model.addAttribute("course", course);
		return "course-edit";
	}
	
	@PostMapping("/edit")
	public String updateCourse(Model model, Course course) {
		courseService.editCourse(course);
		return "redirect:../course";
	}
}

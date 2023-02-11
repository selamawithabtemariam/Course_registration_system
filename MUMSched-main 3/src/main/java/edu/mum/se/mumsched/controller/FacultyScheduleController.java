package edu.mum.se.mumsched.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.se.mumsched.component.SessionManager;
import edu.mum.se.mumsched.config.AuthenticationHandler;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.FacultySchedule;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.service.BlockService;
import edu.mum.se.mumsched.service.CourseService;
import edu.mum.se.mumsched.service.FacultyScheduleService;
import edu.mum.se.mumsched.service.FacultyService;
import edu.mum.se.mumsched.service.LessonService;

@Controller
@RequestMapping("/faculty")
public class FacultyScheduleController {
	
	@Autowired
	private FacultyScheduleService facultyScheduleService;

	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private BlockService blockService;
	
	@Autowired
	private SessionManager session;
	
	@Autowired
	private LessonService lessonService;
	
	@GetMapping("")
	public String showFacultySchedule(Model model) {
		List<FacultySchedule> schedules = facultyScheduleService.getSchedules();
		model.addAttribute("schedules", schedules);
		model.addAttribute("faculty", facultyService.getFaculty(session.getUser().getId()));
		return "schedule-faculty";
	}
	
	@GetMapping("/schedule/block/{blockId}")
	public String showFacultyAddScheduleForm(Model model, @PathVariable("blockId") String blockId) {
		model.addAttribute("block", blockService.getBlock(Integer.parseInt(blockId)));
		model.addAttribute("newCourse", new Course());
		model.addAttribute("courses", courseService.getAllCourses());
		return "schedule-faculty-create";
	}

	@PostMapping("/schedule/block/{blockId}")
	public String createLesson(@PathVariable("blockId") String blockId, Course course) {
		facultyScheduleService.addLessonToFaculty(blockId, course);
		return "redirect:../../../faculty";
	}
	
	@GetMapping("/view-students/lesson/{lessonId}")
	public String viewStudentsForSchedule(Model model, @PathVariable("lessonId") String lessonId) {
		Lesson lesson = lessonService.getLessonById(Integer.parseInt(lessonId));
		model.addAttribute("lesson", lesson);
		return "schedule-faculty-view-students";
	}
}

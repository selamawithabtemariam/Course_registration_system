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
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.StudentSchedule;
import edu.mum.se.mumsched.service.BlockService;
import edu.mum.se.mumsched.service.LessonService;
import edu.mum.se.mumsched.service.StudentScheduleService;
import edu.mum.se.mumsched.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentScheduleController {

	@Autowired
	private StudentScheduleService studentScheduleService;
	
	@Autowired
	private LessonService lessonService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SessionManager session;

	@Autowired
	private BlockService blockService;
	
	@GetMapping("")
	public String showStudentSchedule(Model model) {
		//Get blocks after the entry based on track
		List<StudentSchedule> schedules = studentScheduleService.getSchedules();
		model.addAttribute("schedules", schedules);
		model.addAttribute("student", studentService.getStudent(session.getUser().getId()));

		return "schedule-student";
	}
	
	@GetMapping("/schedule/block/{blockId}")
	public String showStudentAddScheduleForm(Model model, @PathVariable("blockId") String blockId) {
		model.addAttribute("block", blockService.getBlock(Integer.parseInt(blockId)));
		model.addAttribute("newLesson", new Lesson());
		model.addAttribute("lessons", lessonService.getLessonsByBlockId(blockId));
		return "schedule-student-create";
	}

	@PostMapping("/schedule")
	public String createLesson(Lesson lesson) {
		studentScheduleService.assignToLesson(lesson);
		return "redirect:../student";
	}
}

package edu.mum.se.mumsched.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.se.mumsched.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.mum.se.mumsched.component.SessionManager;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.StudentLesson;
import edu.mum.se.mumsched.domain.StudentSchedule;

@Service
public class StudentScheduleServiceImpl implements StudentScheduleService {

	@Autowired
	private BlockService blockService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private SessionManager session;
	
	@Autowired
	private StudentLessonService studentLessonService;
	
	@Override
	public List<StudentSchedule> getSchedules() {
		List<Block> blocks = blockService.getAllBlocks();
		Student student = studentService.getStudent(session.getUser().getId());
		//List<Lesson> lessons = student.getLessons();
		List<StudentLesson> studentLessons = student.getStudentLessons();
		List<Lesson> lessons = studentLessons.stream().map(sl -> sl.getLesson()).collect(Collectors.toList());
		
		Collections.sort(lessons, (a, b) -> a.getBlock().getYear() * 100 + a.getBlock().getMonth() - b.getBlock().getYear() * 100 - b.getBlock().getMonth());
		
		List<StudentSchedule> schedules = new ArrayList<>(blocks.size());
		int i = 0; //lesson pointer
		for(Block block : blocks) {
			StudentSchedule sched = new StudentSchedule();
			sched.setBlock(block);
			
			if(lessons != null && !lessons.isEmpty() && i < lessons.size()
					&& block.getYear() == lessons.get(i).getBlock().getYear() && block.getMonth() == lessons.get(i).getBlock().getMonth()) {
				sched.setCourse(lessons.get(i).getCourse());
				sched.setFaculty(lessons.get(i).getFaculty());
				sched.setLessonId(lessons.get(i).getId());
				i++;
			}
			schedules.add(sched);
		}
		return schedules;
	}

	@Override
	public void assignToLesson(Lesson lesson) {
		/*Lesson theLesson = lessonService.getLessonById(lesson.getId());
		
		Student student = studentService.getStudent(session.getUser().getId());
		student.getLessons().add(theLesson);
		
		studentService.updateStudent(student);
		
		theLesson.getStudents().add(student);*/
		
		lesson = lessonService.getLessonById(lesson.getId());
		Student student = studentService.getStudent(session.getUser().getId());
		studentLessonService.createStudentLesson(lesson, student);
	}

}

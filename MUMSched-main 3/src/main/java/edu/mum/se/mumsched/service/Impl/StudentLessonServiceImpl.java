package edu.mum.se.mumsched.service.Impl;

import edu.mum.se.mumsched.service.StudentLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.mumsched.dao.StudentLessonDao;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.StudentLesson;

@Service
public class StudentLessonServiceImpl implements StudentLessonService {

	@Autowired
	private StudentLessonDao studentLessonDao;
	
	@Override
	public void createStudentLesson(Lesson lesson, Student student) {
		StudentLesson studentLesson = new StudentLesson();
		studentLesson.setLesson(lesson);
		studentLesson.setStudent(student);
		studentLessonDao.save(studentLesson);
	}

}

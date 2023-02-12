package edu.mum.se.mumsched.service;

import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.Student;

public interface StudentLessonService {

	void createStudentLesson(Lesson lesson, Student student);

}

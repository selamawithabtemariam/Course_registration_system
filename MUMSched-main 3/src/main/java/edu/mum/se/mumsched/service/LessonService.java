package edu.mum.se.mumsched.service;

import java.util.List;

import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;

public interface LessonService {

	List<Lesson> getLessonsByBlockId(String blockId);

	List<Lesson> getLessonByFacultyId(Integer facultyId);

	void createLesson(Block block, Course course, Faculty faculty);

	Lesson getLessonById(Integer lessonId);

}

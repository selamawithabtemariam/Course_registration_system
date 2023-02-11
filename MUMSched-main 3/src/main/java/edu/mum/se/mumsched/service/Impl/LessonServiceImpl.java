package edu.mum.se.mumsched.service.Impl;

import java.util.List;

import edu.mum.se.mumsched.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.mumsched.dao.LessonDao;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;

@Service
public class LessonServiceImpl implements LessonService {
	
	@Autowired
	private LessonDao lessonDao;

	@Override
	public List<Lesson> getLessonsByBlockId(String blockId) {
		return lessonDao.findByBlockId(Integer.parseInt(blockId));
	}

	@Override
	public List<Lesson> getLessonByFacultyId(Integer facultyId) {
		return lessonDao.findByFacultyId(facultyId);
	}
	
	@Override
	public void createLesson(Block block, Course course, Faculty faculty) {		
		Lesson lesson = new Lesson();
		lesson.setBlock(block);
		lesson.setCourse(course);
		lesson.setFaculty(faculty);
		
		lessonDao.save(lesson);
	}

	@Override
	public Lesson getLessonById(Integer lessonId) {
		return lessonDao.findById(lessonId).get();
	}

}

package edu.mum.se.mumsched.service;

import java.util.List;

import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.FacultySchedule;

public interface FacultyScheduleService {

	List<FacultySchedule> getSchedules();

	void addLessonToFaculty(String blockId, Course course);
}

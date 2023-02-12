package edu.mum.se.mumsched.service;

import java.util.List;

import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.StudentSchedule;

public interface StudentScheduleService {

	List<StudentSchedule> getSchedules();

	void assignToLesson(Lesson lesson);

}

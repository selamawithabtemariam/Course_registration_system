package edu.mum.se.mumsched.service;

import java.util.List;

import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.service.exception.NotFoundException;

public interface CourseService {
	void addCourse(Course course);
	Course getCourse(Integer id) throws NotFoundException;
	List<Course> getAllCourses();
	void deleteCourse(String courseId) throws NotFoundException;
	void editCourse(Course course);

}

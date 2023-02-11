package edu.mum.se.mumsched.service;

import java.util.List;

import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.service.exception.NotFoundException;

public interface FacultyService {
	void addFaculty(Faculty faculty);
	Faculty getFaculty(Integer facultyId);
	List<Faculty> getAllFaculties();
	void deleteFaculty(Integer facultyId);
	void editFaculty(Faculty faculty);
	
}

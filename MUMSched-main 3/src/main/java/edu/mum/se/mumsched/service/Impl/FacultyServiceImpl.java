package edu.mum.se.mumsched.service.Impl;

import java.util.List;
import java.util.Optional;

import edu.mum.se.mumsched.service.FacultyService;
import edu.mum.se.mumsched.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.mumsched.dao.FacultyDao;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Service
public class FacultyServiceImpl implements FacultyService {
	
	private static final String FACULTY_ROLE = "ROLE_FACULTY";
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void addFaculty(Faculty faculty) {
		Integer studentId = userService.createUser(faculty.getEmail(), FACULTY_ROLE);
		faculty.setId(studentId);
		facultyDao.save(faculty);
	}

	@Override
	public Faculty getFaculty(Integer id) {
		Optional<Faculty> faculty = facultyDao.findById(id);
		
		if(faculty.isEmpty())
			throw new NotFoundException("No faculty with id " + id + " found.");
		
		return faculty.get();
	}

	@Override
	public List<Faculty> getAllFaculties() {
		return facultyDao.findAll();
	}

	@Override
	public void deleteFaculty(Integer facultyId) {
		facultyDao.deleteById(facultyId);
	}

	@Override
	public void editFaculty(Faculty faculty) {
		userService.updateUser(faculty.getId(), faculty.getEmail());
		facultyDao.save(faculty);
	}

}

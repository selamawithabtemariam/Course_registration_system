package edu.mum.se.mumsched.service.Impl;

import java.util.List;
import java.util.Optional;

import edu.mum.se.mumsched.service.EntryService;
import edu.mum.se.mumsched.service.StudentService;
import edu.mum.se.mumsched.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import edu.mum.se.mumsched.dao.StudentDao;
import edu.mum.se.mumsched.dao.UserDao;
import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.Track;
import edu.mum.se.mumsched.domain.User;
import edu.mum.se.mumsched.service.exception.NotFoundException;

@Service
public class StudentServiceImpl implements StudentService {
	
	private static final String STUDENT_ROLE = "ROLE_STUDENT";

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntryService entryService;

	@Override
	public void addStudent(Student student) {
		Integer studentId = userService.createUser(student.getEmail(), STUDENT_ROLE);
		Entry entry = entryService.getEntry(student.getEntry().getId());
		
		student.setId(studentId);
		student.setEntry(entry);

		studentDao.save(student);
	}

	@Override
	public Student getStudent(Integer id) throws NotFoundException {
		Optional<Student> student = studentDao.findById(id);
		
		if(student.isEmpty())
			throw new NotFoundException("No student with id " + id + " found.");
		
		return student.get();
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.findAll();
	}

	@Override
	public void deleteStudent(Integer studentId) {
		studentDao.deleteById(studentId);
		
	}

	@Override
	public void updateStudent(Student student) {
		userService.updateUser(student.getId(), student.getEmail());
		studentDao.save(student);
	}

}

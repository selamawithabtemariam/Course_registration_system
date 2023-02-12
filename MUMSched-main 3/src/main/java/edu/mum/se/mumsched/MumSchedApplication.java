package edu.mum.se.mumsched;

import edu.mum.se.mumsched.dao.StudentDao;
import edu.mum.se.mumsched.dao.UserDao;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.service.StudentService;
import edu.mum.se.mumsched.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MumSchedApplication implements CommandLineRunner {

	@Autowired
	UserService userService;
	@Autowired
	private UserDao userDao;

	@Autowired
	StudentService studentService;


	public static void main(String[] args) {
		SpringApplication.run(MumSchedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");






	}
}

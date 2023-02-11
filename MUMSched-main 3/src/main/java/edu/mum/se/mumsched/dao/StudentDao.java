package edu.mum.se.mumsched.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.mum.se.mumsched.domain.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer>{

}

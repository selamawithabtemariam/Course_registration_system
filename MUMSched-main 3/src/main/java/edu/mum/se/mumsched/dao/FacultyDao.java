package edu.mum.se.mumsched.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.mumsched.domain.Faculty;

public interface FacultyDao extends JpaRepository<Faculty, Integer>{

}

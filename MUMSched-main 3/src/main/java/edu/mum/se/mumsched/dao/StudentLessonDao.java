package edu.mum.se.mumsched.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.se.mumsched.domain.StudentLesson;

@Repository
public interface StudentLessonDao extends JpaRepository<StudentLesson, Integer> {

}

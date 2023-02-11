package edu.mum.se.mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.se.mumsched.domain.Lesson;

@Repository
public interface LessonDao extends JpaRepository<Lesson, Integer> {
	
	List<Lesson> findByFacultyId(Integer facultyId);

	List<Lesson> findByBlockId(Integer blockId);
}

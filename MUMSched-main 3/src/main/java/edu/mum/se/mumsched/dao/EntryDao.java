package edu.mum.se.mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.se.mumsched.domain.Entry;

@Repository
public interface EntryDao extends JpaRepository<Entry, Integer> {
	List<Entry> findAllByOrderByYearAscMonthAsc();
}

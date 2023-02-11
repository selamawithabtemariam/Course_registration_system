package edu.mum.se.mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.mumsched.domain.Block;

public interface BlockDao extends JpaRepository<Block, Integer> {

	List<Block> findAllByOrderByYearAscMonthAsc();

}

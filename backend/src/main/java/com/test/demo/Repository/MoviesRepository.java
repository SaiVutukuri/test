package com.test.demo.Repository;

import com.test.demo.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
	List<Movies> findByStatus(String status);
}
package com.test.demo.Service;


import com.test.demo.Entity.Movies;

import java.util.List;

public interface MoviesService {
	List<Movies> getAllMovies();
	Movies getMoviesById(int cId);
	Movies addMovies(Movies movies);
	void deleteMoviesById(int cId);
	List<Movies> getStatus(String status);
	void updateStatus(Movies movies);
}
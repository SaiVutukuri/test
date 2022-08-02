package com.test.demo.ServiceImpl;

import com.test.demo.Entity.Movies;
import com.test.demo.Repository.MoviesRepository;
import com.test.demo.Service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImpl implements MoviesService {
	@Autowired
	private MoviesRepository moviesRepository;

	@Override
	public List<Movies> getAllMovies() {
		return moviesRepository.findAll();
	}

	@Override
	public Movies addMovies(Movies movies) {
		return moviesRepository.save(movies);
	}

	@Override
	public Movies getMoviesById(int cId) {
		if (moviesRepository.findById(cId).isPresent()) {
			return moviesRepository.findById(cId).get();
	    }else {
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movies with id %d not found", cId));
	    }
	}

	public void deleteMoviesById(int cId) {
		try {
			moviesRepository.deleteById(cId);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Movies> getStatus(String status) {
		List<Movies> statuses = new ArrayList<Movies>();
		statuses=moviesRepository.findByStatus(status);
		return statuses;
	}

	@Override
	public void updateStatus(Movies movies) {
		Optional<Movies> moviesDB = moviesRepository.findById(movies.getcId());
		moviesRepository.save(movies);
	}
}

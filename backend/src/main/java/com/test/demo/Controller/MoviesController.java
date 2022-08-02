package com.test.demo.Controller;

import com.test.demo.Entity.Movies;
import com.test.demo.Service.MoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MoviesController {
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);
    @Autowired
    MoviesService moviesService;

    // Insert movies record
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movies addMovies(@RequestBody Movies movies) {
        return moviesService.addMovies(movies);
    }

    // Fetch all movies records
    @GetMapping
    public List<Movies> getAllMovies() {
        return moviesService.getAllMovies();
    }

    // Fetch single user
    @GetMapping("/{id}")
    public Movies getMoviesById(@PathVariable("id") int cId) {
        try {
            moviesService.getMoviesById(cId);
            logger.info("Get Movies By ID Success");
            return moviesService.getMoviesById(cId);
        } catch (Exception e) {
            logger.error("Movies Not Found " + e.getMessage(), HttpStatus.NOT_FOUND);
            return null;
        }

    }

    // Delete Movies record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovies(@PathVariable int id) {
        try {
            moviesService.deleteMoviesById(id);
            logger.info("Delete Movies By ID Success");
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception ex) {
            // log the error message
            System.out.println(ex.getMessage());
            logger.error("Movies Not Found " + ex.getMessage(), HttpStatus.NOT_FOUND);
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    // get by status
    @GetMapping("status/{status}")
    public List<Movies> getStatus(@PathVariable("status") String status) {
        return moviesService.getStatus(status);
    }

    // Update user record
    @PutMapping("/updatestatus")
    public ResponseEntity<String> updateStatus(@RequestBody Movies movies) {
        try {
            moviesService.updateStatus(movies);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            // log the error message
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}

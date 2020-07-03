package com.techtopics.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtopics.springboot.dataaccess.MovieListRepository;
import com.techtopics.springboot.model.Movie;

@Controller
@RequestMapping("/")
public class MovieListController {
	
	@Autowired
	private MovieListRepository movieListRepository;
	
	@RequestMapping(value="/movies/{actor}",method=RequestMethod.GET)
	public String getMovieListByActor(@PathVariable("actor") String name, Model model) {		
		//call the repository to get the movie list
		List<Movie> movieList = movieListRepository.findMoviesByActor(name);
		model.addAttribute("movies",movieList);		
		return "moviesList";
	}
	
	
	@RequestMapping(value="/movies",method=RequestMethod.POST, consumes = "application/json")	
	public ResponseEntity<Object> addMovie(@RequestBody Movie movie) {
		movieListRepository.save(movie);		
        return ResponseEntity.ok().build();
	}
	
	
}

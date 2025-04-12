package com.App.fawryTask.Controller;

import com.App.fawryTask.Entity.Movies;
import com.App.fawryTask.Repsitory.MovieRepository;
import com.App.fawryTask.Service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class movieController {
     private static final String OMDB_API_URL="http://www.omdbapi.com/?apikey=YOUR_API_KEY&t=\"";
      @Autowired
      private MovieService movieService;

      @GetMapping
      public List<Movies>  getMovie(  @RequestParam String Title){
           RestTemplate restTemplate= new RestTemplate();
           String result= restTemplate.getForObject(OMDB_API_URL+ Title,String.class);
          ObjectMapper objectMapper = new ObjectMapper();
          try {

              Map<String, Object> responseMap = objectMapper.readValue(result, Map.class);
              List<Movies> movies = (List<Movies>) responseMap.get("Search");
              return movies;
          } catch (Exception e) {
              e.printStackTrace();
              return Collections.emptyList(); // or handle the error appropriately
          }
      }
      @PostMapping
     public void addNewMovie(@RequestParam Movies movies){
     movieService.addMovie(movies);
      }
      @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable int id){
      movieService.deleteMoviweById(id);
      }
}

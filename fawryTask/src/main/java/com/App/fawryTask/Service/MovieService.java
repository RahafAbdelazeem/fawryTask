package com.App.fawryTask.Service;

import com.App.fawryTask.Entity.Movies;
import com.App.fawryTask.Repsitory.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public List<Movies>getAllMovies(){
        return movieRepository.findAll();
    }

     public List<Movies> getMovieByTitle(String Title){
         return movieRepository.findByTitle(Title);

     }
     public void addMovie(Movies movie){
         movieRepository.save(movie);
     }
      public void deleteMoviweById( int Id){
        movieRepository.deleteById(Id);
      }
}

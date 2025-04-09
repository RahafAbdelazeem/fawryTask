package com.App.fawryTask.Repsitory;

import com.App.fawryTask.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer> {
    List<Movies> findByTitle(String title);
    Movies findByImdbId(int imdbId);
}

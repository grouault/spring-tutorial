package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Movie;
import com.hibernate4all.tutorial.repository.MovieRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie getMovie(Long id) {
        Movie proxy = movieRepository.getReference(id);
        Movie  movie = (Movie) Hibernate.unproxy(proxy);
        return movie;
    }

    public Movie addMovie(Movie movie){
        return movieRepository.persist(movie);
    }

    public Optional<Movie> updateMovie(Movie movie) {
        return movieRepository.update(movie);
    }

    public Boolean removeMovie(Long id) {
        return movieRepository.remove(id);
    }

    @Transactional
    public void updateDescription(Long id, String description) {
        Movie movie = movieRepository.find(id);
        movie.setDescription(description);
    }

    public List<Movie> getMovies(){
        List<Movie> movies = movieRepository.getAll();
        return movies;
    }

}

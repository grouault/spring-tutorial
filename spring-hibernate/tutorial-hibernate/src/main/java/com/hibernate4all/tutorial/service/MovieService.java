package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Movie;
import com.hibernate4all.tutorial.repository.MovieRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public void updateDescription(Long id, String description) {
        Movie movie = movieRepository.find(id);
        movie.setDescription(description);
    }

}

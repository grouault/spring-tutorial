package com.hibernate4all.tutorial.repository;

import com.hibernate4all.tutorial.domain.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void persist(Movie movie){
        LOGGER.trace("entityManager.contains() : " + entityManager.contains(movie));
        entityManager.persist(movie);
        entityManager.flush();
        // entityManager.detach(movie);
        entityManager.detach(movie);
        movie.setDescription("change description");
        LOGGER.trace("entityManager.contains() : " + entityManager.contains(movie));
    }

    @Transactional
    public Movie merge(Movie movie){
        Movie result = entityManager.merge(movie);
        entityManager.contains(result);
        return result;
    }

    public Movie find(Long id){
        Movie result = entityManager.find(Movie.class,id);
        LOGGER.trace("entityManager.contains() : " + entityManager.contains(result));
        return result;

    }

    public List<Movie> getAll(){
        return entityManager.createQuery("from Movie", Movie.class).getResultList();
    }

    @Transactional
    public void remove(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        LOGGER.trace("entityManager.contains() : " + entityManager.contains(movie));
        movie.setName("test gildas");
        movie.setDescription("test toto");
        Movie movie2 = entityManager.find(Movie.class, id);
        movie2.setName("test max");
        entityManager.persist(movie2);
        Movie movie3 = entityManager.find(Movie.class, id);
        LOGGER.trace("entityManager.contains() : " + entityManager.contains(movie));
        entityManager.remove(movie);
        LOGGER.trace("entityManager.contains() : " + entityManager.contains(movie));
    }

    public Movie getReference(Long id) {
        Movie result = entityManager.getReference(Movie.class, id);
        return result;
    }

}


package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MovieRatingTest {

    private MovieRating movieRating;

    @BeforeEach
    public void setUp() {
        movieRating = new MovieRating(10281, "The Matrix", 1999, 9.9, 1028);
    }

    @Test
    public void testMovieRatingCreated() {
        assertEquals(1, movieRating.getId());
        assertEquals("The Matrix", movieRating.getTitle());
        assertEquals(1999, movieRating.getYear());
        assertEquals(9.9, movieRating.getRating());
        assertEquals(1028, movieRating.getVotes());
    }

    @Test
    public void testMovieSetters() {
        movieRating.setId(2);
        movieRating.setTitle("The Matrix Reloaded");
        movieRating.setYear(2003);
        movieRating.setRating(9);
        movieRating.setVotes(1000);
        assertEquals(2, movieRating.getId());
        assertEquals("The Matrix Reloaded", movieRating.getTitle());
        assertEquals(2003, movieRating.getYear());
        assertEquals(9, movieRating.getRating());
        assertEquals(1000, movieRating.getVotes());
    }




}

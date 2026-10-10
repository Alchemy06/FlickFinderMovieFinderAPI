package com.flickfinder.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.javalin.http.Context;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * TODO: Implement this class
 */
class PersonControllerTest {
    private Context ctx;
    private PersonDAO personDAO;
    private PersonController personController;

    @BeforeEach
    void setUp() {
        personDAO = mock(PersonDAO.class);
        ctx = mock(Context.class);
        personController = new PersonController(personDAO);
    }

    @Test
    void testGetAllPeople() {
        personController.getAllPeople(ctx);
        try {
            verify(personDAO).getAllPeople();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testThrows500ExceptionWhenGetAllDatabaseError() throws SQLException {
        when(personDAO.getAllPeople()).thenThrow(new SQLException());
        personController.getAllPeople(ctx);
        verify(ctx).status(500);
    }

    @Test
    void testGetPersonById() {
        when(ctx.pathParam("id")).thenReturn("1");
        personController.getPersonById(ctx);
        try {
            verify(personDAO).getPersonById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//@Test
//    public void testGetMoviesStarringPerson() throws SQLException {
//        // Mock path param
//        when(ctx.pathParam("id")).thenReturn("1");
//
//
//    // Prepare a list of movies to return
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie(1, "Movie 1", 2020)); // Example movie object
//
//
//        // Mock static method PersonDAO.getMovieStarringPerson
//        try (MockedStatic<PersonDAO> mocked = Mockito.mockStatic(PersonDAO.class)) {
//            mocked.when(() -> personDAO.getMovieStarringPerson(123)).thenReturn(movies);
//
//            personController.getMoviesStarringPerson(ctx);
//
//            // Verify status 200 and json called with movies list
//            verify(ctx).status(200);
//            verify(ctx).json(movies);
//        }
//    }



}


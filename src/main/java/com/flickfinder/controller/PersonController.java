package com.flickfinder.controller;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonController {
    private final PersonDAO PersonDAO;

    public PersonController(PersonDAO personDAO) {
        this.PersonDAO = personDAO;
    }

    public void getAllPeople(Context ctx) {

        List<Person> person = new ArrayList<>();

        try {
            String limit = ctx.queryParam("limit");
            if (limit != null) {
                int intlimit = Integer.parseInt(limit);
                person = PersonDAO.getPersonByLimit(intlimit);
            }
            else {
                person = PersonDAO.getAllPeople();
            }
            ctx.json(person);
        }
        catch (SQLException e) {
            ctx.status(500);
            ctx.result("Database error");
            e.printStackTrace();
        }

    }
    public void getPersonById(Context ctx) {

        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            Person person = PersonDAO.getPersonById(id);
            if (person == null) {
                ctx.status(404);
                ctx.result("Movie not found");
                return;
            }
            ctx.json(PersonDAO.getPersonById(id));
        } catch (SQLException e) {
            ctx.status(500);
            ctx.result("Database error");
            e.printStackTrace();
        }
    }

    public void getMoviesStarringPerson(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Movie> movieStaringPerson = PersonDAO.getMovieStarringPerson(id);
            if (movieStaringPerson.isEmpty()) {
                ctx.status(404).result("No people found for this movie.");
            } else ctx.status(200).json(movieStaringPerson); // Return the list as JSON
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid movie ID format.");
        } catch (SQLException e) {
            ctx.status(500).result("Database error.");
            e.printStackTrace();
        }


        // to complete the must-have requirements you need to add the following methods:
        // getAllPeople
        // getPersonById
        // you will add further methods for the more advanced tasks; however, ensure your have completed
        // the must have requirements before you start these.
    }
}
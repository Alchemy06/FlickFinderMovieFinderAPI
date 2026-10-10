package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;

import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import io.javalin.http.Context;

/**
 * The controller for the movie endpoints.
 * 
 * The controller acts as an intermediary between the HTTP routes and the DAO.
 * 
 * As you can see each method in the controller class is responsible for
 * handling a specific HTTP request.
 * 
 * Methods a Javalin Context object as a parameter and uses it to send a
 * response back to the client.
 * We also handle business logic in the controller, such as validating input and
 * handling errors.
 *
 * Notice that the methods don't return anything. Instead, they use the Javalin
 * Context object to send a response back to the client.
 */

public class MovieController {

	/**
	 * The movie data access object.
	 */

	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initializes the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllMovies(Context ctx) {
		List<Movie> movie = new ArrayList<>();
		try {
			String limit =  ctx.queryParam("limit");
			if (limit != null) {
				int intlimit = Integer.parseInt(limit);
				movie = movieDAO.getMovieByLimit(intlimit);
			}
			else {
				movie = movieDAO.getAllMovies();
			}
			ctx.json(movie);
		}
		catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();

		}

	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	public void getPeopleByMovieId(Context ctx) {
		try {
			int id = Integer.parseInt(ctx.pathParam("id"));
			List<Person> PersoninMovie = movieDAO.getPeopleByMovieId(id);
			if (PersoninMovie.isEmpty()) {
				ctx.status(404).result("No people found for this movie.");
			}
			else ctx.status(200).json(PersoninMovie); // Return the list as JSON
		}
		catch (NumberFormatException e) {
			ctx.status(400).result("Invalid movie ID format.");
		} catch (SQLException e) {
			ctx.status(500).result("Database error.");
			e.printStackTrace();
		}
	}

	public void getRatingsByYear(Context ctx) {
		try {
			int year = Integer.parseInt(ctx.pathParam("year"));

			// Get optional query parameters, with defaults
			int limit = ctx.queryParamAsClass("limit", Integer.class).getOrDefault(50);
			int minVotes = ctx.queryParamAsClass("votes", Integer.class).getOrDefault(1000);
			List<MovieRating> results = movieDAO.getMovieByYear(year, minVotes, limit);

			if (results.isEmpty()) {
				ctx.status(404).result("No movies found for this year.");
			} else {
				ctx.status(200).json(results);
			}
		} catch (NumberFormatException e) {
			ctx.status(400).result("Invalid movie ID format.");
		} catch (SQLException e) {
			ctx.status(500).result("Database error.");
			e.printStackTrace();
		}
	}
}
package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;
import com.flickfinder.model.MovieRating;

/**
 * Test for the Movie Data Access Object.
 * This uses an in-memory database for testing purposes.
 */

class MovieDAOTest {

	/**
	 * The movie data access object.
	 */

	private MovieDAO movieDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables.
	 * We are using an in-memory database for testing purposes.
	 * This gets passed to the Database class to get a connection to the database.
	 * As it's a singleton class, the entire application will use the same
	 * connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		movieDAO = new MovieDAO();

	}

	/**
	 * Tests the getAllMovies method.
	 * We expect to get a list of all movies in the database.
	 * We have seeded the database with 5 movies, so we expect to get 5 movies back.
	 * At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllMovies() {
		try {
			List<Movie> movies = movieDAO.getAllMovies();
			assertEquals(5, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method.
	 * We expect to get the movie with the specified id.
	 */
	@Test
	void testGetMovieById() {
		Movie movie;
		try {
			movie = movieDAO.getMovieById(1);
			assertEquals("The Shawshank Redemption", movie.getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetMovieByIdInvalidId() {
		// write an assertThrows for a SQLException

		try {
			Movie movie = movieDAO.getMovieById(1000);
			assertEquals(null, movie);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	@Test
	void testGetMovieByYearWithRatings() {
		try {
			List<MovieRating> results = movieDAO.getMovieByYear(1994, 1000, 50);

			// Replace these assertions with expected values from your Seeder
			assertEquals(false, results.isEmpty(), "Expected at least one movie for 1994 with >1000 votes");

			MovieRating first = results.get(0);
			assertEquals(1994, first.getYear());
			assertEquals(2200000, first.getVotes());
			assertEquals(9.3, first.getRating()); // just to be sure rating is present

		} catch (SQLException e) {
			fail("SQLException thrown in testGetMovieByYearWithRatings");
			e.printStackTrace();
		}
	}


	@Test
	void testGetMovieByLimit() {
		try {
			int limit = 3;
			List<Movie> movies = movieDAO.getMovieByLimit(limit);

			assertEquals(limit, movies.size(), "Expected exactly " + limit + " movies");

		} catch (SQLException e) {
			fail("SQLException thrown in testGetMovieByLimit");
			e.printStackTrace();
		}
	}

	/**
	 * Tests that requesting more movies than exist still works without error.
	 */
	@Test
	void testGetMovieByLimitMoreThanAvailable() {
		try {
			int limit = 100; // assuming seeder doesn't seed this many
			List<Movie> movies = movieDAO.getMovieByLimit(limit);

			// You seeded 5 movies, so we should get 5 max
			assert(movies.size() <= limit);
		} catch (SQLException e) {
			fail("SQLException thrown in testGetMovieByLimitMoreThanAvailable");
			e.printStackTrace();
		}
	}

	/**
	 * Tests retrieving people (stars) by a valid movie ID.
	 */
	@Test
	void testGetPeopleByMovieId() {
		try {
			// Assume movie ID 1 is seeded and has at least one star
			List<Person> people = movieDAO.getPeopleByMovieId(1);

			assertEquals(1 , people.get(0).getId());
			assertEquals(2 , people.get(1).getId());


		} catch (SQLException e) {
			fail("SQLException thrown in testGetPeopleByMovieId");
			e.printStackTrace();
		}
	}
	@Test
	void testGetPeopleByMovieIdInvalidId() {
		try {
			// Assume 999 is not a valid movie ID in seeded data
			List<Person> people = movieDAO.getPeopleByMovieId(999);
			assert(people.isEmpty());
		} catch (SQLException e) {
			fail("SQLException thrown in testGetPeopleByMovieIdInvalidId");
			e.printStackTrace();
		}
	}







	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}

}
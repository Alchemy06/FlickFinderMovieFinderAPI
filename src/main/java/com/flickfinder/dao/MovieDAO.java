package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.util.Database;
import com.flickfinder.model.Person;
import com.flickfinder.dao.PersonDAO;

/**
 * The Data Access Object for the Movie table.
 * 
 * This class is responsible for getting data from the Movies table in the
 * database.
 * 
 */
public class MovieDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 * 
	 */
	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @return a list of all movies in the database
	 * @throws SQLException if a database error occurs
	 */

	public List<Movie> getAllMovies() throws SQLException {
		List<Movie> movies = new ArrayList<>();

		Statement statement = connection.createStatement();
		
		// I've set the limit to 10 for development purposes - you should do the same.
		ResultSet rs = statement.executeQuery("select * from movies LIMIT 50");
		
		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return movies;
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param id the id of the movie
	 * @return the movie with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Movie getMovieById(int id) throws SQLException {

		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}
		
		// return null if the id does not return a movie.

		return null;

	}
	public List<Person> getPeopleByMovieId(int id) throws SQLException {

		PersonDAO personDAO = new PersonDAO();
		List<Person> people = new ArrayList<>();
		String statement = "select * from Stars where movie_id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			people.add(personDAO.getPersonById(rs.getInt("person_id")));
		}

		// return null if the id does not return a movie.

		return people;

	}

	public List<Movie> getMovieByLimit(int Limit) throws SQLException {
		List<Movie> Movie = new ArrayList<>();

		String sql = "select * from movies LIMIT " + Limit;
		Statement ps = connection.createStatement();
		ResultSet rs = ps.executeQuery(sql);


		while (rs.next()) {
			Movie.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return Movie;
	}

	public List<MovieRating> getMovieByYear(int year, int minVotes, int limit) throws SQLException {
		List<MovieRating> movieRatings = new ArrayList<>();

		//used text block to avoid confusion.

		String sql = """
        SELECT m.id, m.title, m.year, r.rating, r.votes
        FROM (
            SELECT * FROM Movies WHERE year = ? LIMIT ?
        ) m
        JOIN Ratings r ON m.id = r.movie_id
        WHERE r.votes > ?
        ORDER BY r.rating DESC
    """;
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, year);
		ps.setInt(2, limit);
		ps.setInt(3, minVotes);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			movieRatings.add(new MovieRating(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getDouble("rating"),
					rs.getInt("votes")

			));
		}

		return movieRatings;
	}
}



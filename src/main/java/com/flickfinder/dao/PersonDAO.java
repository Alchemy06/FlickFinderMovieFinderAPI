package com.flickfinder.dao;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement this class
 * 
 */
public class PersonDAO {

    private final Connection connection;
    public PersonDAO() {
        Database database = Database.getInstance();
        connection = database.getConnection();
    }

    public List<Person> getAllPeople() throws SQLException {
        List<Person> People = new ArrayList<>();

        Statement statement = connection.createStatement();

        // I've set the limit to 10 for development purposes - you should do the same.
        ResultSet rs = statement.executeQuery("select * from people LIMIT 50");

        while (rs.next()) {
            People.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
        }

        return People;
    }

    public Person getPersonById(int id) throws SQLException {

        String statement = "select * from people where id = ?";
        PreparedStatement ps = connection.prepareStatement(statement);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
        }

        // return null if the id does not return a movie.

        return null;

    }

    public List<Movie> getMovieStarringPerson(int id) throws SQLException {

        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movie = new ArrayList<>();
        String statement = "select * from Stars where person_id = ?";
        PreparedStatement ps = connection.prepareStatement(statement);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            movie.add(movieDAO.getMovieById(rs.getInt("movie_id")));
        }

        // return null if the id does not return a movie.

        return movie;

    }

    public List<Person> getPersonByLimit(int Limit) throws SQLException {
        List<Person> person = new ArrayList<>();

        String sql = "select * from people LIMIT " + Limit;
        Statement ps = connection.createStatement();
        ResultSet rs = ps.executeQuery(sql);


        while (rs.next()) {
            person.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
        }

        return person;
    }






        // for the must have requirements, you will need to implement the following
	// methods:
	// - getAllPeople()
	// - getPersonById(int id)
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.  

}

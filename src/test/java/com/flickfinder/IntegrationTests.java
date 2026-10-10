package com.flickfinder;

import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;
import io.javalin.Javalin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * These are our integration tests.
 * We are testing the application as a whole, including the database.
 */
class IntegrationTests {

	/**
	 * The Javalin app.*
	 */
	Javalin app;

	/**
	 * The seeder object.
	 */
	Seeder seeder;

	/**
	 * The port number. Try and use a different port number from your main
	 * application.
	 */
	int port = 6000;

	/**
	 * The base URL for our test application.
	 */
	String baseURL = "http://localhost:" + port;

	/**
	 * Bootstraps the application before each test.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		app = AppConfig.startServer(port);
	}

	/**
	 * Test that the application retrieves a list of all movies.
	 * Notice how we are checking the actual content of the list.
	 * At this higher level, we are not concerned with the implementation details.
	 */

	@Test
	void retrieves_a_list_of_all_movies() {
		given().when().get(baseURL + "/movies").then().assertThat().statusCode(200). // Assuming a successful
												// response returns HTTP
												// 200
				body("id", hasItems(1, 2, 3, 4, 5))
				.body("title", hasItems("The Shawshank Redemption", "The Godfather",
						"The Godfather: Part II", "The Dark Knight", "12 Angry Men"))
				.body("year", hasItems(1994, 1972, 1974, 2008, 1957));
	}

	@Test
	void retrieves_a_single_movie_by_id() {

		given().when().get(baseURL + "/movies/1").then().assertThat().statusCode(200). // Assuming a successful
												// response returns HTTP
												// 200
				body("id", equalTo(1))
				.body("title", equalTo("The Shawshank Redemption"))
				.body("year", equalTo(1994));
	}

	@Test
	void retrieves_a_list_of_all_people() {
		given().when().get(baseURL + "/people/?limit=5").then().assertThat().statusCode(200). // Assuming a successful
				// response returns HTTP
				// 200
						body("id", hasItems(1, 2, 3, 4, 5))
				.body("name", hasItems("Fred Astaire", "Lauren Bacall",
						"Brigitte Bardot", "John Belushi", "Ingmar Bergman"))
				.body("birth", hasItems(1899, 1924, 1934, 1949, 1918));
	}

	@Test
	void retrieves_a_single_person_by_id() {
		given().when().get(baseURL + "/people/1").then().assertThat().statusCode(200). // Assuming a successful
				// response returns HTTP
				// 200
						body("id", equalTo(1))
				.body("name", equalTo("Fred Astaire"))
				.body("birth", equalTo(1899));
	}

	@Test
	void retrieves_ratings_for_movies_by_year() {
		given().when().get(baseURL + "/movies/ratings/1994").then().assertThat().statusCode(200).
	body("id", hasItems(97416, 106339, 106379, 100881))
				.body("rating", hasItems(7.1, 6.6, 5.3, 5))
				.body("votes", hasItems(2018, 6271, 4090, 1194));

	}

	@Test
	void retrieves_stars_for_movie_by_id() {
		given().when().get(baseURL + "/movies/1/stars").then().assertThat().statusCode(200)
				.body("id", hasItems(1, 2))
				.body("name", hasItems("Morgan Freeman", "Tim Robbins"));
	}

	@Test
	void retrieves_movies_starring_a_person() {
		given().when().get(baseURL + "/people/1/movies").then().assertThat().statusCode(200)
				.body("id", hasItems(1, 5))
				.body("title", hasItems("The Shawshank Redemption", "12 Angry Men"));
	}



	/**
	 * Tears down the application after each test.
	 * We want to make sure that each test runs in isolation.
	 */
	@AfterEach
	void tearDown() {
		seeder.closeConnection();
		app.stop();
	}

}

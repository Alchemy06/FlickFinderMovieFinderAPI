package com.flickfinder.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * 
 * TODO: Implement this class
 * 
 */

class PersonTest {

    private Person person;

    /**
     * Set up the movie object before each test.
     *
     */
    @BeforeEach
    public void setUp() {
        person = new Person(1, "Zayn Nova", 1999);
    }

    /**
     * Test the movie object is created with the correct values.
     */
    @Test
    public void testMovieCreated() {
        assertEquals(1, person.getId());
        assertEquals("Zayn Nova", person.getName());
        assertEquals(1999, person.getBirth());
    }

    /**
     * Test the movie object is created with the correct values.
     */
    @Test
    public void testMovieSetters() {
        person.setId(2);
        person.setName("Sage Phoenix");
        person.setBirth(2003);
        assertEquals(2, person.getId());
        assertEquals("Sage Phoenix", person.getName());
        assertEquals(2003, person.getBirth());
    }


}
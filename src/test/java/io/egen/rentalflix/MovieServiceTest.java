package io.egen.rentalflix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit test cases for MovieService
 */
public class MovieServiceTest {

	static MovieService ms = new MovieService();
	
	@BeforeClass
	public static void loadTestData()
	{
		ms.create(new Movie(1, "Movie One", "2015", "English"));
		ms.create(new Movie(2, "Movie Two", "2015", "English"));
	}
	
	/**
	 * check if create works properly
	 */
	@Test
	public void createTestMovie()
	{
		assertNotNull(ms.create(new Movie(3, "Movie Three", "2015", "English")));
	}
	
	/**
	 * Check if movie list has at-least two entries
	 */
	@Test
	public void getEmptyList()
	{
		ArrayList<Movie> list =  (ArrayList<Movie>) ms.findAll();
		assertTrue(list.size()>=2);
	}
	
	/**
	 * Find movies by name
	 */
	@Test
	public void findByNameWhenPresent()
	{
		ArrayList<Movie> list =  (ArrayList<Movie>) ms.findByName("Movie One");
		Movie movie = (Movie)list.get(0);
	    assertTrue("Movie One".equals(movie.getTitle()) && movie.getId() == 1   );
	}
	
	/**
	 * Update the languages filed in the movies pojo
	 */
	@Test
	public void updateExistingElement()
	{
		ms.update(new Movie(1, "Movie One", "2015", "English French"));
		ArrayList<Movie> list =  (ArrayList<Movie>) ms.findByName("Movie One");
		Movie movie = (Movie)list.get(0);
		assertTrue("English French".equals(movie.getLanguage()));
	}
	
	/**
	 * update a element that dosen't exist
	 */
	@Test(expected=IllegalArgumentException.class)
	public void updateNonExistantElement()
	{
		ms.update(new Movie(100, "Movie One", "2015", "English French"));
	}
	
	/**
	 * Delete a movie that exists
	 */
	@Test
	public void deleteExistingMovie()
	{
		Movie movie = ms.delete(2);
		assertEquals(movie.getTitle(),"Movie Two");
	}
	
	/**
	 * Delete a movie that dosen't exists
	 */
	@Test(expected=IllegalArgumentException.class)
	public void deleteNonExistingMovie()
	{
		ms.delete(22);
	}
	
	/**
	 * Rent a new movie
	 */
	@Test
	public void rentANewMovie()
	{
		assertTrue(ms.rentMovie(1, "user a"));
	}
	
	/**
	 * rent a movie that has already been rented
	 */
	@Test(expected=IllegalArgumentException.class)
	public void rentAlreadyRentedMovie()
	{
		ms.rentMovie(1, "user a");
	}
	
	/**
	 * Rent a movie that does not exist
	 */
	@Test
	public void rentANonAccountedMovie()
	{
		assertTrue(!ms.rentMovie(22, "user a"));
	}
}

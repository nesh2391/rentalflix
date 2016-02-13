package io.egen.rentalflix.DAO.Mock;

import java.util.List;

import io.egen.rentalflix.Movie;

public interface DaoOperations {
	
	/**
	 * Thread safe insert
	 * @return 0 on success, fail code otherwise
	 */
	public int insert(Movie m);
	
	/**
	 * 
	 * @param m, object of type Movie
	 * @return 0 on success
	 */
	public int update(Movie m);
	
	/**
	 * 
	 *@param m, object of type Movie
	 *@return 0 on success
	 */
	public Movie delete(int id);
	
	/**
	 * 
	 * @param movieId, uid of movie
	 * @param renterId, the name of the renter
	 * @return
	 */
	public int rent(int movieId, String renterId);

	/**
	 * @return List
	 */
	public List<Movie> getList();

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Movie> findByName(String name);

	/**
	 * 
	 * @param movieId
	 * @param returnId
	 * @return
	 */
	int returnMovie(int movieId, String returnId);
	
	

}

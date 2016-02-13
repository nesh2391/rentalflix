package io.egen.rentalflix.DAO.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import io.egen.rentalflix.Movie;

public class MockDAO implements DaoOperations {

	private static Logger theLogger =   Logger.getLogger(MockDAO.class.getName());
	//List of movies
	private static List<Movie> movieList = new ArrayList<Movie>();
	//Movie Checkout List
	private static HashMap<Integer,String> renters = new HashMap<Integer,String>();
	
	/**
	 * get the entire inventory list 
	 */
	@Override
	public List<Movie> getList()
	{
		return movieList;
	}
	
	/**
	 * insert a new movie 
	 */
	@Override
	public synchronized int insert(Movie m) {
		if(movieList.contains(m))
			{System.out.println("Redundant insert");
				return 0;}
		movieList.add(m);
		return 0;
	}
	
	/**
	 * update a movie records 
	 */
	@Override
	public synchronized int update(Movie m) {
		if(movieList.contains(m))
		{
			Movie movie=movieList.get((movieList.indexOf(m)));
			movie.setTitle(m.getTitle());
			movie.setLanguage(m.getLanguage());
			movie.setYear(m.getYear());
			return 0;
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * find a movie by name 
	 */
	@Override
	public synchronized List<Movie> findByName(String name)
	{
		List<Movie> returnList = new ArrayList<Movie>();
		Iterator<Movie> itr = movieList.iterator();
		while(itr.hasNext())
		{
			Movie movie=itr.next();
			if(name.equals(movie.getTitle()))
				returnList.add(movie);
		}
		return returnList;
	}
	
	/**
	 * delete a movie, throws exception
	 */
	@Override
	public synchronized Movie delete(int id) {
		// TODO Auto-generated method stub
		Iterator<Movie> itr = movieList.iterator();
		while(itr.hasNext())
		{
			Movie movie=itr.next();
			if(movie.getId()==id)
			{	movieList.remove(movie);
				return movie;
			}
		}
		theLogger.info("Delete on a non existant element");
		throw new IllegalArgumentException();
	}
	
	public boolean contains(int id)
	{
		Iterator<Movie> itr = movieList.iterator();
		while(itr.hasNext())
		{
			Movie movie=itr.next();
			if(id == movie.getId())
				return true;
		}
		return false;
	}
	
	/**
	 * rent a movie using this function
	 * @return 0 for success, -2 if the movie has already been rented, -1 if the movie does not exist 
	 */
	@Override
	public  int rent(int movieId, String renterId) {
		
		if(contains(movieId))
		{
			//the movie has already been rented
			if(renters.containsKey(movieId))
			{ theLogger.info("Movie has already been rented");
				return -2;}
			else
			{ theLogger.info("adding to renters list");
				renters.put(movieId, renterId);
				return 0;}
		}
		return -1;
	}
	
	/**
	 * additional function, use for returning a movie 
	 */
	@Override
	public int returnMovie(int movieId, String returnId){
		if(renters.containsKey(movieId))
		{
			if(renters.get(movieId).equals(returnId))
			{
				renters.remove(movieId);
				return 0;
			}
		}
		return -1;
	}
	
	
	

}

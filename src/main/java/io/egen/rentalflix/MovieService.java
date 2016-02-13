package io.egen.rentalflix;

import java.util.ArrayList;
import java.util.List;

import io.egen.rentalflix.DAO.Mock.MockDAO;

/**
 * Service implementing IFlix interface
 * You can use any Java collection type to store movies
 */
public class MovieService implements IFlix {

	private MockDAO mockDAO = new MockDAO();
	
	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return mockDAO.getList();
	}

	@Override
	public List<Movie> findByName(String name) {
		// TODO Auto-generated method stub
		ArrayList<Movie> movies = (ArrayList<Movie>) mockDAO.findByName(name);
		return movies;
	}

	@Override
	public Movie create(Movie movie) {
		// TODO Auto-generated method stub
		if(mockDAO.insert(movie)==0)
			return movie;
		return null;
	}

	@Override
	public Movie update(Movie movie) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		mockDAO.update(movie);
		return movie;
		
	}

	@Override
	public Movie delete(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Movie movie = mockDAO.delete(id);
		return movie;
	}

	@Override
	public boolean rentMovie(int movieId, String user) {
		// TODO Auto-generated method stub
		int returnValue=mockDAO.rent(movieId, user);
		if(returnValue==-1)//movie does not exist 
			return false;
		else if(returnValue==-2)//movie has already been rented
			throw new IllegalArgumentException();
		else
			return true;
	}

}

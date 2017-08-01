package springSnipplets.springData;

import java.util.List;

//Movie-related functionalities to be implemented
public interface IMovieDAO {
	public Movie getMovie(int id);

	public String getStars(String title);

	public List<Movie> getMovies(String sql);

	public List<Movie> getAllMovies();

	public void insertMovie(Movie m);

	public void updateMovie(Movie m);

	public void deleteMovie(int id);

	public void deleteAllMovies();
}

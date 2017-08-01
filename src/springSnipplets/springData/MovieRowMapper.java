package springSnipplets.springData;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

// Maps a Movie Object to columns of a Resultset
public class MovieRowMapper implements RowMapper<Movie> {

	Movie movie = null;

	@Override
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		movie = new Movie();
		// data extracted from Resultset onto the Movie object
		movie.setId(rs.getInt("id"));
		movie.setTitle(rs.getString("title"));
		movie.setStars(rs.getString("stars"));
		return movie;
	}

}

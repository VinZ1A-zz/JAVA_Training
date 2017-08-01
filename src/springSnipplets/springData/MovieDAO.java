package springSnipplets.springData;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.orm.hibernate5.HibernateTemplate;

//Using JdbcTemplate, variants:
//SimpleJdbcTemplate and NamedParameterJdbcTemplate

//Illustrates
// - how to insert/update data
// - iterate over the Resultset efficiently
// - how connection data is hidden from user
// - resource pooling / exception management

//Movie Data Access Object definition
public class MovieDAO implements IMovieDAO {

	private JdbcTemplate jdbcTemplate = null;
	private HibernateTemplate hibernateTemplate = null;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemp) {
		this.hibernateTemplate = hibernateTemp;
	}

	@Override
	// Returns a single column data
	public String getStars(String title) {
		// expects comma separated list
		// String stars = getJdbcTemplate().queryForObject("select stars from
		// MOVIES where title='" + title + "'",
		// String.class);

		// better: parameterized query using a bind variable
		String stars = getJdbcTemplate().queryForObject("select stars from MOVIES where title=?",
				new Object[] { title }, String.class);
		return stars;

		// also, queryForInt, queryForList/Map ...
	}

	@Override
	// returns a Movie through a RowMapper, mapping data to class
	public Movie getMovie(int id) {
		String sql = "select * from MOVIES where id=?";
		return getJdbcTemplate().queryForObject(sql, new Object[] { id }, new MovieRowMapper());
	}

	// other way using HibernateTemplate
	public Movie getMovie_HIB(int id) {
		if (getHibernateTemplate() == null)
			System.out.println("no HibernateTemplate !");

		return getHibernateTemplate().load(Movie.class, id);
	}

	@Override
	// can return not just an object but a list of objects
	public List<Movie> getAllMovies() {
		RowMapper<Movie> mapper = new MovieRowMapper();
		String sql = "select * from MOVIES";
		return getJdbcTemplate().query(sql, new RowMapperResultSetExtractor<Movie>(mapper, 10));
	}

	@Override

	public List<Movie> getMovies(String sql) {
		return null; // could use sql as well
	}

	// using Hibernate (UNTESTED)
	public Movie findMovie(int id) {
		String sql = "from MOVIES as movies where movies.id=?";
		return (Movie) getHibernateTemplate().find(sql, id);
	}

	@Override
	public void insertMovie(Movie m) {
		String sql = "insert into MOVIES (ID, TITLE, STARS) values(?,?,?)";
		Object[] params = new Object[] { m.getId(), m.getTitle(), m.getStars() };
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(sql, params, types);
	}

	@Override
	public void deleteMovie(int id) {
		String sql = "delete FROM MOVIES where ID=?";
		Object[] params = new Object[] { id };
		getJdbcTemplate().update(sql, params);

		// or, using hibernate (UNTESTED)
		// getHibernateTemplate().delete(m);
	}

	@Override
	public void deleteAllMovies() {
		String sql = "delete FROM MOVIES";
		// could be also a stored procedure eg. "call MOVIES.DELETE_ALL_MOVIES"
		getJdbcTemplate().update(sql);
	}

	@Override
	// using Hibernate (UNTESTED)
	public void updateMovie(Movie m) {
		getHibernateTemplate().update(m); // cool

	}

}

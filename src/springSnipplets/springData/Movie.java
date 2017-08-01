package springSnipplets.springData;

//Movie Data - will be wrapped from DB data through MovieRowMapper
public class Movie {
	int id;
	String title;
	String stars;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}
}

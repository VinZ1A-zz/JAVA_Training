package springSnipplets.advanced;

// illustrates usage of properties in XML config file
public class JobSearchAgent {
	// to be loaded from a key/value list
	// eg. job.location - /users/zedude/dev/js;
	// job.type = permanent;
	// in ch2-spring-beans.properties for instance
	private String location = null;
	private String type = null;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

package springSnipplets.springJMS;

import java.io.Serializable;

public class Trade implements Serializable {
	String someProp = "DEFAULT";

	public Trade(String iName) {
		someProp = iName;
	}

	public String getSomeProp() {
		return someProp;
	}

	public void setSomeProp(String someProp) {
		this.someProp = someProp;
	}
}

package springSnipplets.advanced;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CapitalCitiesManager {
	private List<String> countriesList = null;
	private Map<String, String> countryCapitalMap = null;
	private Properties countryProperties = null; // name-value pairs

	public List<String> getCountriesList() {
		return countriesList;
	}

	public void setCountriesList(List<String> countriesList) {
		this.countriesList = countriesList;
	}

	public Map<String, String> getCountryCapitalMap() {
		return countryCapitalMap;
	}

	public void setCountryCapitalMap(Map<String, String> countryCapitalMap) {
		this.countryCapitalMap = countryCapitalMap;
	}

	@Override
	public String toString() {
		return "list: " + countriesList.toString() //
				+ "; capitals: " + countryCapitalMap.toString() //
				+ "; props: " + countryProperties.toString();
	}

	public Properties getCountryProperties() {
		return countryProperties;
	}

	public void setCountryProperties(Properties countryProperties) {
		this.countryProperties = countryProperties;
	}
}

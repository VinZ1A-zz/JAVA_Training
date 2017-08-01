package springSnipplets.fundamentals;

import java.util.HashMap;
import java.util.Map;

//a dummy example ;)
public class FxRateProvider {
	private double rate = 0.0;
	private String baseCurrency = "USD";
	private Map<String, Double> currencies = null;

	/* Invoked via Spring's init-method callback */
	public void initMe() {
		currencies = new HashMap<String, Double>();
		currencies.put("GBP", 1.5);
		currencies.put("USD", 1.0);
		currencies.put("JPY", 1000.00);
		System.out.println("FxRateProvider initialized!");
	}

	// invoked when destroying bean
	public void destroyMe() {
		// do cleanup opes here;
		currencies = null;
		System.out.println("FxRateProvider destroyed!"); // could not see it?
	}

	public double getRate(String currency) {
		if (!currencies.containsKey(currency))
			return 0;
		return currencies.get(currency);
	}

	public void setBaseCurrency(String iCur) {
		baseCurrency = iCur;
	}
}

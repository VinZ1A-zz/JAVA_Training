package springSnipplets.autoWiring;

//illustrates autowiring by name
public class TradeReceiver {
	private TradePersistor tradePersistor = null;
	private TradeTransformer tradeTransformer = null;
	private TradeSolo tradeSolo = null;

	// proof that autowiring by Name works
	public void doSmthg() {
		// Expects to see 4 / 2
		System.out.println("inTradeReceiver : Pers" + tradePersistor.a + //
				" ; Trans" + tradeTransformer.a + //
				" ; Solo" + tradeSolo.a);
	}

	public void setTradePersistor(TradePersistor obj) {
		tradePersistor = obj;
	}

	public void setTradeTransformer(TradeTransformer obj) {
		tradeTransformer = obj;
	}

	public void setTradeSolo(TradeSolo obj) {
		tradeSolo = obj;
	}

	// Setters needed as usual to auto-Wire attributes
	public static class TradePersistor {
		int a = 2;

		// illustrate Autowiring by constructor
		// SomeDude is instanciated by Framework
		public TradePersistor(SomeDude dude) {
			a = dude.name.length();
		}
	}

	public static class TradeTransformer {
		int a = 1;

		public TradeTransformer() {
			a = 3;
		}
	}

	public static class TradeSolo {
		int a = 0;

		public TradeSolo() {
			a = 2;
		}
	}

	public static class SomeDude {
		String name = "bloke";

		public SomeDude() {
			name = "dude";
		}
	}
}

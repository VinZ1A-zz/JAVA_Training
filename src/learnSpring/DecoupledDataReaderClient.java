package learnSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DecoupledDataReaderClient {
	// Could be better: To decouple Client and IReader, use a service layer in
	// the middle (Service would be glued to Client instead)
	// private IReader reader = null; // not great design

	private ReaderService service = null; // better design
	private ApplicationContext ctx = null;

	// read bean config from XML
	public DecoupledDataReaderClient() {
		ctx = new ClassPathXmlApplicationContext("learnSpring/basics-reader-beans.xml");
		service = (ReaderService) ctx.getBean("readerService"); // when using
																// Service
	}

	private String fetchData() {
		// get 'reader' bean (note: absence of new())
		// not great design (coupling client and Reader)
		// reader = (IReader) ctx.getBean("reader");
		// return reader.read();

		// use a service layer instead:
		return service.fetchData();
	}

	public static void main(String[] args) {
		DecoupledDataReaderClient client = new DecoupledDataReaderClient();
		System.out.println("Fetch Data using Spring config : " + client.fetchData());
	}
}

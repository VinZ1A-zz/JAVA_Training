package springSnipplets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import springSnipplets.advanced.CapitalCitiesManager;
import springSnipplets.advanced.InstanceCounter;
import springSnipplets.advanced.InstanceCounter.PrototypeInstanceCounter;
import springSnipplets.advanced.JobSearchAgent;
import springSnipplets.advanced.PostProcessComponent;
import springSnipplets.autoWiring.TradeReceiver;
import springSnipplets.fundamentals.ContextStartedEventListener;
import springSnipplets.fundamentals.EmployeeFactory;
import springSnipplets.fundamentals.FxRateProvider;
import springSnipplets.fundamentals.HugeFileEventPublisher;
import springSnipplets.fundamentals.ReaderService;
import springSnipplets.fundamentals.SpecialEmployee;

public class DecoupledDataReaderClient {
	// Could be better: To decouple Client and IReader, use a service layer in
	// the middle (Service would be glued to Client instead)
	// private IReader reader = null; // not great design

	private ReaderService service = null; // better design
	private ApplicationContext ctx = null;

	// read bean config from XML
	public DecoupledDataReaderClient() {
		ctx = new ClassPathXmlApplicationContext("springSnipplets/basics-reader-beans.xml");
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

		// basic/dirty instanciation of FxRateProvider - using relative class
		// location
		// note: constructor could be a String[] as well
		ApplicationContext ctxFX = new ClassPathXmlApplicationContext("springSnipplets/ch2-spring-beans.xml");
		FxRateProvider rateService = (FxRateProvider) ctxFX
				.getBean("fxRateProvider"); /*
											 * , FxRateProvider.class as
											 * optional second arg
											 */

		// other way of instantiating a bean - using File system location
		AbstractApplicationContext ctxFile = new FileSystemXmlApplicationContext(
				"src/springSnipplets/ch2-spring-beans.xml");
		FxRateProvider rateService2 = (FxRateProvider) ctxFile.getBean("fxRateProvider");

		// can also be used (Java web aps & Spring MVC) :
		// WebXmlApplicationContext

		// singleton instanciation
		EmployeeFactory empFactory = (EmployeeFactory) ctxFile.getBean("employeeFactory");

		// forcing a bean to have a init method, programmatically
		SpecialEmployee specEmp = (SpecialEmployee) ctxFile.getBean("specialEmployee");

		// illustrating events
		ContextStartedEventListener contextStartEventListener = (ContextStartedEventListener) ctxFile
				.getBean("contextStartEventListener");
		// starts the context
		((AbstractApplicationContext) ctxFX).start();
		// can also trigger another ApplicationListener on ContextRefreshedEvent
		// with refresh()
		// ... ContextStoppedEvent with stop(); ect...
		// custom events :
		// eg. publish an event whenever a file > 100Mb is received
		// need: Publisher, Listener, Event and Client
		HugeFileEventPublisher hugeFileEventPublisher = (HugeFileEventPublisher) ctxFile
				.getBean("hugeFileEventPublisher");
		// triggers the HugeFileEvent event, which will be sent in the context,
		// then received by the listener : HugeFileEventListener ('Received
		// event' in logs)
		// !! Event model is single-threaded (SYNCHRONOUS by nature)
		// eg. process is blocked until all listeners have processed their task
		// not recommended to use (use instead EventBus, Elf (Event LIstener
		// Framework, or Spring's Integration)
		hugeFileEventPublisher.publish("some-huge-file.txt");

		// ----- autowire examples -----
		TradeReceiver tradeReceiver = (TradeReceiver) ctxFile.getBean("tradeReceiver");
		tradeReceiver.doSmthg(); // --> not working ??

		// ----- singleton vs prototype scopes
		InstanceCounter instanceCnt = (InstanceCounter) ctxFile.getBean("instanceCounter");
		// gets 2 (because it's one singleton PER context/container!)
		System.out.println("nb of InstanceCounter instances " + instanceCnt.instanceCount);
		InstanceCounter instanceCnt2 = (InstanceCounter) ctxFile.getBean("instanceCounter");
		// still 2 ([almost a] singleton)
		System.out.println("new nb of InstanceCounter instances " + instanceCnt.instanceCount);
		// ****** prototype *******
		PrototypeInstanceCounter protoInstanceCnt = (PrototypeInstanceCounter) ctxFile
				.getBean("prototypeInstanceCounter");
		// gets 1 (built upon request only)
		System.out.println("nb of PrototypeInstanceCounter instances " + protoInstanceCnt.instanceCount);
		PrototypeInstanceCounter protoInstanceCnt2 = (PrototypeInstanceCounter) ctxFile
				.getBean("prototypeInstanceCounter");
		// now incremented to 2 (new prototype built)
		System.out.println("new nb of PrototypeInstanceCounter instances " + protoInstanceCnt2.instanceCount);

		// can read properties outside of the XML config file using key/value
		// pairs property file
		JobSearchAgent jobSearchAgent = (JobSearchAgent) ctxFile.getBean("jobSearchAgent");
		System.out.println("jobSearchAgent instanciated with location = " + jobSearchAgent.getLocation());

		// property editors : allows the injection of non-primitive data (List,
		// Setp, Map...)
		// java.util.list or java.util.set(in this case, will remove duplicates
		// if any)
		CapitalCitiesManager capitalCitiesManager = (CapitalCitiesManager) ctxFile.getBean("capitalCitiesManager");
		System.out.println("capitalCitiesManager = " + capitalCitiesManager);

		// testing Bean Post processor mechanism
		PostProcessComponent postProcessComponent = (PostProcessComponent) ctxFile.getBean("postProcessComponent");
		postProcessComponent.sayHi();

		// close context (no need to cast since using AbstractApplicationContext
		// instead of ApplicationContext interface)
		// this calls all the destroyers of the beans (DisposableBean instances
		// and beans with destroy-method attribute)
		ctxFile.close(); // no need to cast

	}
}

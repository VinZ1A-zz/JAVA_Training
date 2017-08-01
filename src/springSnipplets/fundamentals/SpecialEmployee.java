package springSnipplets.fundamentals;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//init/destroy callbacks : three ways:
// Annotation based		@PostConstruct and @PreDestroy
// Programming based	implements InitializingBean, DisposableBean
// Config based 		init-method & destroy-method in XML

//not as great: coupling the class with a third-party (Spring) dependency
//limits possibilty of later migration (Google Guice?)
// Avoid Vendor lockins!
public class SpecialEmployee extends Employee implements InitializingBean, DisposableBean {

	String otherAttribute = null;

	// enforces the usage of init-method / destroy-method in code
	@Override
	// Ensures initialization is done for the beam, programmatically
	// after all properties are set
	// allows extra dynamic setup / initialize / reset
	public void afterPropertiesSet() throws Exception {
		System.out.println("LazyEmployee::afterPropertiesSet called");
		// DO SMTHG, here: re-set the value set up in XML
		otherAttribute = "CHARACTERISTIC";
	}

	@Override
	// to be called whenever the Spring container shuts down
	public void destroy() throws Exception {
		System.out.println("LazyEmployee::destroy called");
		// do cleanup here
	}

	// note: can also use @PostConstruct and @PreDestroy annotations

	public void setOtherAttribute(String in) {
		System.out.println("LazyEmployee::setOtherAttribute called");
		otherAttribute = in;
	}

}

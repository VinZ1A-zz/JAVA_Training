package learnSpring;

import learnSpring.withoutSpring.DataReaderClient;

//NEXT : Autowiring page 41 

public class Sandbox {

	public static void main(String... str) {
		System.out.println("in learnSpring");

		// Without Spring - read data from various sources
		// Exp: from file using an "enhanced" design (interface)
		DataReaderClient.main(str);

		// 2. with Spring? using Dependency Injection (aka. Inversion of
		// Control)
	}

}

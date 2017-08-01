package springSnipplets;

import springSnipplets.withoutSpring.DataReaderClient;

public class Sandbox {

	public static void main(String... str) {
		System.out.println("in springSnipplets");

		// Without Spring - read data from various sources
		// Exp: from file using an "enhanced" design (interface)
		DataReaderClient.main(str);

		// 2. with Spring? using Dependency Injection (aka. Inversion of
		// Control)
	}

}

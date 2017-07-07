package learnSpring;

import learnSpring.withoutSpring.IReader;

public class ReaderService {
	// Service coupled to IReader, not the client
	private IReader reader = null;

	public ReaderService(IReader reader) {
		this.reader = reader;
	}

	public String fetchData() {
		// delegates call to implementation
		return reader.read();
	}
}

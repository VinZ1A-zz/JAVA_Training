package springSnipplets.advanced;

public class InstanceCounter {
	static public int instanceCount = 0;

	public InstanceCounter() {
		instanceCount++;
		System.out.println("incrementing nb of instances of InstanceCounter to " + instanceCount);
	}

	public static class PrototypeInstanceCounter {
		static public int instanceCount = 0;

		public PrototypeInstanceCounter() {
			instanceCount++;
			System.out.println("incrementing nb of instances of PrototypeInstanceCounter to " + instanceCount);
		}
	}
}

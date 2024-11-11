package sigleton;

public class ClaseSingleton {

	private static ClaseSingleton instance = null;
	
	private ClaseSingleton() {
		
	}
	
	public static ClaseSingleton getInstance() {
		if(instance == null) {
			instance = new ClaseSingleton();
		}
		return instance;
	}
}

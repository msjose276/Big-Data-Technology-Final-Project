package cs523.SparkWC;

public class Helper {

	public static void printThreadSafeError(String message) {
        synchronized (System.out) {
            System.err.println(message);
        }
	 }
	
	public static void printThreadSafeOut(String message) {
        synchronized (System.out) {
            System.out.println(message);
        }
	 }
}

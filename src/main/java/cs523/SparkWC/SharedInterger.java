package cs523.SparkWC;

public class SharedInterger {
	private int value;

    public SharedInterger(int initialValue) {
        this.value = initialValue;
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int newValue) {
        this.value = newValue;
    }

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }
}

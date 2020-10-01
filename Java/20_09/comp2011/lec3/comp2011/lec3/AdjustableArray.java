package comp2011.lec3;

public class AdjustableArray {
	private static final int INITIAL = 32;
	private int[] data;
	private int size;
	
	public AdjustableArray() {
		data = new int[INITIAL];
		size = 0;
	}
	
	public void add(int  a) {
		if (size == data.length) {
			int [] old = data;

			int newSize = size * 3 / 2;

			data = new int[newSize];
			for (int i = 0; i < size; i++)
				data[i] = old[i];
		}
		data[size++] = a; 
	}
	public String toString() {
		return new String(data, 0, size);
	}
	
	public static void main(String[] args) {
		AdjustableArray aa = new AdjustableArray();
		int n = 32768;  // n in general.
		for(int i = 0; i < n; i++)
			aa.add(i);
		System.out.print(aa);
	}
}
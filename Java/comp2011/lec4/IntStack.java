package comp2011.lec4;

/**
 * 
 * @author yixin cao (September 26, 2020)
 *
 * A stack for integers. Note that initial index of top is 0.
 */
public class IntStack {
    private static final int CAPACITY=128;
	int[] data;
	int top;

    public IntStack() {
        this(CAPACITY);
    }
    
    public IntStack(int size){
        top = 0;
        data = new int[size];
    }
    
	public boolean isEmpty()  {
		return top == 0;
	}
	
	public void push(int e) {
		if (top == CAPACITY) {
		    // throw an exception.
		    System.out.println("overflow");
		    return;
		}
		data[top++] = e;	
	}

	public int pop() {
        if (top == 0) {
            // throw an exception.
            System.out.println("downflow");
            return -1;
        }
		return data[--top];
	}
}

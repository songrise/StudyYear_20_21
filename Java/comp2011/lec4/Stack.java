package comp2011.lec4;

/**
 * 
 * @author yixin cao (September 26, 2020)
 *
 *         The stack implementation with generics.
 */
public class Stack<T> {
	private static final int CAPACITY = 128;
	private Object[] data;
	private int top;

	public Stack() {
		this(CAPACITY);
	}

	public Stack(int size) {
		top = -1;
		data = new Object[size];
	}

	public void push(T element) {
		if (top == data.length - 1) {
			System.out.println("overflow");
			return;
		}
		data[++top] = element;
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		if (isEmpty()) {
			System.out.println("underflow");
			return null;
		}
		return (T) data[top--];
	}

	@SuppressWarnings("unchecked")
	public T peek() {
		return (T) data[top];
	}

	public boolean isEmpty() {
		return top < 0;
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		int n = 100;
		while (n > 0) {
			stack.push(n % 2);
			n /= 2;
		}
		while (!stack.isEmpty())
			System.out.print(stack.pop());
	}
}

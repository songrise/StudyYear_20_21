package comp2011.lec4;

/**
 * 
 * @author yixin cao (September 26, 2020)
 *
 * To be finished.
 *
 * This implementation doesn't use generics, because it needs to compare elements.
 * To know about this, please check the API for java.util.Comparator.
 * 
 * The Java implementation of Priority Queues can be found at java.util.PriorityQueue.
 */
public class PriorityQueue {
    private static final int CAPACITY = 32;
    private int[] data;
    private int top;

    public PriorityQueue() {
        this(CAPACITY);
    }

    public PriorityQueue(int size){
        data = new int[size];
        top = -1;
    }
    
    public void add(int item){
        
    }
    
    public int pop(){
        return -1;
    }

    public boolean isEmpty(){
        return false;
    }

    public int peek(){
        if(isEmpty()) { System.out.println("Oops..."); return ' ';}
        return -1;
    }

    public String toString(){
        return "";
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

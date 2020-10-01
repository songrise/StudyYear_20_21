package comp2011.lec3;

class CharStack {
    private static final int CAPACITY = 128;
    private char[] data;
    private int top;
    
    public CharStack() {
        this(CAPACITY);
    }
    
    public char peek(){
        if(isEmpty()) { System.out.println("Oops..."); return ' ';}
        return data[top];
    }

    public CharStack(int size){
        top = -1;
        data = new char[size];
    }
    
    public void push(char c){
        data[++top] = c;
    }

    char pop(){
        if(isEmpty()) { System.out.println("Oops..."); return ' ';}
        // return data[top--];
        char c = data[top];
        top--;
        return c;
    }

    public boolean isEmpty(){
        return top < 0;
    }

    public String toString(){
        String s = new String(data).substring(0,top+1);
        return s;
    }

}

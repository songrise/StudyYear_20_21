package comp2011.lec11;

import java.util.Objects;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * Demonstration of {\code String.hashCode()}, and some pitfalls of programming (not limited to Java).
 * 
 */
public class HashCodeTester {
    class Record {
        String name = "Peppa";
        int age = 7;
        char gender = 'F';
        
        // (31 * name.hashCode() + Integer.valueOf(age).hashCode() ) * 31 + ... 
        @Override public int hashCode() {
            return Objects.hash(name, age, gender);
        }
    }
    
    public static void main(String[] args) {
        HashCodeTester o = new HashCodeTester();
        // since we haven't implemented one here, it will call the default one provided by Object
        System.out.println(o.hashCode());
        // this translate the hash code into hexadecimal format.
        System.out.println(String.format("0x%08X", o.hashCode()));        
        System.out.println(o); // return its reference (address).
        
     	String s = "Hong Kong";
    	System.out.println(s.hashCode());
    	System.out.println("Hong Kong".hashCode());
    	int h = "polygenelubricants".hashCode();
    	System.out.println(h);
    	System.out.println(Math.abs(h));
//    	int[] a = {81, 686, 873, 317, 919, 378, 746, 779, 128, 997, 383};
//    	for (int i = 0; i < a.length; i ++) System.out.println(a[i]% 17);
    }
    
}

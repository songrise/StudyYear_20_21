
public class TheClass {
    public static void main(String[] args) throws Exception {
        String str = new String();
        Class c1 = str.getClass();
        System.out.println(c1);

        Object obj = c1.getConstructor().newInstance();
        String className = "java.util.Random";
        Class c2 = Class.forName(className);
        Class c3 = String.class;
    }
}

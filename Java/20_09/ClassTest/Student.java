package ClassTest;

public class Student extends Person {
    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    public void getDescription() {
        System.out.printf("The students name is: %s, his major is %s", this.getName(), this.major);
    }

    public static void main(String[] args) {
        // ok to declare be a abstract obj, but must be newed by subclass.
        Person s1 = new Student("Alex", "Comp");
        s1.getDescription();

    }
}

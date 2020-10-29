package Inheritance;

class Person {
    static void describe() {
        System.out.println("This is person class");
    }

    private void introduce() {
        System.out.println("I am a human");
    }
}

public class Student extends Person {
    static void describe() {
        System.out.println("This is student class");
    }

    private void introduce() {
        System.out.println("I am a student");
    }

    public static void main(String[] args) {
        Person a = new Person();
    }
}

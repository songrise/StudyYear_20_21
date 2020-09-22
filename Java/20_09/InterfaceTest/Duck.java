package InterfaceTest;

import java.io.Serializable;

public class Duck implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Duck() {
        this.name = "";
        this.age = 0;
    }

    public Duck(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show_info() {
        System.out.printf("Duck " + this.name + ", " + this.age + " years old.\n");
    }

    @Override
    public String toString() {
        return this.name + "--" + Integer.toString(this.age);
    }

}

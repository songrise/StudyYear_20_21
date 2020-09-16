package ClassTest;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : StuffTest.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-14
 * @Github ：https://github.com/songrise
 * @Descriptions: Test a Stuff class. Based on textbook..
 **/

public class Stuff extends Person {
    // id for the class, declared to be static
    static int id = 1;
    private int stuffId;
    private double salary;

    public Stuff() {
        super("");
        this.salary = 0;
        this.setStuffId();
    }

    public Stuff(String name) {
        super(name);
        this.setStuffId();

    }

    public Stuff(String name, double salary) {
        super(name);
        this.salary = salary;
        this.setStuffId();
    }

    double getSalary() {
        return this.salary;
    }

    private int getStuffId() {
        return this.stuffId;
    }

    private void setStuffId() {
        this.stuffId = id++;
    }

    boolean changeSalary(double newsalary) {
        this.salary = newsalary;
        return true;
    }

    public void getDescription() {
        System.out.printf("Id: %d | Name: %s salary: $%.2f\n", this.getStuffId(), getName(), this.getSalary());
    }

}

class StuffTest {
    public static void main(String[] args) {
        Stuff Tom = new Stuff("Tom", 400);
        Tom.getDescription();
        Tom.changeSalary(1000d);
        Tom.getDescription();
        Stuff Amy = new Stuff("Amy");
        Amy.getDescription();
    }
}

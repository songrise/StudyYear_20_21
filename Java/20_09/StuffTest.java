
/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : StuffTest.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-14
 * @Github ：https://github.com/songrise
 * @Descriptions: Test a Stuff class. Based on textbook..
 **/

class Stuff {
    // id for the class, declared to be static
    static int id = 1;
    private int stuffId;
    final private String name;
    private double income;

    public Stuff() {
        this.name = "";
        this.income = 0;
        this.setStuffId();
    }

    public Stuff(String name) {
        this.name = name;
        this.setStuffId();

    }

    public Stuff(String name, double income) {
        this.name = name;
        this.income = income;
        this.setStuffId();
    }

    private String getName() {
        return this.name;
    }

    private double getIncome() {
        return this.income;
    }

    private int getStuffId() {
        return this.stuffId;
    }

    private void setStuffId() {
        this.stuffId = id++;
    }

    boolean changeIncome(double newIncome) {
        this.income = newIncome;
        return true;
    }

    void showInfo() {
        System.out.printf("Id: %d | Name: %s Income: $%.2f\n", this.getStuffId(), getName(), this.getIncome());
    }

}

public class StuffTest {
    public static void main(String[] args) {
        Stuff Tom = new Stuff("Tom", 400);
        Tom.showInfo();
        Tom.changeIncome(1000d);
        Tom.showInfo();
        Stuff Amy = new Stuff("Amy");
        Amy.showInfo();
    }
}

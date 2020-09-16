package ClassTest;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : Manager.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-17
 * @Github ：https://github.com/songrise
 * @Descriptions: Test inheritance
 **/

public class Manager extends Stuff {
    private double bouns = 0d;

    void setBouns(double val) {
        this.bouns = val;
    }

    final double getSalary() {
        return super.getSalary() + this.bouns;
    }

    public static void main(String[] args) {
        Manager a = new Manager();
        a.setBouns(100d);
        a.getDescription();
    }
}

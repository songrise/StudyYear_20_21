package ClassTest;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : Person.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-17
 * @Github ：https://github.com/songrise
 * @Descriptions: To test abstract classes.
 **/

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    // I think it is kind of like a function prototype in C.
    public abstract void getDescription();

    public String getName() {
        return this.name;
    }

}

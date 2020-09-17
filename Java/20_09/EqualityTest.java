
/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : EqualityTest.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-17
 * @Github ：https://github.com/songrise
 * @Descriptions: This is to test equal method for a object.
 **/

class Myobj {
    public int i = 0;

    Myobj(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (this == obj) {
            return true;
        }
        if (this == null) {
            return false;
        }

        if (!(obj instanceof Myobj)) {
            return false;
        }

        Myobj o = (Myobj) obj;// a cast is needed
        return this.i == o.i;
    }
}

public class EqualityTest extends Object {

    public static void main(String[] args) {
        Myobj a = new Myobj(2);
        Myobj b = new Myobj(2);
        Myobj c = a;
        System.out.println(a.equals(b) ? "a euqals b" : "a !euqals b");
        System.out.println(a == b ? "a == b" : "a != b");

        System.out.println(a.equals(c) ? "a equals c" : "a !equals c");
        System.out.println(a == c ? "a == c" : "a != c");

        System.out.println(c.getClass());

    }

}

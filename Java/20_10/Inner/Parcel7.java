/**
* -*- coding : utf-8 -*-
* @FileName  : Parcel7.java
* @Author    : Ruixiang JIANG (Songrise)
* @Time      : 2020-10-05
* @Github    ：https://github.com/songrise
* @Descriptions: Test anonymous inner class, codes are derived from Thinking in Java
**/

package Inner;

class Contents {
    private int i = 11;

    public int value() {
        return i;
    }

}

public class Parcel7 {
    public Contents contents() {
        return new Contents() {
            private int i = 7;

            public int value() {
                return i;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value());
    }
}

package InterfaceTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : testSerializable.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-22
 * @Github ：https://github.com/songrise
 * @Descriptions: This is to test Serializable interface and its usage.
 *                https://blog.csdn.net/u014750606/article/details/80040130 It
 *                may also be used to deep copy an object. I shall test it later
 *                and use it to do the project.
 **/

public class testSerializable {
    public static void main(String[] args) {
        Duck d = new Duck("BD Duck", 3);
        System.out.println("Serialize " + d.toString());
        try {
            FileOutputStream fos = new FileOutputStream(".\\DuckObj.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(d);
            oos.flush();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

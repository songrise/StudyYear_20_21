package InterfaceTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class testDeSerialize {
    public static void main(String[] args) {
        Duck d;
        try {
            FileInputStream fis = new FileInputStream("DuckObj.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            d = (Duck) ois.readObject();
            ois.close();
            System.out.println(d.toString());
            d.show_info();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

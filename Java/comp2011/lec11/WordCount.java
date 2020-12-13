package comp2011.lec11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * A stub for visiting a webpage and parsing its words.
 * You can play with word counting with these codes.
 * 
 */
public class WordCount {

    public static void main(String[] args) {
        try {
            /*
            URL url = new URL("https://www.comp.polyu.edu.hk/~csycao/news");
            //URLConnection conn = url.openConnection();
            InputStream is = url.openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            */

            String url = "https://en.wikipedia.org/wiki/Hash_function";
            Scanner sc = new Scanner(new URL(url).openStream()).useDelimiter("[^a-zA-Z]+");
//            System.out.print("s " + sc.hasNext());
//            System.out.print(sc.next());
            while (sc.hasNext()) {
                String word = sc.next();
                System.out.println(word);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}





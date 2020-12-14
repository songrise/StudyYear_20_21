package comp2011.lec11;

import java.util.*;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * Some examples using java.util.HashMap.
 * {@code color} stores the mapping from color names to their RGB codes, and
 * {@code dns} stores the mapping from domain names to their IP addresses.
 * 
 * Information on Internet Protocol version 4: https://en.wikipedia.org/wiki/IPv4
 * and the Domain Name System (DNS): https://en.wikipedia.org/wiki/Domain_Name_System
 * 
 * Information on the RGB color model: https://en.wikipedia.org/wiki/RGB_color_model  
 * 
 */    
public class MapExamples {
    private static class IP {
        int[] address;
        public IP (int a, int b, int c, int d) {
            address = new int[4];
            address[0] = a;
            address[1] = b;
            address[2] = c;
            address[3] = d;
        }
        public String toString() {
            return new StringBuilder().append(address[0]).append('.').
                    append(address[1]).append('.').append(address[2]).
                    append('.').append(address[3]).toString();
        }
    }
    private static class Color{
        int r, g, b;
        public Color (int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
        public String toString() {
            return new StringBuilder().append('(').append(r).append(',').
                    append(g).append(',').append(b).append(')').toString();
        }
    }
    
    private static void color( ) {
        Map<String, Color> map = new HashMap<String, Color>();
        map.put("Aquamarine", new Color(0x7F, 0xFF, 0xD0));
        map.put("Black", new Color(0, 0, 0));
        map.put("white", new Color(255, 255, 255));
        map.put("Cardinal", new Color(0xC4, 0x1E, 0x3A));
        map.put("Cyan", new Color(0x00, 0xFF, 0xFF));
        map.put("Dark blue", new Color(0x00, 0x00, 0x8B));
        map.put("Forest green", new Color(0x01, 0x44, 0x21));
        map.put("Magenta", new Color(0xFF, 0x00, 0xFF));
        map.put("Maroon", new Color(0x80, 0x00, 0x00));
        // https://www.polyu.edu.hk/web/en/about_polyu/university_identity/
        map.put("PolyU Red", new Color(160, 35, 55));
        map.put("PolyU Grey", new Color(88, 89, 91));
        map.put("Royal blue", new Color(0x00, 0x23, 0x66));
        map.put("PolyU Red", new Color(0x00, 0x23, 0x60));
        map.put("Silver", new Color(0xC0, 0xC0, 0xC0));
        map.put("Turquoise", new Color(0x30, 0xD5, 0xC8));
        map.put("Violet", new Color(0x8F, 0x00, 0xFF));
        
        System.out.println("The color code of PolyU Red is " + map.get("PolyU Red"));
    }
    
    private static void dns( ) {
        Map<String, IP> map = new HashMap<String, IP>();
        map.put("www.polyu.edu.hk", new IP(158, 132, 48, 76));
        map.put("www.comp.polyu.edu.hk", new IP(158, 132, 20, 226));
        map.put("www.google.com", new IP(172, 217, 24, 68));
        map.put("www.youtube.com", new IP(216, 58, 200, 78));
        map.put("www.facebook.com", new IP(31, 13, 77, 35));
        map.put("www.amazon.com", new IP(13, 224, 154, 9));
        map.put("www.google.com.hk", new IP(216, 58, 199, 3));
        map.put("www.wikipedia.org", new IP(103, 102, 166, 224));
        map.put("www.zoom.us", new IP(3, 235, 71, 135));
        map.put("www.hktvmall.com", new IP(14, 198, 244, 42));
        map.put("www.cti.hk", new IP(14, 198, 244, 42));
        map.put("www.whatsapp.com", new IP(31, 13, 77, 60));

        System.out.println("The IP address of wikipedia is " + map.get("www.wikipedia.org"));
    }
    
    public static void main(String[] args) {
        dns();
        color();
    }

}

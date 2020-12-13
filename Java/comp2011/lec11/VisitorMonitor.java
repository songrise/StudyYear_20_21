package comp2011.lec11;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * A toy model of keeping track of the visitor of a website.
 * 
 * To avoid too much details, we use (4-byte) integers as IPv4 addresses.
 * 
 * Java provides facilities for manipulating IP addresses: java.net.InetAddress.  
 * 
 */
public class VisitorMonitor {
    /**
     * This method returns the last 16 bits of the {\code ip}.
     * Or, c.d of the four parts a.b.c.d.
     * 
     * WARNING: in Java, % may return a negative number,
     * so we change the first bit to be 0 (by & 0x7FFFFFFF) 
     * before the modulo operation (% 0x10000).
     */
	public static int h(int ip) {
		return (ip & 0x7FFFFFFF) % 0x10000;  // -1 % 4 = -1?        1 0000 0000 0000 0000  
		// return (ip >> 8 & 0x7FFFFFFF) % 0x10000; // it takes b.c
	}
	
	public static String display(int ip) {
		StringBuilder sb = new StringBuilder();
		for (int i = 24; i >= 0; i-=8) {
			int x = (ip >> i & 0x7FFFFFFF) % 0x100;
			sb.append(x).append('.');
		}
		return sb.substring(0, sb.length()-1);
	}

	/*
 
 	public static InetAddress newIP() {
		SecureRandom random = new SecureRandom();
		byte[] ip = new byte[4];
		for (int i = 0; i < 4; i++) 
			ip[i] = (byte) random.nextInt(256);
		try {
			return InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int h(InetAddress ip) {
		byte[] b = ip.getAddress();
		return ( ((b[1]  & 0xff) << 8) + (b[2] & 0xff) ) & 0x7FFFFFFF;
	}
	*/
	
	public static void main(String[] args) {
		int size = 10000;
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < size; i++) {
			int ip = random.nextInt();
			System.out.println(display(ip) + " in the box " + h(ip));
		}
		// System.out.println(String.format("0x%08X", (0xABCDEF11 & 0x7FFFFFFF) % 0x10000));
		// 1010 1011 1100 1101 1110 1111 0001 0001 & 0111 1111 1111 1111 1111 1111 1111 1111
		
	    /*
	     * Uncomment this and the two methods above to test.
	     int size = 10000;
		for (int i = 0; i < size; i++) {
			InetAddress ip = newIP();
			System.out.println(ip + " in the box " + h(ip));
			System.out.println(ip.getHostName());
		}
         */
	}
}
	
package comp2011.lec11;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * A way of removing duplicate that does *NOT* work in general.
 * 
 */
public class DuplicateRemover {
	static boolean duplicateChar(String s) {
		// Java is based on Unicode. 
		// If you want to test only ASCII characters, 
		// please change 65536 to 256. 		
		boolean[] b = new boolean[65536];
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			if (b[c]) return true;
			else b[c] = true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(duplicateChar("Hong Kong"));
		System.out.println(duplicateChar("America"));
		System.out.println(duplicateChar("polytechnic"));
		System.out.println(duplicateChar("56050520"));
		System.out.println(duplicateChar("香港\u9999") + "; 香港\u9999");
		
		String a = "call me at your earliest convenience";
        System.out.println(a.hashCode());
	}

}

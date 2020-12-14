package comp2011.lec11;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 * Demonstration of hashing function % p for different prime numbers p.
 * 
 */
public class Subjects {
	public static void main(String args[]){
	    mod97();
	    int[] a = {1001, 1003, 1011, 2011, 2018, 2021, 2411, 2421, 2426};
	    int[] b = {17, 19, 23};
	    for (int M:b) {
	        System.out.println("\n M = " + M + ": ");	        
	        //for (int i:a) System.out.print(i % M + ", ");
	        Arrays.stream(a).mapToObj(i -> i % M).forEach(System.out::println);
	        //Arrays.stream(a).mapToObj(i -> i % M).sorted().forEach(System.out::println);
	    }	    
	    
		Hashtable<String, String> courses = new Hashtable<String,String>();  
		  
		courses.put("COMP1001", "PROBLEM SOLVING METHODOLOGY IN INFORMATION TECHNOLOGY");   
		courses.put("COMP2011", "DATA STRUCTURES");
		courses.put("COMP2021", "OBJECT-ORIENTED PROGRAMMING");
		courses.put("COMP2121", "E-BUSINESS");
		courses.put("COMP2411", "DATABASE SYSTEMS");
		courses.put("COMP2421", "COMPUTER ORGANIZATION");
		courses.put("COMP3011", "DESIGN AND ANALYSIS OF ALGORITHMS");
		courses.put("COMP305",  "DATA STRUCTURES AND ALGORITHMS");
		courses.put("COMP312",  "COMPUTER COMMUNICATIONS NETWORKS");
		courses.put("COMP3122", "INFORMATION SYSTEMS DEVELOPMENT");
		courses.put("COMP3134", "BUSINESS INTELLIGENCE AND CUSTOMER RELATIONSHIP MANAGEMENT");
		courses.put("COMP3134", "BUSINESS INTELLIGENCE AND CUSTOMER RELATIONSHIP MANAGEMENT ");
		courses.put("COMP320",  "INTRODUCTION TO INTERNET COMPUTING");
		courses.put("COMP3211", "SOFTWARE ENGINEERING");
		courses.put("COMP3211", "SOFTWARE ENGINEERING");
		courses.put("COMP3235", "SOFTWARE PROJECT MANAGEMENT");
		courses.put("COMP3334", "COMPUTER SYSTEMS SECURITY");
		courses.put("COMP3335", "DATABASE SECURITY");
		courses.put("COMP3421", "WEB APPLICATION DESIGN AND DEVELOPMENT");
		courses.put("COMP3421", "WEB APPLICATION DESIGN AND DEVELOPMENT");
		courses.put("COMP3432", "INNOVATIVE COMPUTING PARADIGMS");
		courses.put("COMP407",  "COMPUTER GRAPHICS");
		courses.put("COMP4122", "GAME DESIGN AND DEVELOPMENT");
		courses.put("COMP4123", "BUSINESS PROCESS AND WORKFLOW MANAGEMENT");
		courses.put("COMP4133", "INFORMATION RETRIEVAL");
		courses.put("COMP4135", "KNOWLEDGE AND INFORMATION MANAGEMENT");
		courses.put("COMP4334", "PRINCIPLES AND PRACTICE OF INTERNET SECURITY");
		courses.put("COMP4422", "COMPUTER GRAPHICS");
		courses.put("COMP4431", "ARTIFICIAL INTELLIGENCE");
		courses.put("COMP4433", "DATA MINING AND DATA WAREHOUSING");
		courses.put("COMP4434", "BIG DATA ANALYTICS");
		courses.put("COMP4438", "EMBEDDED SOFTWARE");
		courses.put("COMP4442", "SERVICE AND CLOUD COMPUTING");
		courses.put("COMP4512", "INTELLECTUAL PROPERTY PROTECTION AND MANAGEMENT");
		courses.put("COMP5111", "DATABASE SYSTEMS AND MANAGEMENT");
		courses.put("COMP5121", "DATA MINING AND DATA WAREHOUSING APPLICATIONS");
		courses.put("COMP5122", "E-COMMERCE FUNDAMENTALS AND DEVELOPMENT");
		courses.put("COMP5131", "INTRODUCTION TO INFORMATION SYSTEMS");
		courses.put("COMP5132", "INFORMATION SYSTEMS ACQUISITION AND INTEGRATION");
		courses.put("COMP5133", "INFORMATION SYSTEMS AND E-COMMERCE STRATEGY");
		courses.put("COMP5134", "INFORMATION SYSTEM DEVELOPMET WITH OO METHODS");
		courses.put("COMP5135", "INFORMATION SYSTEMS AUDIT AND CONTROL");
		courses.put("COMP5138", "SERVICES SCIENCE MANAGEMENT");
		courses.put("COMP5220", "INFORMATION SYSTEMS PROJECT MANAGEMENT");
		courses.put("COMP5222", "SOFTWARE TESTING AND QUALITY ASSURANCE");
		courses.put("COMP5311", "INTERNET INFRASTRUCTURE AND PROTOCOLS");
		courses.put("COMP5322", "INTERNET COMPUTING AND APPLICATIONS");
		courses.put("COMP5325", "DISTRIBUTED COMPUTING");
		courses.put("COMP5326", "WIRELESS COMPUTING SYSTEMS AND APPLICATIONS");
		courses.put("COMP5331", "WEB ADVERTISING AND WEB PUBLISHING");
		courses.put("COMP5332", "WEB SERVICES AND PROJECT DEVELOPMENT");
		courses.put("COMP5412", "FUNDAMENTALS OF CHINESE COMPUTING");
		courses.put("COMP5422", "MULTIMEDIA COMPUTING, SYSTEMS AND APPLICATIONS");
		courses.put("COMP5422", "MULTIMEDIA COMPUTING, SYSTEMS AND APPLICATIONS");
		courses.put("COMP5517", "HUMAN COMPUTER INTERACTION");
		courses.put("COMP5538", "CUSTOMER RELATIONSHIP MANAGEMENT AND TECHNOLOGY");

		System.out.println(courses.containsKey("COMP0000"));
		System.out.println(courses.get("COMP3011"));
		for(Map.Entry<String, String> m:courses.entrySet()){
			System.out.println(m.getKey() + " " + m.getValue());
		}  
	}
	
	private static void mod97() {
	    int[] a = {1001, 1003, 1011, 1901, 2011, 2012, 2021, 2022, 2121, 2222, 2322, 2411, 2421, 2422, 2432, 3011, 3021, 3121, 3122, 3131, 3133, 3134, 3211, 3222, 3233, 3235, 3334, 3335, 3421, 3422, 3432, 3438, 3511, 3512, 3531, 3911, 3921, 4000, 4001, 4121, 4122, 4123, 4125, 4127, 4133, 4134, 4135, 4141, 4142, 4146, 4322, 4332, 4334, 4342, 4422, 4431, 4433, 4434, 4435, 4438, 4441, 4442, 4512, 4531, 4911, 4913, 4921};
	    int[] b = new int[a.length];
	    for (int i=0;i < a.length;i++)
	        b[i] = (a[i] % 97);
	    Arrays.sort(b);
	    System.out.println(Arrays.toString(b));
	}
}

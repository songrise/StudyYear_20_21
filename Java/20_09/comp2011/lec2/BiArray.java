package comp2011.lec2;

import java.security.SecureRandom;

public class BiArray {

	public static void test1() {
		long startTime = 0;
		double duration = 0;
		int N = 1 << 14; // 32768
		System.out.println("N = " + N);

		SecureRandom random = new SecureRandom();
		int a[][] = new int[N][N];
		int max = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = random.nextInt();

		startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				max = max > a[i][j] ? max : a[i][j];
		System.out.println("max = " + max);
		duration = (System.currentTimeMillis() - startTime) / 1000.;
		System.out.println("it takes " + duration + " seconds to scan the matrix by rows.");

		startTime = System.currentTimeMillis();
		max = 0;
		for (int j = 0; j < N; j++)
			for (int i = 0; i < N; i++)
				max = max > a[i][j] ? max : a[i][j];
		System.out.println("max = " + max);
		duration = (System.currentTimeMillis() - startTime) / 1000.;
		System.out.println("it takes " + duration + " seconds to scan the matrix by columns.");
	}

	public static void test2() {
		int[][] a = new int[10][];
		for (int i = 0; i < 10; i++)
			a[i] = new int[i];
		System.out.println("the length of a is " + a.length);
		for (int i = 0; i < 10; i++)
			System.out.println("the length of a[" + i + "] is " + a[i].length);
	}

	public static void main(String[] args) {
		test1();
	}
}

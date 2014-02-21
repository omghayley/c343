/** Yuzhen Ye@Indiana University, Bloomington, C343, Spring 2014*/
package qsort;

import java.util.Random;

public class QsortTest {
	private static Random rmachine;
	public static void main(String[] args) {
		rmachine = new Random();
		testQsort();
	}
	public static void testQsort() {
		System.out.println("Input numbers:");
		int size = 50;
		Integer[] nums = new Integer[size];
		for(int i = 0; i < size; i ++) {
			nums[i] = rmachine.nextInt(100);
			System.out.print(" " + nums[i]);
		}
		System.out.println("");
		Qsort<Integer> q = new Qsort<Integer>(size, nums);
		q.display();
	}
}

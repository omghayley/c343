/** Yuzhen Ye@Indiana University, Bloomington, C343, Spring 2014*/
package qsort;

public class Qsort <E extends Comparable <? super E>>{
	private E[] list;
	private int listSize;
	Qsort(int num, E[] inp) {
		listSize = num;
		list = inp;
		sort(0, listSize - 1);
	}
	public void sort(int i, int j) {
		int pivotIndex = findPivot(i, j);
		swapNodes(pivotIndex, j); //Place pivot in the end
		int k = partition(list[j], i-1, j);
		//Put pivot back in place; 
		swapNodes(k, j); 
		if(k > i + 1) { sort(i, k-1); }
		if(j > k + 1) { sort(k+1, j); }
	}
	private int findPivot(int s, int e) {
		return (s + e) / 2; //middle point
	}
	private int partition(E pivot, int s, int e) {
		int l = s;
		int r = e;
		//don't know how many values are greater than the pivot value?
		//this algorithm moves indices inwards from the ends of the subarray
		//swaps values if they are found in the "wrong" side
		while(l < r) {
			while(list[++l].compareTo(pivot) < 0);
			while((r > 0) && (list[--r].compareTo(pivot) > 0));
			if(r > l) {
				swapNodes(l, r);
			}
		}
		//return the last l
		return l;
	}
	public void swapNodes(int i, int j) {
		E tmp = list[i];
		list[i] = list[j];
		list[j] = tmp;
	}
	public void display() {
		String output = "After quicksort (total num: " + listSize + ")\n";
		for(int i = 0; i < listSize; i ++) output += " " + list[i];
		System.out.println(output);
	}
}
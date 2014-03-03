package huffman;

public class HuffCode {
	public static void main(String[] args) {
		String letters = "CDEKLMUZ";
		int[] freq = {32, 42, 120, 7, 42, 24, 37, 2};
		//String letters = "ABCDEFGHIJKL";
		//int[] freq = {2, 3, 5, 7, 11, 13, 17, 19, 23, 31, 37, 41};
		System.out.println("letters: " + letters);
		HuffTree huff = new HuffTree(letters, freq);
		String encode = huff.encode("DEED");
		System.out.println("DEED code: " + encode);
		String decode = huff.decode(encode);
		System.out.println("decoded: " + decode);
	}
}

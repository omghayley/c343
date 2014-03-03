/**For simplicity, the huffman codes are represented in Strings, not bits
 * Yuzhen Ye@School of Informatics and Computing, Indiana University, Bloomington
 */
package huffman;

import java.util.Enumeration;
import java.util.Hashtable;

public class HuffTree<Key, E> {
	private MinHeap<Integer, String> heap; //for building the tree
	private BinNode<Integer, String> root; //for traversal
	private Hashtable<String, String> codeTable; //huffman code table 
	private Hashtable<String, Integer> codeFreq;
	public HuffTree(String letters, int[] weights) {
		init(letters, weights);
		buildTree();
		codeTable = new Hashtable<String, String>();
		codeFreq = new Hashtable<String, Integer>();
		for(int i = 0; i < letters.length(); i ++) 
			codeFreq.put(letters.substring(i, i + 1), weights[i]);
		getCode();
	}
	private void init(String letters, int[] weights) {
		int maxNum = letters.length();
		//BinNode<Integer, String>[] nodes = (BinNode<Integer, String>[]) new Object[maxNum];
		BinNode<Integer, String>[] nodes = new BinNode[maxNum];
		for(int i = 0; i < maxNum; i ++) {
			nodes[i] = new BinNode<Integer, String>(weights[i], letters.substring(i, i + 1)); 
		}
		heap = new MinHeap<Integer, String>(maxNum, maxNum, nodes);
		heap.display();
	}
	private void buildTree() {
		while(heap.length() > 1) {
			BinNode<Integer, String> node1 = heap.removeMin();
			BinNode<Integer, String> node2 = heap.removeMin();
			BinNode<Integer, String> newnode = new BinNode<Integer, String>(node1.getKey() + node2.getKey(), " ");
			newnode.setLeft(node1);
			newnode.setRight(node2);
			heap.insert(newnode);
			heap.display();
		}
		root = heap.removeMin();
		System.out.println("root of the hufftree: weight " + root.getKey());
	}
	//function 1: get the codes by walking in the tree
	//each leaf node is a letter, and the corresponding path is the code
	private void getCode() {
		//get the code, starting from the root
		getCodeHelp(root, "");
		//display the code & compute the sum of weighted path lengths
		Enumeration<String> keys = codeFreq.keys();
		int sumOfWeightedPath = 0;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
		    System.out.println("Letter: " + key + " " + codeTable.get(key));
		    sumOfWeightedPath += codeTable.get(key).length() * codeFreq.get(key); 
		}
		System.out.println("Total letters: " + root.getKey());
		System.out.println("Sum of weighted path lengths: " + sumOfWeightedPath);
		System.out.println("Ave-Code-Length: " + sumOfWeightedPath * 1.0 / root.getKey());	
	}
	private void getCodeHelp(BinNode<Integer, String>entry, String incode) {
		if(entry == null) return;
		if(entry.getLeft() != null) {
			getCodeHelp(entry.getLeft(), incode + "0");
			getCodeHelp(entry.getRight(), incode + "1");
		}
		else {
			codeTable.put(entry.getValue(), incode);
		} 
	}
	//function 2: encode a message
	public String encode(String instr) {
		String code = "";
		for(int i = 0; i < instr.length(); i ++) {
			String letter = instr.substring(i, i+1);
			code += codeTable.get(letter);
		}
		return code;
	}
	//function 3: decode a message
	public String decode(String coded) {
		String ori = "";
		int	pos = 0;
		int codelen = coded.length();
		BinNode curr = root; 
		assert curr.isLeaf() == false : "empty Huffman tree";
		for(int i = 0; i < coded.length(); i ++) {
			char tmp = coded.charAt(i);
			if(tmp == '0') curr = curr.getLeft();
			else curr = curr.getRight();
			if(curr.isLeaf()) { 
				ori += curr.getValue();
				curr = root;
			}
		}
		return ori;
	}
}
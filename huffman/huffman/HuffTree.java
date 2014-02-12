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
	public HuffTree(String letters, int[] weights) {
		init(letters, weights);
		buildTree();
		codeTable = new Hashtable<String, String>();
		getCode(root, "");
		displayCode();
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
	private void getCode(BinNode<Integer, String>entry, String incode) {
		if(entry == null) return;
		if(entry.getLeft() != null) {
			getCode(entry.getLeft(), incode + "0");
			getCode(entry.getRight(), incode + "1");
		}
		else {
			codeTable.put(entry.getValue(), incode);
		} 
	}
	private void displayCode() {
		Enumeration<String> keys = codeTable.keys();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
		    System.out.println("Letter: " + key + " " + codeTable.get(key));
		}
	}
	public String encode(String instr) {
		String code = "";
		for(int i = 0; i < instr.length(); i ++) {
			String letter = instr.substring(i, i+1);
			code += codeTable.get(letter);
		}
		return code;
	}
	public String decode(String coded) {
		String ori = "";
		int	pos = 0;
		int codelen = coded.length();
		while(coded.length() > 0) {
			String letter = findLeaf(root, coded);
			if(letter == "*") break;
			String tmp = codeTable.get(letter);
			coded = coded.substring(tmp.length(), coded.length());
			ori += letter;
		}
		return ori;
	}
	private String findLeaf(BinNode<Integer, String> entry, String str) {
		if(entry.isLeaf()) return entry.getValue();
		else if(str == "") return "*";
		else {
			char tmp = str.charAt(0);
			str = str.substring(1, str.length());
			if(tmp == '0') {
				return findLeaf(entry.getLeft(), str);
			}
			else {
				return findLeaf(entry.getRight(), str);
			}
		}
	}
}
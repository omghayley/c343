/**For simplicity, the huffman codes are represented in Strings, not bits
 * Yuzhen Ye@School of Informatics and Computing, Indiana University, Bloomington
 */
package huffman;

public class HuffTree<Key, E> {
	private MinHeap<Integer, String> heap; //for building the tree
	private BinNode<Integer, String> root; //for traversal
	//to-do
	//private Hashtable<String, String> codeTable; //huffman code table
	public HuffTree(String letters, int[] weights) {
		init(letters, weights);
		buildTree();
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
}

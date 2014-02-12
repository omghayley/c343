package huffman;

public class BinNode<Key, E> {
	private Key key;
	private E value;
	private BinNode left;
	private BinNode right;
	public BinNode(Key k, E e) {
		key = k;
		value = e;
		left = right = null;
	}
	public Key getKey() { return key; }
	public E getValue() { return value; }
	public void setKey(Key k) { key = k; }
	public void setValue(E e) { value = e; }
	public void setLeft(BinNode node) { left = node; }
	public void setRight(BinNode node) { right = node; }
	public void removeLeft() { left = null; }
	public void removeRight() { right = null; }
	public BinNode getLeft() { return left; }
	public BinNode getRight() { return right; }
	public boolean isLeaf() {
		if((left == null) && (right == null)) return true;
		else return false;
	}
}

public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;
	public FSTree(InternalNode root, Leaf firstLeaf) {
		super();
		this.root = root;
		this.firstLeaf = firstLeaf;
	}
	
	//TODO
	public Leaf search(Leaf f, int x) {
		return null;
	}
	
	//TODO
	public Leaf insert(Leaf f, int x) {
		return null;
	}
	
	//TODO
	public void delete(Leaf f) {
		
	}
	
	public InternalNode getRoot() {
		return root;
	}
	
	public void setRoot(InternalNode root) {
		this.root = root;
	}
	
	public Leaf getFirstLeaf() {
		return firstLeaf;
	}
	
	public void setFirstLeaf(Leaf firstLeaf) {
		this.firstLeaf = firstLeaf;
	}
	
}


public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;
	public FSTree() {
		root = new InternalNode(1, null);
		firstLeaf = null;
	}
	
	//TODO
	public Leaf search(Leaf f, int x) {
		return null;
	}
	
	//TODO
	public Leaf insert(Leaf f, int x) {
		Triple newTriple, firstTriple, temp1, temp2;
		if(f == null) {
			firstTriple = new Triple(0, 0, root, null);
			f = new Leaf(0, x, null, null, firstTriple, root);
			return f;
		}
		f.incCounter();
		f.updateTriple();
		firstTriple = firstLeaf.getFirstTriple();
		newTriple = new Triple(firstTriple.getI(), firstTriple.getJ(), firstTriple.getAncestor(), null);
		temp1 = firstTriple.getNext();
		while(temp1 != null) {
			
		}
		Leaf newLeaf = new Leaf(f.getCounter(), x, f, f.getNext(), f.getFirstTriple(), f.getParent());
		f.setNext(newLeaf);
		return newLeaf;
	}
	//TODO
	public void split() {
		
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

package Tree;

import java.util.LinkedList;

public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;
	public FSTree() {
		root = new InternalNode(1, null, null, null, null, null);
		firstLeaf = null;
		IntermediateNodeLevel2 firstINL2 = new IntermediateNodeLevel2(root, null, null);
		IntermediateNodeLevel1 firstINL1 = new IntermediateNodeLevel1(firstINL2, firstLeaf, 
				null);
		firstINL2.setLeftINL1(firstINL1);
		root.setLeftINL2(firstINL2);
	}
	
	//TODO
	public Leaf search(Leaf f, int x) {
		Leaf temp;
		if(f.getValue() == x)
			return f;
		temp = f.getPrev();
		//check the left leaf of l
		if(temp != null) {
			if (temp.getValue() == x)
				return temp;
		}
		temp = f.getNext();
		//check the right leaf of l
		if(temp != null) {
			if(temp.getValue() == x)
				return temp;
		}
		if(f.getValue() < x) {
			
		}
		return null;
	}
	
	public Leaf search(Leaf f, int x, boolean direction, boolean lastDirection) {
		
		
		return null;
	}
	
	//TODO
	@SuppressWarnings("unchecked")
	public Leaf insert(Leaf f, int x) {
		LinkedList<Triple> triples;
		LinkedList<Triple> newTriples;
		InternalNode ancestor;
		IntermediateNodeLevel1 upNode;
		//empty tree
		if(f == null) {
			Triple firstTriple = new Triple(0, 0, root);
			triples = new LinkedList<Triple>();
			triples.add(firstTriple);
			f = new Leaf(1, x, null, null, triples, root.getLeftINL2().getLeftINL1());
			return f;
		}
		//x already exists
		if(f.getValue() == x)
			return f;
		
		f.incCounter();
		f.updateTriple();
		newTriples = (LinkedList<Triple>) f.getTriples().clone();
		Leaf newLeaf = new Leaf(f.getCounter(), x, f, f.getNext(), newTriples, f.getUpNode());
		f.setNext(newLeaf);
		upNode = f.getUpNode();
		if(upNode.getRightMost() == f)
			upNode.setRightMost(newLeaf);
		upNode.incNumberOfDownNode();
		upNode.split();
		upNode.getUpNode().split();
		ancestor = f.getTriples().getFirst().getAncestor();
		ancestor.split(this);
		return newLeaf;
	}
	
	//TODO
	public void delete(Leaf f) {
		
	}
	
	public void paintImage() {
		
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

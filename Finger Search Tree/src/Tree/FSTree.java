package Tree;

import java.util.LinkedList;

import Paint.MyFrame;

public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;

	public FSTree() {
		root = new InternalNode(1, null, null, null, null, null);
		LinkedList<Triple> triples = new LinkedList<Triple>();
		firstLeaf = new Leaf(0, -1, null, null, triples, null);
		IntermediateNodeLevel2 firstINL2 = new IntermediateNodeLevel2(root, null, null, null, null);
		IntermediateNodeLevel1 firstINL1 = new IntermediateNodeLevel1(firstINL2, null, null, firstLeaf, firstLeaf);
		firstLeaf.setUpNode(firstINL1);
		firstINL2.setLeftINL1(firstINL1);
		firstINL2.setRightINL1(firstINL1);
		root.setLeftINL2(firstINL2);
		root.setRightINL2(firstINL2);
	}

	// TODO
	public Leaf search(Leaf f, int x) {
		Leaf temp;
		if (f.getValue() == x)
			return f;
		temp = f.getPrev();
		// check the left leaf of l
		if (temp != null) {
			if (temp.getValue() == x)
				return temp;
		}
		temp = f.getNext();
		// check the right leaf of l
		if (temp != null) {
			if (temp.getValue() == x)
				return temp;
		}
		if (f.getValue() < x) {

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Leaf insert(Leaf f, int x) {
		LinkedList<Triple> newTriples;
		InternalNode ancestor;
		IntermediateNodeLevel1 upNode;

		// x already exists
		if (f.getValue() == x)
			return f;

		f.incCounter();
		f.updateTriple();
		newTriples = (LinkedList<Triple>) f.getTriples().clone();
		Leaf newLeaf = new Leaf(f.getCounter(), x, f, f.getNext(), newTriples, f.getUpNode());
		if (f.getNext() != null)
			f.getNext().setPrev(newLeaf);
		f.setNext(newLeaf);
		upNode = f.getUpNode();
		if (upNode.getRightMost() == f)
			upNode.setRightMost(newLeaf);
		upNode.incNumberOfDownNode();
		upNode.split();
		ancestor = f.getTriples().getFirst().getAncestor();
		ancestor.split(this);
		return newLeaf;
	}

	public void delete(Leaf f) {
		IntermediateNodeLevel1 upNode = f.getUpNode();
		Leaf prev = f.getPrev();
		Leaf next = f.getNext();

		// Remove the leaf from the double linked list
		prev.setNext(next);
		if (next != null)
			next.setPrev(prev);

		// Change the leftmost or rightmost child of the upNode if necessary
		if (upNode.getNumberOfDownNode() > 1) {
			if (f == upNode.getLeftMost())
				upNode.setLeftMost(next);
			if (f == upNode.getRightMost())
				upNode.setRightMost(prev);
		}

		upNode.decNumberOfDownNode();
		upNode.fuse(this);
	}
	
	public FSTree linearSpace() {
		return null;
	}
	
	public void paintImage() {
		new MyFrame(this);
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

import java.util.LinkedList;

public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;
	public FSTree() {
		root = new InternalNode(1, null, null, null, null, null);
		firstLeaf = null;
		IntermediateNodeLevel1 firstINL1 = new IntermediateNodeLevel1(root, firstLeaf, 
				null, 0);
		root.setLeftINL1(firstINL1);
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
	public Leaf insert(Leaf f, int x) {
		LinkedList<Triple> triples;
		LinkedList<Triple> newTriples;
		InternalNode ancestor;
		if(f == null) {
			Triple firstTriple = new Triple(0, 0, root);
			triples = new LinkedList<Triple>();
			triples.add(firstTriple);
			f = new Leaf(1, x, null, null, triples, root.getLeftINL1());
			return f;
		}
		if(f.getValue() == x)
			return f;
		f.incCounter();
		f.updateTriple();
		newTriples = (LinkedList<Triple>) firstLeaf.getTriples().clone();
		Leaf newLeaf = new Leaf(f.getCounter(), x, f, f.getNext(), newTriples, f.getUpNode());
		f.setNext(newLeaf);
		ancestor = f.getTriples().getFirst().getAncestor();
		ancestor.split();
		return newLeaf;
	}
	
	//TODO
	public void delete(Leaf f) {
		
	}
	
	public String print() {
		if(root == null) 
			return "empty tree";
		return print(root);
		
	}
	public String print(Node node) {
		InternalNode temp;
		Leaf temp2;
		String result = "|\n|";
		if(node instanceof InternalNode) {
			temp = (InternalNode)node;
			while(temp != null) {
				result += "--" +"pr("+ temp.getUpNode().toString() +")"+ temp.toString() + 
						"ch(" + temp.getLeftINL1().toString() + ","
						+ temp.getRightINL1().toString() + ")";
				temp = temp.getNext();
			}
			result += "\n";
			return result + print(node.getLeftINL1().getLeftDownNode());
		}
		temp2 = (Leaf) node;
		while(temp2 != null) {
			result += "**" + temp2.getValue() + "(" + temp2.getUpNode().toString() + 
					")";
			temp2 = temp2.getNext();
		}
		result += "\n";
		return result;


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

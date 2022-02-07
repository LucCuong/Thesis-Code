package Tree;

import java.util.LinkedList;

public class Leaf extends Node{
	private int counter;
	private int value;
	private Leaf prev;
	private Leaf next;
	private LinkedList<Triple> triples;
	public Leaf(int counter, int value, Leaf prev, Leaf next, LinkedList<Triple> triples,
			IntermediateNodeLevel1 upNode) {
		super(upNode, prev, next);
		this.counter = counter;
		this.value = value;
		this.next = next;
		this.prev = prev;
		this.triples = triples;
		this.upNode = upNode;
	}


	public int d() {
		String counterString = Integer.toBinaryString(counter);
		for(int i = counterString.length() - 1; i >= 0; i--) {
			if(counterString.charAt(i) == '1') {
				System.out.println("binary counter: " + counterString + 
						" and value of d:" + (counterString.length()-i));
				return counterString.length()-i;
			}
		}
		System.out.println("binary counter: " + counterString + " and value of d: 0");
		return 0;
	}
	
	public void updateTriple() {
		InternalNode ancestor;
		if(triples.isEmpty() == true) {
			Triple newTriple = new Triple(0, 0, upNode.getUpNode().getUpNode());
			triples.add(newTriple);
			return;
		}
		if(triples.getFirst().getI() >= 1) {
			Triple newTriple = new Triple(0, 0, upNode.getUpNode().getUpNode());
			triples.addFirst(newTriple);
		}else {
			int newJ = triples.getFirst().getJ() + 1;
			triples.getFirst().setI(newJ);
			triples.getFirst().setJ(newJ);
			ancestor = triples.getFirst().getAncestor();
			if(ancestor.getUpNode() != null)
				triples.getFirst().setAncestor(ancestor.getUpNode().getUpNode().getUpNode());
		}
		mergeTriple();
	}
	
	public void mergeTriple() {
		if(triples.size()>1) {
			Triple first 	= triples.get(0);
			Triple second 	= triples.get(1);
			if(first.getJ() == (second.getI() - 1)) {
				second.setI(first.getI());
				triples.removeFirst();
			}
		}
	}
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public void incCounter() {
		this.counter++;
	}
	public Leaf getNext() {
		return next;
	}
	public void setNext(Leaf next) {
		this.next = next;
	}
	public Leaf getPrev() {
		return prev;
	}

	public void setPrev(Leaf prev) {
		this.prev = prev;
	}
	
	public LinkedList<Triple> getTriples() {
		return triples;
	}
	public void setTriples(LinkedList<Triple> triples) {
		this.triples = triples;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}

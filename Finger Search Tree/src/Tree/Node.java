package Tree;

public class Node {
	protected IntermediateNodeLevel1 upNode;
	private Node prev;
	private Node next;


	public Node(IntermediateNodeLevel1 upNode, Node prev, Node next) {
		this.upNode = upNode;
		this.prev = prev;
		this.next = next;
	}

	public IntermediateNodeLevel1 getUpNode() {
		return upNode;
	}
	public void setUpNode(IntermediateNodeLevel1 upNode) {
		this.upNode = upNode;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}

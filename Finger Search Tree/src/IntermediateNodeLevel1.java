
public class IntermediateNodeLevel1 {
	private InternalNode upNode;
	private Node leftDownNode;
	private Node rightDownNode;
	private long numberOfDownNode;
	public IntermediateNodeLevel1(InternalNode upNode, Leaf leftChild, Leaf rightChild, 
			long numberOfDownNode) {
		this.upNode = upNode;
		this.leftDownNode = leftChild;
		this.rightDownNode = rightChild;
		this.numberOfDownNode = numberOfDownNode;
	}

	public boolean toSplit() {
		if(numberOfDownNode > upNode.getDeltaD())
			return true;
		return false;
	}
	public void split() {
		
	}
	public void incNumberOfDownNode() {
		numberOfDownNode++;
	}
	public void decNumberOfDownNode() {
		numberOfDownNode--;
	}
	public InternalNode getUpNode() {
		return upNode;
	}
	public void setUpNode(InternalNode upNode) {
		this.upNode = upNode;
	}
	public Node getLeftDownNode() {
		return leftDownNode;
	}

	public void setLeftDownNode(Node leftDownNode) {
		this.leftDownNode = leftDownNode;
	}

	public Node getRightDownNode() {
		return rightDownNode;
	}

	public void setRightDownNode(Node rightDownNode) {
		this.rightDownNode = rightDownNode;
	}

	public long getNumberOfDownNode() {
		return numberOfDownNode;
	}
	public void setNumberOfDownNode(long numberOfDownNode) {
		this.numberOfDownNode = numberOfDownNode;
	}

	
}

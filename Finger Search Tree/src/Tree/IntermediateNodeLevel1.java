package Tree;


public class IntermediateNodeLevel1 {
	private InternalNode upNode;
	private Node leftDownNode;
	private Node rightDownNode;
	private long numberOfDownNode;
	private IntermediateNodeLevel1 pair;
	public IntermediateNodeLevel1(InternalNode upNode, Node leftDownNode, Node rightDownNode, 
			long numberOfDownNode) {
		this.upNode = upNode;
		this.leftDownNode = leftDownNode;
		this.rightDownNode = rightDownNode;
		this.numberOfDownNode = numberOfDownNode;
	}

	public boolean toSplit() {
		if(numberOfDownNode > upNode.getDeltaD())
			return true;
		return false;
	}
	public void split() {
		if(numberOfDownNode > upNode.getDeltaD()) {
			//the INL1 doesn't have a pair yet
			if(pair == null) {
				//create a new pair node and assign the new pair as rightmost INL1
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(upNode, this.rightDownNode, null, 0);
				this.pair = newINL1;
				if(upNode.getRightINL1() == null || upNode.getRightINL1() == this)
					upNode.setRightINL1(pair);
				newINL1.setPair(this);
			}else {
				//the current INL1 already has a pair node
				pair.setLeftDownNode(rightDownNode);
			}
			rightDownNode = rightDownNode.getPrev();
			pair.incNumberOfDownNode();
			numberOfDownNode--;
			if(pair.getNumberOfDownNode() == upNode.getDeltaD()) {
				pair.setPair(null);
				this.pair = null;
			}
			return;
		}
		if(numberOfDownNode == upNode.getDeltaD()) {
			this.pair.setPair(null);
			this.pair = null;
		}
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

	public IntermediateNodeLevel1 getPair() {
		return pair;
	}

	public void setPair(IntermediateNodeLevel1 pair) {
		this.pair = pair;
	}
	
	
}

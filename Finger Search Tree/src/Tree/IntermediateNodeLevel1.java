package Tree;


public class IntermediateNodeLevel1 {
	private IntermediateNodeLevel2 upNode;
	private Node leftMost;
	private Node rightMost;
	private long numberOfDownNode;
	private IntermediateNodeLevel1 pair;
	public IntermediateNodeLevel1(IntermediateNodeLevel2 upNode, Node leftMost, Node rightMost) {
		this.upNode = upNode;
		this.leftMost = leftMost;
		this.rightMost = rightMost;
		this.numberOfDownNode = 0;
	}

	public boolean toSplit() {
		if(numberOfDownNode > upNode.getUpNode().getDeltaD())
			return true;
		return false;
	}
	//TODO
	public void split() {
		if(numberOfDownNode > upNode.getUpNode().getDeltaD()) {
			//the INL1 doesn't have a pair yet
			if(pair == null) {
				//create a new pair node and assign the new pair as rightmost INL1
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(upNode, this.rightMost, null);
				this.pair = newINL1;
				if(upNode.getRightINL1() == null || upNode.getRightINL1() == this)
					upNode.setRightINL1(pair);
				newINL1.setPair(this);
			}else {
				//the current INL1 already has a pair node
				pair.setLeftMost(rightMost);
			}
			rightMost = rightMost.getPrev();
			pair.incNumberOfDownNode();
			numberOfDownNode--;
			if(pair.getNumberOfDownNode() == upNode.getUpNode().getDeltaD()) {
				pair.setPair(null);
				this.pair = null;
			}
			return;
		}
		if(numberOfDownNode == upNode.getUpNode().getDeltaD()) {
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

	public IntermediateNodeLevel2 getUpNode() {
		return upNode;
	}

	public void setUpNode(IntermediateNodeLevel2 upNode) {
		this.upNode = upNode;
	}

	public Node getLeftMost() {
		return leftMost;
	}

	public void setLeftMost(Node leftMost) {
		this.leftMost = leftMost;
	}

	public Node getRightMost() {
		return rightMost;
	}

	public void setRightMost(Node rightMost) {
		this.rightMost = rightMost;
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

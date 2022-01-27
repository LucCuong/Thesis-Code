package Tree;


public class IntermediateNodeLevel1 {
	private IntermediateNodeLevel2 upNode;
	private Node leftMost;
	private Node rightMost;
	private long numberOfDownNode;
	private IntermediateNodeLevel1 prev;
	private IntermediateNodeLevel1 next;
	private IntermediateNodeLevel1 pair;
	public IntermediateNodeLevel1(IntermediateNodeLevel2 upNode,IntermediateNodeLevel1 prev, IntermediateNodeLevel1 next, Node leftMost, Node rightMost) {
		this.upNode = upNode;
		this.prev = prev;
		this.next = next;
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
		long deltaD = upNode.getUpNode().getDeltaD();
		if(numberOfDownNode > deltaD) {
			//the INL1 doesn't have a pair yet
			if(pair == null) {
				//create a new pair node and assign the new pair as rightmost INL1
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(upNode, this, this.next, this.rightMost, this.rightMost);
				next = newINL1;
				pair = newINL1;
				newINL1.setPair(this);
				pair.incNumberOfDownNode();
			}else {
				//the current INL1 already has a pair node
				pair.setLeftMost(rightMost);
				pair.incNumberOfDownNode();
				if(pair.getNumberOfDownNode() == deltaD) {
					pair.setPair(null);
					pair = null;
					upNode.incNumberOfDownNode();
					if(upNode.getRightINL1() == this)
						upNode.setRightINL1(next);
				}
			}
			rightMost = rightMost.getPrev();
			numberOfDownNode--;
			upNode.split();
			return;
		}
		if(numberOfDownNode == deltaD) {
			if(pair != null) {
				pair.setPair(null);
				pair = null;
				upNode.incNumberOfDownNode();
				if(upNode.getRightINL1() == prev)
					upNode.setRightINL1(this);
				upNode.split();
			}

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

	public IntermediateNodeLevel1 getPrev() {
		return prev;
	}

	public void setPrev(IntermediateNodeLevel1 prev) {
		this.prev = prev;
	}

	public IntermediateNodeLevel1 getNext() {
		return next;
	}

	public void setNext(IntermediateNodeLevel1 next) {
		this.next = next;
	}
	
	
}

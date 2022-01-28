package Tree;

public class IntermediateNodeLevel1 {
	private IntermediateNodeLevel2 upNode;
	private Node leftMost;
	private Node rightMost;
	private long numberOfDownNode;
	private long deltaD;
	private IntermediateNodeLevel1 prev;
	private IntermediateNodeLevel1 next;
	private IntermediateNodeLevel1 pair;

	public IntermediateNodeLevel1(IntermediateNodeLevel2 upNode, IntermediateNodeLevel1 prev,
			IntermediateNodeLevel1 next, Node leftMost, Node rightMost) {
		this.upNode = upNode;
		this.prev = prev;
		this.next = next;
		this.leftMost = leftMost;
		this.rightMost = rightMost;
		this.numberOfDownNode = 0;
		this.deltaD = DeltaD(upNode.getUpNode().getHight());
	}

	// TODO
	public void split() {
		if (numberOfDownNode > deltaD) {
			// the INL1 doesn't have a pair yet
			if (pair == null) {
				// create a new pair node and assign the new pair as rightmost INL1
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(upNode, this, this.next, this.rightMost,
						this.rightMost);
				next = newINL1;
				pair = newINL1;
				newINL1.setPair(this);
				pair.incNumberOfDownNode();
			} else {
				// the current INL1 already has a pair node
				pair.setLeftMost(rightMost);
				pair.incNumberOfDownNode();
				if (pair.getNumberOfDownNode() == deltaD) {
					pair.setPair(null);
					pair = null;
					upNode.incNumberOfDownNode();
					if (upNode.getRightINL1() == this)
						upNode.setRightINL1(next);
				}
			}
			rightMost = rightMost.getPrev();
			numberOfDownNode--;
			upNode.split();
			return;
		}
		if (numberOfDownNode == deltaD) {
			if (pair != null) {
				pair.setPair(null);
				pair = null;
				upNode.incNumberOfDownNode();
				if (upNode.getRightINL1() == prev)
					upNode.setRightINL1(this);
				upNode.split();
			}

		}
	}

	public void fuse() {
		if (numberOfDownNode == (deltaD - 1)) {
			
			// Move a child from the pair node to this node
			if (pair != null) {
				this.setRightMost(rightMost.getNext());
				pair.setLeftMost(this.rightMost.getNext());
				numberOfDownNode++;
				pair.decNumberOfDownNode();
				
				// Pair node has no child
				if(pair.getNumberOfDownNode() == 0) {
					IntermediateNodeLevel1 next = pair.getNext();
					next.setPrev(this);
					this.next = next;
					pair.setPair(null);
					this.pair = null;
					return;
				}
			}else {
				// The current node doesn't have a pair node
				
				upNode.fuse();
			}
		}
		
		// The current node has no child
		if(numberOfDownNode == 0)

		upNode.fuse();
	}

	public long DeltaD(int d) {
		long result = 2;
		for (int i = 1; i < d; i++) {
			result = (long) Math.pow(2, result);
		}
		return result;
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

	public long getDeltaD() {
		return deltaD;
	}

}

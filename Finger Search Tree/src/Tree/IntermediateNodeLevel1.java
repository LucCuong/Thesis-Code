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
		this.numberOfDownNode = 1;
		this.deltaD = DeltaD(upNode.getUpNode().getHight());
	}

	public void split() {
		if (numberOfDownNode > deltaD) {
			// the INL1 doesn't have a pair yet
			if (pair == null) {
				// create a new pair node and assign the new pair as rightmost INL1
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(this.upNode, this, this.next, this.rightMost,
						this.rightMost);
				if (next != null)
					next.setPrev(newINL1);
				next = newINL1;
				pair = newINL1;
				newINL1.setPair(this);
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
			rightMost.setUpNode(next);
			rightMost = rightMost.getPrev();
			numberOfDownNode--;
			upNode.split();
			return;
		}
		
		// The current node has a pair node and reaches deltaD sub nodes
		if ((numberOfDownNode == deltaD) && (pair != null) && (pair.getNumberOfDownNode() == deltaD)) {
			pair.setPair(null);
			pair = null;
			upNode.incNumberOfDownNode();
			if (upNode.getRightINL1() == prev)
				upNode.setRightINL1(this);
			upNode.split();
		}
	}

	public void fuse(FSTree tree) {
		if (numberOfDownNode == (deltaD - 1)) {
			// Move a child from the pair node to this node
			if (pair != null) {
				this.setRightMost(rightMost.getNext());
				pair.setLeftMost(this.rightMost.getNext());
				rightMost.setUpNode(this);
				numberOfDownNode++;
				pair.decNumberOfDownNode();

				// Pair node has no child
				if (pair.getNumberOfDownNode() == 0) {
					IntermediateNodeLevel1 newNext = pair.getNext();
					if (newNext != null) {
						newNext.setPrev(this);
						this.next = newNext;
						pair.setPair(null);
						this.pair = null;
					} else
						next = null;
					return;
				}
			}
			// The current node doesn't have a pair node
			else {
				// The previous node has a pair node
				// => move one child from previous node to the current node
				if ((prev != null) && (prev.getPair() != null)) {
					leftMost = leftMost.getPrev();
					leftMost.setUpNode(this);
					prev.decNumberOfDownNode();
					numberOfDownNode++;

					// The previous node has no child => delete it
					if (prev.getNumberOfDownNode() == 0) {
						IntermediateNodeLevel1 newPrev = prev.getPrev();
						newPrev.setPair(null);
						prev.setPair(null);
						newPrev.setNext(this);
						prev = newPrev;
						return;
					}
					prev.setRightMost(leftMost.getPrev());
					return;
				}
				// Check the right neighbor node similarly
				// The next neighbor has a pair node
				// => move one child from the next neighbor to this node
				if ((next != null) && (next.pair != null)) {
					IntermediateNodeLevel1 pairOfNext = next.getPair();
					rightMost = rightMost.getNext();
					rightMost.setUpNode(this);
					numberOfDownNode++;
					next.setLeftMost(rightMost.getNext());
					next.setRightMost(pairOfNext.getLeftMost());
					next.getRightMost().setUpNode(next);
					pairOfNext.decNumberOfDownNode();

					// The pair node of the next node has no child
					// => remove it and unlink it with the next node
					if (pairOfNext.getNumberOfDownNode() == 0) {
						IntermediateNodeLevel1 newNext = pairOfNext.getNext();
						pairOfNext.setPair(null);
						next.setPair(null);
						if (newNext != null)
							newNext.setPrev(next);
						next.setNext(newNext);
						return;
					}
					pairOfNext.setLeftMost(pairOfNext.getLeftMost().getNext());
					return;
				}
				// The previous node doesn't have a pair node
				// => fuse this node with the previous node
				if (prev != null) {
					prev.setPair(this);
					pair = prev;
					// Two nodes don't have the same up node
					if (prev.getUpNode() != upNode) {
						IntermediateNodeLevel2 oldUpNode = this.upNode;
						this.upNode = prev.getUpNode();
						oldUpNode.decNumberOfDownNode();
						if (oldUpNode.getNumberOfDownNode() > 0) {
							if (this == oldUpNode.getLeftINL1())
								oldUpNode.setLeftINL1(next);
						}
						oldUpNode.fuse(tree);
						return;
					}
					// Tow nodes have the same up node
					// Change the rightINL1 of the upNode if necessary
					if (this == upNode.getRightINL1())
						upNode.setRightINL1(prev);
					upNode.decNumberOfDownNode();
					upNode.fuse(tree);
					return;
				}
				// The previous node is null
				if (next != null) {
					next.setPair(this);
					pair = next;

					// Make sure the left node of the pair always have deltaD sub nodes
					this.rightMost = next.getLeftMost();
					rightMost.setUpNode(this);
					next.setLeftMost(leftMost.getNext());
					numberOfDownNode++;
					next.decNumberOfDownNode();
					upNode.decNumberOfDownNode();
					upNode.fuse(tree);
					return;
					// Don't need to consider the case that two nodes have different up nodes
				}
			}
		}

		// The current node has no child
		if (numberOfDownNode == 0)
			// The current node has a pair node
			if (pair != null) {
				pair.setNext(next);
				next.setPrev(prev);
				pair.setPair(null);
				pair = null;
				return;
			}
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

package Tree;


public class InternalNode extends Node {
	private int height;
	private boolean dead;
	private IntermediateNodeLevel1 upNode;
	private InternalNode next;
	private InternalNode prev;
	private IntermediateNodeLevel2 leftINL2;
	private IntermediateNodeLevel2 rightINL2;

	public InternalNode(int height, IntermediateNodeLevel1 upNode, InternalNode prev, InternalNode next,
			IntermediateNodeLevel2 leftINL2, IntermediateNodeLevel2 rightINL2) {
		super(upNode, prev, next);
		this.height = height;
		this.next = next;
		this.prev = prev;
		this.leftINL2 = leftINL2;
		this.rightINL2 = rightINL2;
		this.dead = false;
		this.upNode = upNode;
	}

	public void split(FSTree tree) {
		if (dead == false)
			if (rightINL2 != leftINL2) {
				InternalNode newIN = new InternalNode(height, upNode, this, this.next, rightINL2, rightINL2);
				rightINL2.setUpNode(newIN);
				if(rightINL2.getPair() != null)
					rightINL2.getPair().setUpNode(newIN);
				IntermediateNodeLevel2 newRightINL2 = rightINL2.getPrev();
				if(newRightINL2.getPair() != null) {
					rightINL2 = newRightINL2.getPrev();
				}else {
					rightINL2 = newRightINL2;
				}
				if(next != null)
					next.setPrev(newIN);
				next = newIN;
				
				// The current node is the root
				// => Create a new root
				if (upNode == null) {
					InternalNode newRoot = new InternalNode((this.height + 1), null, null, null, null, null);
					IntermediateNodeLevel2 newINL2 = new IntermediateNodeLevel2(newRoot, null, null, null, null);
					IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(newINL2, null, null, this, newIN);
					newINL1.setNumberOfDownNode(2);
					newINL2.setLeftINL1(newINL1);
					newINL2.setRightINL1(newINL1);
					newRoot.setLeftINL2(newINL2);
					newRoot.setRightINL2(newINL2);
					this.upNode = newINL1;
					newIN.setUpNode(newINL1);
					tree.setRoot(newRoot);
				} else {
					if (this == upNode.getRightMost()) {
						upNode.setRightMost(newIN);
					}
					upNode.incNumberOfDownNode();
					upNode.split();
				}
			}
	}

	public void delete(FSTree tree) {
		// The node has no sub INL2 node
		if(leftINL2 == null && rightINL2 == null) {
			dead = true;
			prev.setNext(next);
			if(next != null)
				next.setPrev(prev);
			upNode.decNumberOfDownNode();
			
			// Update the leftmost or rightmost sub node of the up node
			if(this == upNode.getLeftMost())
				upNode.setLeftMost(next);
			if(this == upNode.getRightMost())
				upNode.setRightMost(prev);
			upNode.fuse(tree);
			// Reduce heigth of the tree if the root has only one child
			if(height == tree.getRoot().getHight()-1) {
				InternalNode father = upNode.getUpNode().getUpNode();
				father.reduce(tree);
			}
			
		}
	}
	
	public void reduce(FSTree tree) {
		if ((upNode == null) && (height > 1)) {
			// Current node is root and only has one sub node
			// And the sub node has only one sub node
			if((leftINL2 == rightINL2) && (leftINL2.getLeftINL1() == leftINL2.getRightINL1())) {
				IntermediateNodeLevel1 onlyINL1 = leftINL2.getLeftINL1();
				if(onlyINL1.getNumberOfDownNode() == 1) {
					dead = true;
					InternalNode newRoot = (InternalNode) onlyINL1.getLeftMost();
					tree.setRoot(newRoot);
					onlyINL1.setLeftMost(null);
					onlyINL1.setRightMost(null);
					newRoot.setUpNode(null);
				}
			}
		}
	}
	
	public int getHight() {
		return height;
	}

	public void setHight(int hight) {
		this.height = hight;
	}

	public IntermediateNodeLevel1 getUpNode() {
		return upNode;
	}

	public void setUpNode(IntermediateNodeLevel1 upNode) {
		this.upNode = upNode;
	}

	public InternalNode getNext() {
		return next;
	}

	public void setNext(InternalNode next) {
		this.next = next;
	}

	public InternalNode getPrev() {
		return prev;
	}

	public void setPrev(InternalNode prev) {
		this.prev = prev;
	}

	public IntermediateNodeLevel2 getLeftINL2() {
		return leftINL2;
	}

	public void setLeftINL2(IntermediateNodeLevel2 leftINL2) {
		this.leftINL2 = leftINL2;
	}

	public IntermediateNodeLevel2 getRightINL2() {
		return rightINL2;
	}

	public void setRightINL2(IntermediateNodeLevel2 rightINL2) {
		this.rightINL2 = rightINL2;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}

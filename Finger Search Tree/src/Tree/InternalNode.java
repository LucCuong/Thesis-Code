package Tree;

import java.lang.Math;
public class InternalNode extends Node{
	private int hight;
	private long deltaD;
	private IntermediateNodeLevel1 upNode;
	private InternalNode next;
	private InternalNode prev;
	private IntermediateNodeLevel1 leftINL1;
	private IntermediateNodeLevel1 rightINL1;
	public InternalNode(int hight, IntermediateNodeLevel1 upNode, InternalNode prev, 
		InternalNode next, IntermediateNodeLevel1 leftINL1, IntermediateNodeLevel1 rightINL1) {
		super(upNode, prev, next);
		this.hight 		= hight;
		this.deltaD 	= calculateDeltaD(hight);
		this.next 		= next;
		this.prev 		= prev;
		this.leftINL1 	= leftINL1;
		this.rightINL1 	= rightINL1;
	}
	private long calculateDeltaD(int d) {
		return (long) Math.pow(2, (long)Math.pow(2,d));
	}
	public void split(FSTree tree) {
		if((rightINL1 != null) & (leftINL1.getPair()!=rightINL1)) {
			InternalNode newIN;
			if(rightINL1.getPair() != null) {
				newIN = new InternalNode(hight, upNode, this, next, rightINL1.getPair(), rightINL1);
			}else {
				newIN = new InternalNode(hight, upNode, this, next, rightINL1, null);
			}
			//current node is the root
			if(upNode == null) {
				InternalNode newRoot = new InternalNode((this.hight + 1), null, null, null,null ,null);
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(newRoot, this, newIN, 2);
				newRoot.setLeftINL1(newINL1);
				this.upNode = newINL1;
				newIN.setUpNode(newINL1);
				tree.setRoot(newRoot);
			}else {
				if((this == upNode.getRightDownNode()) || (upNode.getRightDownNode() == null)) {
					upNode.setRightDownNode(newIN);
				}
				upNode.incNumberOfDownNode();
				upNode.split();
			}
			this.next = newIN;
			this.rightINL1 = this.leftINL1.getPair();
		}
	}
	public void merge() {
		
	}

	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	public long getDeltaD() {
		return deltaD;
	}
	public void setDeltaD(long deltaD) {
		this.deltaD = deltaD;
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
	public IntermediateNodeLevel1 getLeftINL1() {
		return leftINL1;
	}
	public void setLeftINL1(IntermediateNodeLevel1 leftINL1) {
		this.leftINL1 = leftINL1;
	}
	public IntermediateNodeLevel1 getRightINL1() {
		return rightINL1;
	}
	public void setRightINL1(IntermediateNodeLevel1 rightINL1) {
		this.rightINL1 = rightINL1;
	}


	
}

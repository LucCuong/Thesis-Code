package Tree;

import java.lang.Math;
public class InternalNode extends Node{
	private int hight;
	private long deltaD;
	private long gammaD;
	private boolean dead;
	private IntermediateNodeLevel1 upNode;
	private InternalNode next;
	private InternalNode prev;
	private IntermediateNodeLevel2 leftINL2;
	private IntermediateNodeLevel2 rightINL2;
	public InternalNode(int hight, IntermediateNodeLevel1 upNode, InternalNode prev, 
		InternalNode next, IntermediateNodeLevel2 leftINL2, IntermediateNodeLevel2 rightINL2) {
		super(upNode, prev, next);
		this.hight 		= hight;
		this.deltaD 	= calculateDeltaD(hight);
		this.gammaD 	= calculateGammaD(hight);
		this.next 		= next;
		this.prev 		= prev;
		this.leftINL2 	= leftINL2;
		this.rightINL2 	= rightINL2;
		this.dead = false;
	}
	private long calculateDeltaD(int d) {
		return (long) Math.pow(2, (long)Math.pow(2,d));
	}
	private long calculateGammaD(int d) {
		long result;
		long power2d1 = (long) Math.pow(2, (d-1));
		long power1 = 3 * (d-1) * power2d1;
		long power2 = (long) Math.pow(2, d) + power2d1 -2;
		result = (long) Math.pow(2, power1) * (long) Math.pow(2, power2);
		return result;
	}
	public void split(FSTree tree) {
		if((rightINL2 != null) & (leftINL2.getPair()!=rightINL2)) {
			InternalNode newIN;
			if(rightINL2.getPair() != null) {
				newIN = new InternalNode(hight, upNode, this, next, rightINL2.getPair(), rightINL2);
			}else {
				newIN = new InternalNode(hight, upNode, this, next, rightINL2, null);
			}
			//current node is the root
			if(upNode == null) {
				InternalNode newRoot = new InternalNode((this.hight + 1), null, null, null,null ,null);
				IntermediateNodeLevel2 newINL2 = new IntermediateNodeLevel2(newRoot,null, null);
				IntermediateNodeLevel1 newINL1 = new IntermediateNodeLevel1(newINL2, this, newIN);
				newINL1.setNumberOfDownNode(2);
				newINL2.setLeftINL1(newINL1);
				newRoot.setLeftINL2(newINL2);
				this.upNode = newINL1;
				newIN.setUpNode(newINL1);
				tree.setRoot(newRoot);
			}else {
				if((this == upNode.getRightMost()) || (upNode.getRightMost() == null)) {
					upNode.setRightMost(newIN);
				}
				upNode.incNumberOfDownNode();
				upNode.split();
			}
			this.next = newIN;
			this.rightINL2 = this.leftINL2.getPair();
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
	public long getGammaD() {
		return gammaD;
	}
	public void setGammaD(long gammaD) {
		this.gammaD = gammaD;
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

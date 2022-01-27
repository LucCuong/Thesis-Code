package Tree;

import java.lang.Math;

public class InternalNode extends Node {
	private int height;
	private long deltaD;
	private long gammaD;
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
		this.deltaD = DeltaD(height);
		this.gammaD = GammaD(height);
		this.next = next;
		this.prev = prev;
		this.leftINL2 = leftINL2;
		this.rightINL2 = rightINL2;
		this.dead = false;
	}

	public long DeltaD(int d) {
		long result = 2;
		for (int i = 1; i < height; i++) {
			result = (long) Math.pow(2, result);
		}
		return result;
	}

	private long GammaD(int d) {
		long result;
		long power2d1 = (long) Math.pow(2, (d - 1));
		long power1 = 3 * (d - 1) * power2d1;
		long power2 = (long) Math.pow(2, d) + power2d1 - 2;
		result = (long) Math.pow(2, power1) * (long) Math.pow(2, power2);
		return result;
	}

	public void split(FSTree tree) {
		if (dead == false)
			if ((rightINL2 != null) & (leftINL2.getPair() != rightINL2)) {
				InternalNode newIN;
				newIN = new InternalNode(height, upNode, this, next, rightINL2, rightINL2);
				next = newIN;
				// current node is the root
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

	public void merge() {

	}

	public int getHight() {
		return height;
	}

	public void setHight(int hight) {
		this.height = hight;
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

import java.lang.Math;
public class InternalNode extends Node{
	private int hight;
	private long deltaD;
	private IntermediateNodeLevel1 upNode;
	private long numberOfDownNode;
	private InternalNode next;
	private InternalNode prev;
	private IntermediateNodeLevel1 leftINL1;
	private IntermediateNodeLevel1 rightINL1;
	public InternalNode(int hight, IntermediateNodeLevel1 upNode, InternalNode next, 
		InternalNode prev, IntermediateNodeLevel1 leftINL1, IntermediateNodeLevel1 rightINL1) {
		this.hight 		= hight;
		this.deltaD 	= calculateDeltaD(hight);
		this.upNode 	= upNode;
		this.next 		= next;
		this.prev 		= prev;
		this.leftINL1 	= leftINL1;
		this.rightINL1 	= rightINL1;
	}
	private long calculateDeltaD(int d) {
		return (long) Math.pow(2, (long)Math.pow(2,d));
	}
	public void split() {
		if(rightINL1 == null) {
			if(leftINL1.toSplit())
				leftINL1.split();
		}
		if(rightINL1.toSplit() == true) {
			rightINL1.split();
		}
	}
	public void incNumberOfDownNode() {
		numberOfDownNode++;
	}
	public void decNumberOfDownNode() {
		numberOfDownNode--;
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
	public long getNumberOfDownNode() {
		return numberOfDownNode;
	}
	public void setNumberOfDownNode(long numberOfDownNode) {
		this.numberOfDownNode = numberOfDownNode;
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

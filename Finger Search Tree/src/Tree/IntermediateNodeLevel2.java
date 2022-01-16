package Tree;


public class IntermediateNodeLevel2 {
	private InternalNode upNode;
	private long numberOfDownNode;
	private IntermediateNodeLevel1 leftINL1;
	private IntermediateNodeLevel1 rightINL1;
	private IntermediateNodeLevel2 pair;

	public IntermediateNodeLevel2(InternalNode upNode, IntermediateNodeLevel1 leftINL1,
			IntermediateNodeLevel1 rightINL1) {
		this.upNode = upNode;
		this.leftINL1 = leftINL1;
		this.rightINL1 = rightINL1;
		this.numberOfDownNode = 1;
	}
	public void split() {
		
	}
	public InternalNode getUpNode() {
		return upNode;
	}
	public void setUpNode(InternalNode upNode) {
		this.upNode = upNode;
	}
	public long getNumberOfDownNode() {
		return numberOfDownNode;
	}
	public void setNumberOfDownNode(long numberOfDownNode) {
		this.numberOfDownNode = numberOfDownNode;
	}
	public IntermediateNodeLevel1 getLeftINL1() {
		return leftINL1;
	}
	public void setLeftINL1(IntermediateNodeLevel1 leftMost) {
		this.leftINL1 = leftMost;
	}
	public IntermediateNodeLevel1 getRightINL1() {
		return rightINL1;
	}
	public void setRightINL1(IntermediateNodeLevel1 rightMost) {
		this.rightINL1 = rightMost;
	}
	public IntermediateNodeLevel2 getPair() {
		return pair;
	}
	public void setPair(IntermediateNodeLevel2 pair) {
		this.pair = pair;
	}
	
}

package Tree;

public class CurrentSearchNodeStorage {
	private InternalNode leftIN;
	private InternalNode rightIN;
	private IntermediateNodeLevel1 leftINL1;
	private IntermediateNodeLevel1 rightINL1;
	private IntermediateNodeLevel2 leftINL2;
	private IntermediateNodeLevel2 rightINL2;
	
	
	public InternalNode getLeftIN() {
		return leftIN;
	}
	public void setLeftIN(InternalNode leftIN) {
		this.leftIN = leftIN;
	}
	public InternalNode getRightIN() {
		return rightIN;
	}
	public void setRightIN(InternalNode rightIN) {
		this.rightIN = rightIN;
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
	
	
}

package Tree;

public class OwnStorage {
	private InternalNode leftIN;
	private InternalNode rightIN;
	private IntermediateNodeLevel1 leftINL1;
	private IntermediateNodeLevel1 rightINL1;
	private IntermediateNodeLevel2 leftINL2;
	private IntermediateNodeLevel2 rightINL2;
	private InternalNode failIN;
	private IntermediateNodeLevel2 failINL2;
	private IntermediateNodeLevel1 failINL1;
	private InternalNode tempIN;
	private IntermediateNodeLevel1 tempINL1;
	private IntermediateNodeLevel2 tempINL2;
	private Leaf leftBarrier;
	private Leaf rightBarrier;
	private int x;
	
	public Leaf getLeftBarrier() {
		return leftBarrier;
	}
	public void setLeftBarrier(Leaf leftBarrier) {
		this.leftBarrier = leftBarrier;
	}
	public Leaf getRightBarrier() {
		return rightBarrier;
	}
	public void setRightBarrier(Leaf rightBarrier) {
		this.rightBarrier = rightBarrier;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
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
	public InternalNode getFailIN() {
		return failIN;
	}
	public void setFailIN(InternalNode failIN) {
		this.failIN = failIN;
	}
	public IntermediateNodeLevel2 getFailINL2() {
		return failINL2;
	}
	public void setFailINL2(IntermediateNodeLevel2 failINL2) {
		this.failINL2 = failINL2;
	}
	public IntermediateNodeLevel1 getFailINL1() {
		return failINL1;
	}
	public void setFailINL1(IntermediateNodeLevel1 failINL1) {
		this.failINL1 = failINL1;
	}
	public InternalNode getTempIN() {
		return tempIN;
	}
	public void setTempIN(InternalNode tempIN) {
		this.tempIN = tempIN;
	}
	public IntermediateNodeLevel1 getTempINL1() {
		return tempINL1;
	}
	public void setTempINL1(IntermediateNodeLevel1 tempINL1) {
		this.tempINL1 = tempINL1;
	}
	public IntermediateNodeLevel2 getTempINL2() {
		return tempINL2;
	}
	public void setTempINL2(IntermediateNodeLevel2 tempINL2) {
		this.tempINL2 = tempINL2;
	}
	
}

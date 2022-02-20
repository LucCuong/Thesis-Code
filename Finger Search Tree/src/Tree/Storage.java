package Tree;

public class Storage {
	private IntermediateNodeLevel1 INL1;
	private IntermediateNodeLevel2 INL2;
	private int x;
	private Leaf f;
	
	public IntermediateNodeLevel1 getINL1() {
		return INL1;
	}
	public void setINL1(IntermediateNodeLevel1 iNL1) {
		INL1 = iNL1;
	}
	public IntermediateNodeLevel2 getINL2() {
		return INL2;
	}
	public void setINL2(IntermediateNodeLevel2 iNL2) {
		INL2 = iNL2;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public Leaf getF() {
		return f;
	}
	public void setF(Leaf f) {
		this.f = f;
	}
	
	
}

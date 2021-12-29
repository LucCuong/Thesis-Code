import java.lang.Math;
public class InternalNode{
	private int hight;
	private long deltaD;
	private InternalNode parent;
	private long numberOfChildren;
	public InternalNode(int hight, InternalNode parent) {
		this.hight = hight;
		this.deltaD = calculateDeltaD(hight);
		this.parent = parent;
	}
	private long calculateDeltaD(int d) {
		return (long) Math.pow(2, (long)Math.pow(2,d));
	}
	public boolean toSplit() {
		if(numberOfChildren > (2*deltaD))
			return true;
		return false;
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
	public InternalNode getParent() {
		return parent;
	}
	public void setParent(InternalNode parent) {
		this.parent = parent;
	}
	public long getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(long numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	
}

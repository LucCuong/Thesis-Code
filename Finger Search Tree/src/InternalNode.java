import java.lang.Math;
public class InternalNode{
	private int hight;
	private long deltaD;
	public InternalNode(int hight) {
		this.hight = hight;
		this.deltaD = calculateDeltaD(hight);
	}
	private long calculateDeltaD(int d) {
		return (long) Math.pow(2, (long)Math.pow(2,d));
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
	
}

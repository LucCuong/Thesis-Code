
public class Triple {
	private int i;
	private int j;
	private InternalNode ancestor;
	private Triple next;
	public Triple(int i, int j, InternalNode ancestor, Triple next) {
		super();
		this.i = i;
		this.j = j;
		this.ancestor = ancestor;
		this.next = next;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public InternalNode getAncestor() {
		return ancestor;
	}
	public void setAncestor(InternalNode ancestor) {
		this.ancestor = ancestor;
	}
	public Triple getNext() {
		return next;
	}
	public void setNext(Triple next) {
		this.next = next;
	}
	
}

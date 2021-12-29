
public class Leaf{
	private int counter;
	private int value;
	private Leaf prev;
	private Leaf next;
	private Triple firstTriple;
	private InternalNode parent;
	public Leaf(int counter, int value, Leaf prev, Leaf next, Triple firstTriple, InternalNode parent) {
		this.counter = counter;
		this.value = value;
		this.next = next;
		this.prev = prev;
		this.firstTriple = firstTriple;
		this.parent = parent;
	}


	public int d() {
		String counterString = Integer.toBinaryString(counter);
		for(int i = counterString.length() - 1; i >= 0; i--) {
			if(counterString.charAt(i) == '1') {
				System.out.println("binary counter: " + counterString + " and value of d:" + (counterString.length()-i));
				return counterString.length()-i;
			}
		}
		System.out.println("binary counter: " + counterString + " and value of d: 0");
		return 0;
	}
	
	public void updateTriple() {
		if(firstTriple == null) {
			Triple newTriple = new Triple(0, 0, parent, null);
			firstTriple = newTriple;
			return;
		}
		if(firstTriple.getI() >= 1) {
			Triple temp = firstTriple;
			Triple newTriple = new Triple(0, 0, parent, temp);
			firstTriple = newTriple;
		}else {
			int newJ = firstTriple.getJ() + 1;
			firstTriple.setI(newJ);
			firstTriple.setJ(newJ);
			firstTriple.setAncestor(firstTriple.getAncestor().getParent());
		}
		mergeTriple();
	}
	
	public void mergeTriple() {
		Triple second = firstTriple.getNext();
		if(second != null) {
			if(firstTriple.getJ() == (second.getI()-1)) {
				second.setI(firstTriple.getI());
				firstTriple = second;
			}
		}
	}
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public void incCounter() {
		this.counter++;
	}
	public Leaf getNext() {
		return next;
	}
	public void setNext(Leaf next) {
		this.next = next;
	}
	public Leaf getPrev() {
		return prev;
	}
	public void setPrev(Leaf prev) {
		this.prev = prev;
	}

	public Triple getFirstTriple() {
		return firstTriple;
	}

	public void setFirstTriple(Triple firstTriple) {
		this.firstTriple = firstTriple;
	}
	
	public InternalNode getParent() {
		return parent;
	}

	public void setParent(InternalNode parent) {
		this.parent = parent;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
	
}

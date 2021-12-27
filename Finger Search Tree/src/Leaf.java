
public class Leaf{
	private int counter;
	private Leaf next;
	private Leaf prev;
	private Triple first;
	public Leaf(int counter, Leaf next, Leaf prev, Triple first) {
		this.counter = counter;
		this.next = next;
		this.prev = prev;
		this.first = first;
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
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
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

	public Triple getFirst() {
		return first;
	}

	public void setFirst(Triple first) {
		this.first = first;
	}

}

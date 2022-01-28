package Tree;


public class IntermediateNodeLevel2 {
	private InternalNode upNode;
	private long gammaD;
	private long numberOfDownNode;
	private IntermediateNodeLevel2 prev;
	private IntermediateNodeLevel2 next;
	private IntermediateNodeLevel1 leftINL1;
	private IntermediateNodeLevel1 rightINL1;
	private IntermediateNodeLevel2 pair;

	public IntermediateNodeLevel2(InternalNode upNode, IntermediateNodeLevel2 prev, IntermediateNodeLevel2 next, IntermediateNodeLevel1 leftINL1, IntermediateNodeLevel1 rightINL1) {
		this.upNode = upNode;
		this.prev = prev;
		this.next = next;
		this.leftINL1 = leftINL1;
		this.rightINL1 = rightINL1;
		this.numberOfDownNode = 1;
		this.gammaD = GammaD(upNode.getHight());
	}
	public void split() {
		if(numberOfDownNode > gammaD) {
			//the INL2 doesn't have a pair yet
			if(pair == null) {
				//create a new pair node and assign the new pair as rightmost INL2
				IntermediateNodeLevel2 newINL2 = new IntermediateNodeLevel2(upNode, this, this.next, this.rightINL1, this.rightINL1);
				next = newINL2;
				pair = newINL2;
				newINL2.setPair(this);
				pair.incNumberOfDownNode();
			}else {
				//the current INL2 node already has a pair node
				pair.setLeftINL1(rightINL1);
				pair.incNumberOfDownNode();
				if(pair.getNumberOfDownNode() == gammaD) {
					pair.setPair(null);
					pair = null;
					if(upNode.getRightINL2() == this)
						upNode.setRightINL2(next);
				}
			}
			rightINL1 = rightINL1.getPrev();
			numberOfDownNode--;
			return;
		}
		if(numberOfDownNode == gammaD) {
			if(pair != null) {
				pair.setPair(null);
				pair = null;
				if(upNode.getRightINL2() == prev)
					upNode.setRightINL2(this);
			}
		}
	}
	
	public void fuse() {
		
	}
	
	private long GammaD(int d) {
		long result;
		long power2d1 = (long) Math.pow(2, (d - 1));
		long power1 = 3 * (d - 1) * power2d1;
		long power2 = (long) Math.pow(2, d) + power2d1 - 2;
		result = (long) Math.pow(2, power1) * (long) Math.pow(2, power2);
		return result;
	}
	public void incNumberOfDownNode() {
		numberOfDownNode++;
	}
	public void decNumberOfDownNode() {
		numberOfDownNode--;
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
	public IntermediateNodeLevel2 getPrev() {
		return prev;
	}
	public void setPrev(IntermediateNodeLevel2 prev) {
		this.prev = prev;
	}
	public IntermediateNodeLevel2 getNext() {
		return next;
	}
	public void setNext(IntermediateNodeLevel2 next) {
		this.next = next;
	}
	public long getGammaD() {
		return gammaD;
	}
	
}

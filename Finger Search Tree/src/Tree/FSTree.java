package Tree;

import java.util.LinkedList;

import Paint.MyFrame;

public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;
	private Storage storage;
	static long count = 0;

	public FSTree() {
		root = new InternalNode(1, null, null, null, null, null);
		LinkedList<Triple> triples = new LinkedList<Triple>();
		firstLeaf = new Leaf(0, -1, null, null, triples, null);
		IntermediateNodeLevel2 firstINL2 = new IntermediateNodeLevel2(root, null, null, null, null);
		IntermediateNodeLevel1 firstINL1 = new IntermediateNodeLevel1(firstINL2, null, null, firstLeaf, firstLeaf);
		firstLeaf.setUpNode(firstINL1);
		firstINL2.setLeftINL1(firstINL1);
		firstINL2.setRightINL1(firstINL1);
		root.setLeftINL2(firstINL2);
		root.setRightINL2(firstINL2);
	}

	public Leaf ownSearch(Leaf f, int x) {
		storage = new Storage();
		storage.setX(x);
		IntermediateNodeLevel1 upNode = f.getUpNode();
		// The leaf f has the same value as x
		if (f.getValue() == x) {
			return f;
		}
		// The leaf f has smaller value than x, x is located on the right side of f
		if (f.getValue() < x) {
			storage.setLeftBarrier(f);
			return helpSearch1(upNode, true);
		}

		// The leaf f has larger value than x, x is located on the left side of f
		storage.setLeftBarrier(firstLeaf);
		storage.setRightBarrier(f);
		return helpSearch1(upNode, false);
	}

	public Leaf helpSearch1(IntermediateNodeLevel1 current, boolean rightDirection) {
		count++;
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		IntermediateNodeLevel2 tempINL2;
		Leaf tempLeaf;
		if (rightDirection) {
			tempINL1 = current;
			// Already check the rightmost sub node
			if (tempINL1.getRightMost() == storage.getLeftIN()) {
				storage.setLeftINL1(current);
				return helpSearch2(current.getUpNode(), true);
			}
			// Go to the rightmost leaf spanned by CURRENT
			while (tempINL1.getRightMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getRightMost();
				tempINL2 = tempIN.getRightINL2();
				if (tempINL2.getPair() != null)
					tempINL2 = tempINL2.getNext();
				tempINL1 = tempINL2.getRightINL1();
				if (tempINL1.getPair() != null)
					tempINL1 = tempINL1.getNext();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getRightMost(); // Rightmost leaf

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
//				System.out.println("INL1: The rightmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + storage.getX());
				// x is beyond the range spanned by the current node
				// => Update the new base leaf f and search in the subtree of the current up node
				storage.setLeftINL1(current);
				storage.setLeftBarrier(tempLeaf);
				if (current.getUpNode() != storage.getFailINL2())
					return helpSearch2(current.getUpNode(), true);
				if (storage.getRightINL1() != null) {
					IntermediateNodeLevel1 rightINL1 = storage.getRightINL1();
					if (rightINL1 != null && rightINL1.getPrev() != storage.getLeftINL1()) {
						return helpSearch1(rightINL1.getPrev(), false);
					} else {
						System.out.println("The leaf x doesn't exist!!!");
						return storage.getLeftBarrier();
					}
				}
				return storage.getLeftBarrier();
			} else {
				// The rightmost leaf spanned by the current intermediate node level 1 is larger
//				System.out.println("INL1: The rightmost leaf is " + tempLeaf.getValue() + ",larger than X:" + storage.getX());
				if (storage.getRightBarrier() != null && tempLeaf.getValue() >= storage.getRightBarrier().getValue()) {
					storage.setFailINL1(current);
					if (current.getLeftMost() instanceof InternalNode) {
						InternalNode rightIN = storage.getRightIN();
						if (rightIN != null && rightIN.getPrev() != null && rightIN.getPrev() != storage.getLeftIN()) {
							return helpSearch(rightIN.getPrev(), false);
						} else {
							System.out.println("The leaf x doesn't exist!!!");
							return storage.getLeftBarrier();
						}
					}
					if (storage.getLeftBarrier().getUpNode() == storage.getRightBarrier().getUpNode())
						return storage.getLeftBarrier();
				}
					
				storage.setRightBarrier(tempLeaf);
				return helpSearch1(tempLeaf.getUpNode(), false);
			}
		} else {
			// Search on the left side
			tempINL1 = current;
			// Already check the leftmost sub node
			if (tempINL1.getLeftMost() == storage.getRightIN()) {
				storage.setRightINL1(current);
				return helpSearch2(current.getUpNode(), false);
			}
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
//				System.out.println("INL1: The leftmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + storage.getX());
				if (storage.getLeftBarrier() != null && tempLeaf.getValue() <= storage.getLeftBarrier().getValue()) {
					storage.setFailINL1(current);
					if (current.getLeftMost() instanceof InternalNode) {
						InternalNode leftIN = storage.getLeftIN();
						if (leftIN != null && leftIN.getNext() != null && leftIN.getNext() != storage.getRightIN()) {
							return helpSearch(leftIN.getNext(), true);
						} else {
							System.out.println("The leaf x doesn't exist!!!");
							return storage.getLeftBarrier();
						}
					}
					if (storage.getRightBarrier().getUpNode() == storage.getLeftBarrier().getUpNode())
						return storage.getLeftBarrier();
				}
				storage.setLeftBarrier(tempLeaf);
				return helpSearch1(tempLeaf.getUpNode(), true);
			} else {
//				System.out.println("INL1: The leftmost leaf is " + tempLeaf.getValue() + ",larger than X:" + storage.getX());
				// x is beyond the range spanned by the current node
				storage.setRightINL1(current);
				storage.setRightBarrier(tempLeaf);
				if (current.getUpNode() != storage.getFailINL2())
					return helpSearch2(current.getUpNode(), false);
				if (storage.getLeftINL1() != null) {
					IntermediateNodeLevel1 leftINL1 = storage.getLeftINL1();
					if (leftINL1 != null && leftINL1.getNext() != storage.getRightINL1()) {
						return helpSearch1(leftINL1.getNext(), true);
					} else {
						System.out.println("The leaf x doesn't exist!!!");
						return storage.getLeftBarrier();
					}
				}
				// TODO: check
				return storage.getLeftBarrier();
			}
		}
	}

	public Leaf helpSearch2(IntermediateNodeLevel2 current, boolean rightDirection) {
		count++;
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		IntermediateNodeLevel2 tempINL2;
		Leaf tempLeaf;

		// Go on the right side
		if (rightDirection) {
			tempINL1 = current.getRightINL1();
			if (tempINL1.getPair() != null)
				tempINL1 = tempINL1.getNext();
			// Already check the leftmost sub node
			if (tempINL1 == storage.getLeftINL1()) {
				storage.setLeftINL2(current);
				return helpSearch(current.getUpNode(), true);
			}
			while (tempINL1.getRightMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getRightMost();
				tempINL2 = tempIN.getRightINL2();
				if (tempINL2.getPair() != null)
					tempINL2 = tempINL2.getNext();
				tempINL1 = tempINL2.getRightINL1();
				if (tempINL1.getPair() != null)
					tempINL1 = tempINL1.getNext();
			}

			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getRightMost(); // Rightmost leaf spanned by current INL1

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
//				System.out.println("INL2: The rightmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + storage.getX());
				storage.setLeftINL2(current);
				storage.setLeftBarrier(tempLeaf);
				if (current.getUpNode() != storage.getFailIN())
					return helpSearch(current.getUpNode(), true);
				if (storage.getRightINL2() != null && storage.getRightINL2().getPrev() != storage.getLeftINL2()) {
					return helpSearch2(storage.getRightINL2().getPrev(), false);
				}
				return storage.getLeftBarrier();
			} else {
				// The rightmost leaf spanned by the current intermediate node level 2 is larger
				// than x
//				System.out.println("INL2: The rightmost leaf is " + tempLeaf.getValue() + ",larger than X:" + storage.getX());
				if (storage.getRightBarrier() != null && tempLeaf.getValue() > storage.getRightBarrier().getValue()) {
					storage.setFailINL2(current);
					IntermediateNodeLevel1 rightINL1 = storage.getRightINL1();
					if (rightINL1 != null && rightINL1.getPrev() != storage.getLeftINL1()) {
						return helpSearch1(rightINL1.getPrev(), false);
					}
					else {
						System.out.println("The leaf x doesn't exist!!!");
						return storage.getLeftBarrier();
					}
				}
				storage.setRightBarrier(tempLeaf);
				return helpSearch1(tempINL1, false);
			}
		} else {
			// Search on the left side
			tempINL1 = current.getLeftINL1();
			// Already check the leftmost sub node
			if (tempINL1 == storage.getRightINL1()) {
				storage.setRightINL2(current);
				return helpSearch(current.getUpNode(), false);
			}
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
//				System.out.println("INL2: The leftmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + storage.getX());
				if (storage.getLeftBarrier() != null && tempLeaf.getValue() < storage.getLeftBarrier().getValue()) {
					storage.setFailINL2(current);
					IntermediateNodeLevel1 leftINL1 = storage.getLeftINL1();
					if (leftINL1 != null && leftINL1.getNext() != storage.getRightINL1())
						return helpSearch1(leftINL1.getNext(), true);
					else {
						System.out.println("The leaf x doesn't exist!!!");
						return storage.getLeftBarrier();
					}
				}
					
				storage.setLeftBarrier(tempLeaf);
				return helpSearch1(tempINL1, true);
			} else {
//				System.out.println("INL2: The leftmost leaf is " + tempLeaf.getValue() + ",larger than X:" + storage.getX());
				storage.setRightINL2(current);
				storage.setRightBarrier(tempLeaf);
				if (current.getUpNode() != storage.getFailIN())
					return helpSearch(current.getUpNode(), false);
				if (storage.getLeftINL2() != null && storage.getLeftINL2().getNext() != storage.getRightINL2())
					return helpSearch2(storage.getLeftINL2().getNext(), true);
				return storage.getLeftBarrier();
			}
		}
	}

	public Leaf helpSearch(InternalNode current, boolean rightDirection) {
		count++;
		InternalNode tempIN = current;
		IntermediateNodeLevel2 tempINL2;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;

		// Go on the right side
		if (rightDirection == true) {
			tempINL2 = current.getRightINL2();
			if (tempINL2.getPair() != null)
				tempINL2 = tempINL2.getNext();
			// Already check the leftmost sub node
			if (tempINL2 == storage.getLeftINL2()) {
				if (current.getUpNode() != null) {
					storage.setLeftIN(current);
					return helpSearch1(current.getUpNode(), true);
				} else {
					System.out.println("The leaf x doesn't exist!!!");
					return storage.getLeftBarrier();
				}
			}
			tempINL1 = tempINL2.getRightINL1();
			if (tempINL1.getPair() != null)
				tempINL1 = tempINL1.getNext();
			while (tempINL1.getRightMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getRightMost();
				tempINL2 = tempIN.getRightINL2();
				if (tempINL2.getPair() != null)
					tempINL2 = tempINL2.getNext();
				tempINL1 = tempINL2.getRightINL1();
				if (tempINL1.getPair() != null)
					tempINL1 = tempINL1.getNext();
			}
			// Get the rightmost leaf spanned by the current internal node
			tempLeaf = (Leaf) tempINL1.getRightMost();

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
//				System.out.println("IN: The rightmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + storage.getX());
				storage.setLeftIN(current);
				if (current.getUpNode() != null) {
					storage.setLeftBarrier(tempLeaf);
					if (current.getUpNode() != storage.getFailINL1())
						return helpSearch1(current.getUpNode(), true);
					if (storage.getRightIN() != null && storage.getRightIN().getPrev() != storage.getLeftIN())
						return helpSearch(storage.getRightIN().getPrev(), false);
					return storage.getLeftBarrier();
				} else {
					System.out.println("The leaf x doesn't exist!!!");
					return tempLeaf;
				}
			} else {
				// the rightmost leaf spanned by the current internal node is larger than x
//				System.out.println("IN: The rightmost leaf is " + tempLeaf.getValue() + ",bigger than X:" + storage.getX());
				if (storage.getRightBarrier() != null && tempLeaf.getValue() > storage.getRightBarrier().getValue()) {
					storage.setFailIN(current);
					IntermediateNodeLevel2 rightINL2 = storage.getRightINL2();
					if (rightINL2 != null && rightINL2.getPrev() != storage.getLeftINL2())
						return helpSearch2(rightINL2.getPrev(), false);
					else {
						System.out.println("The leaf x doesn't exist!!!");
						return storage.getLeftBarrier();
					}
				}
					
				storage.setRightBarrier(tempLeaf);
				return helpSearch1(tempINL1, false);

			}
		} else {
			// Search on the left side
			tempINL2 = current.getLeftINL2();
			// Already check the leftmost sub node
			if (tempINL2 == storage.getRightINL2()) {
				if (current.getUpNode() != null) {
					storage.setRightIN(current);
					return helpSearch1(current.getUpNode(), false);
				} else {
					System.out.println("The leaf x doesn't exist!!!");
					return storage.getLeftBarrier();
				}
			}
			tempINL1 = tempINL2.getLeftINL1();
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
//				System.out.println("IN: The leftmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + storage.getX());
				if (storage.getLeftBarrier() != null && tempLeaf.getValue() < storage.getLeftBarrier().getValue()) {
					storage.setFailIN(current);
					IntermediateNodeLevel2 leftINL2 = storage.getLeftINL2();
					if (leftINL2 != null && leftINL2.getNext() != storage.getRightINL2())
						return helpSearch2(leftINL2.getNext(), true);
					else {
						System.out.println("The leaf x doesn't exist!!!");
						return storage.getLeftBarrier();
					}
				}
					
				storage.setLeftBarrier(tempLeaf);
				return helpSearch1(tempINL1, true);
			} else {
//				System.out.println("IN: The leftmost leaf is " + tempLeaf.getValue() + ",larger than X:" + storage.getX());
				if (current.getUpNode() != null) {
					storage.setRightIN(current);
					storage.setRightBarrier(tempLeaf);
					if (current.getUpNode() != null)
						return helpSearch1(current.getUpNode(), false);
					if (storage.getLeftIN() != null && storage.getLeftIN().getNext() != storage.getRightIN())
						return helpSearch(storage.getLeftIN().getNext(), true);
					return storage.getLeftBarrier();
				} else {
					System.out.println("The leaf x doesn't exist!!!");
					return tempLeaf;
				}
			}
		}

		// Find x recursively through intermediate node levels
	}

	@SuppressWarnings("unchecked")
	public Leaf insert(Leaf f, int x) {
		LinkedList<Triple> newTriples;
		InternalNode ancestor;
		IntermediateNodeLevel1 upNode;

		// x already exists
		if (f.getValue() == x)
			return f;

		f.incCounter();
		f.updateTriple();
		newTriples = (LinkedList<Triple>) f.getTriples().clone();
		Leaf newLeaf = new Leaf(f.getCounter(), x, f, f.getNext(), newTriples, f.getUpNode());
		if (f.getNext() != null)
			f.getNext().setPrev(newLeaf);
		f.setNext(newLeaf);
		upNode = f.getUpNode();
		if (upNode.getRightMost() == f)
			upNode.setRightMost(newLeaf);
		upNode.incNumberOfDownNode();
		upNode.split();
		ancestor = f.getTriples().getFirst().getAncestor();
		ancestor.split(this);
		return newLeaf;
	}

	public void delete(Leaf f) {
		IntermediateNodeLevel1 upNode = f.getUpNode();
		Leaf prev = f.getPrev();
		Leaf next = f.getNext();

		// Remove the leaf from the double linked list
		prev.setNext(next);
		if (next != null)
			next.setPrev(prev);

		// Change the leftmost or rightmost child of the upNode if necessary
		if (upNode.getNumberOfDownNode() > 1) {
			if (f == upNode.getLeftMost())
				upNode.setLeftMost(next);
			if (f == upNode.getRightMost())
				upNode.setRightMost(prev);
		}

		upNode.decNumberOfDownNode();
		upNode.fuse(this);
	}

	public FSTree linearSpace() {
		return null;
	}

	public void paintImage() {
		new MyFrame(this);
	}

	public InternalNode getRoot() {
		return root;
	}

	public void setRoot(InternalNode root) {
		this.root = root;
	}

	public Leaf getFirstLeaf() {
		return firstLeaf;
	}

	public void setFirstLeaf(Leaf firstLeaf) {
		this.firstLeaf = firstLeaf;
	}

}
package Tree;

import java.util.LinkedList;

import Paint.MyFrame;

public class FSTree {
	private InternalNode root;
	private Leaf firstLeaf;
	private OwnStorage ownStorage;
	private Storage storage;
	public long searchCount = 0;
	public long ownSearchCount = 0;
	public static long splitINL1 = 0;
	public static long splitINL2 = 0;
	public static long splitTotal = 0;
	public static long mergeINL1 = 0;
	public static long mergeINL2 = 0;
	public static long mergeTotal = 0;

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
	
	public Leaf linearSearch(Leaf f, int x) {
		Leaf temp = f;
		while (temp != null && temp.getValue() < x) {
			if (temp.getValue() == x)
				return temp;
			if (temp.getNext() == null)
				return temp;
			temp = temp.getNext();
		}
		return temp;
	}
	
	public Leaf search(Leaf f, int x) {
		if (f.getValue() == x) {
			return f;
		}

		// Check if x is neighbor of f
		if (f.getPrev() != null)
			if (f.getPrev().getValue() == x)
				return f.getPrev();
		if (f.getNext() != null)
			if (f.getNext().getValue() == x)
				return f.getNext();

		storage = new Storage();
		storage.setX(x);
		storage.setINL1(f.getUpNode());
		storage.setINL2(f.getUpNode().getUpNode());
		storage.setF(f);
		InternalNode father = f.getUpNode().getUpNode().getUpNode();

		// Look up on the father node
		return helpSearch(father);
	}

	public Leaf helpSearch(InternalNode current) {
		searchCount++;
		InternalNode tempIN = current;
		IntermediateNodeLevel2 tempINL2;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;

		// Go on the right side
		if (storage.getF().getValue() < storage.getX()) {
			tempINL2 = current.getRightINL2();
			if (tempINL2.getPair() != null)
				tempINL2 = tempINL2.getNext();

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
				// x is outside the range spanned by the current internal node
				if (current.getUpNode() != null) {
					storage.setINL1(current.getUpNode());
					storage.setINL2(current.getUpNode().getUpNode());
					return helpSearch(current.getUpNode().getUpNode().getUpNode());
				}
				return tempLeaf;

			} else {
				// Found the range that contains x
				return helpSearch2(storage.getINL2());
			}
		} else {
			// Search on the left side
			tempINL2 = current.getLeftINL2();
			tempINL1 = tempINL2.getLeftINL1();
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempLeaf = (Leaf) tempINL1.getLeftMost();
			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
				// x is in the range spanned by the current internal node
				return helpSearch2(storage.getINL2());
			} else {
				if (current.getUpNode() != null) {
					storage.setINL1(current.getUpNode());
					storage.setINL2(current.getUpNode().getUpNode());
					return helpSearch(current.getUpNode().getUpNode().getUpNode());
				}
				return tempLeaf;
			}

		}
	}

	public Leaf helpSearch2(IntermediateNodeLevel2 current) {
		searchCount++;
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		IntermediateNodeLevel2 tempINL2;
		Leaf tempLeaf;

		// Go on the right side
		if (storage.getF().getValue() < storage.getX()) {
			tempINL1 = current.getRightINL1();
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

			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getRightMost(); // Rightmost leaf spanned by current INL2

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;
			// x is outside the range spanned by the current intermediate level 2 node
			if (tempLeaf.getValue() < storage.getX()) {
				// The next INL2 is not null
				if (current.getNext() != null) {
					storage.setINL1(current.getNext().getLeftINL1());
					return helpSearch2(current.getNext());
				} else
					return tempLeaf;
			} else
				return helpSearch1(storage.getINL1());
		} else {
			// Search on the left side
			tempINL1 = current.getLeftINL1();
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;
			// x is in the range spanned by current INL2
			if (tempLeaf.getValue() < storage.getX())
				return helpSearch1(storage.getINL1());
			else {
				// x is outside of the range => search in the previous INL2
				if (current.getPrev() != null) {
					tempINL1 = current.getPrev().getRightINL1();
					if (tempINL1.getPair() != null)
						storage.setINL1(tempINL1.getPair());
					else
						storage.setINL1(tempINL1);
					return helpSearch2(current.getPrev());
				}
				return tempLeaf;

			}
		}
	}

	public Leaf helpSearch1(IntermediateNodeLevel1 current) {
		searchCount++;
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		IntermediateNodeLevel2 tempINL2;
		Leaf tempLeaf;
		if (storage.getF().getValue() < storage.getX()) {
			tempINL1 = current;
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

			// x is outside the range spanned by the current intermediate level 2 node
			if (tempLeaf.getValue() < storage.getX()) {
				// The next INL2 is not null
				if (current.getNext() != null) {
					return helpSearch1(current.getNext());
				}
				return tempLeaf;
			} else {
				// x is in the range
				if (current.getLeftMost() instanceof InternalNode) {
					// The sub node is a internal node
					InternalNode subNode = (InternalNode) current.getLeftMost();
					storage.setINL2(subNode.getLeftINL2());
					storage.setINL1(subNode.getLeftINL2().getLeftINL1());
					return helpSearch2(storage.getINL2());
				} else {
					// The sub node is a leaf
					tempLeaf = (Leaf) current.getLeftMost();
					if (tempLeaf.getValue() == storage.getX())
						return tempLeaf;
					if (tempLeaf.getValue() > storage.getX())
						if (tempLeaf.getPrev() != null)
							return tempLeaf.getPrev();
					return tempLeaf;
				}
			}
		} else {
			// Search on the left side
			tempINL1 = current;
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == storage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < storage.getX()) {
				// x is in the range spanned by current INL1
				if (current.getRightMost() instanceof InternalNode) {
					InternalNode subNode = (InternalNode) current.getRightMost();
					tempINL2 = subNode.getRightINL2();
					if (subNode.getRightINL2().getPair() != null) {
						tempINL2 = tempINL2.getNext();
						storage.setINL2(tempINL2);
					}else
						storage.setINL2(tempINL2);
					tempINL1 = tempINL2.getRightINL1();
					if(tempINL1.getPair() != null)
						storage.setINL1(tempINL1.getNext());
					else
						storage.setINL1(tempINL1);
					return helpSearch2(storage.getINL2());
				}
				tempLeaf = (Leaf) current.getRightMost();
				if(tempLeaf.getValue() == storage.getX())
					return tempLeaf;
				if(tempLeaf.getValue() > storage.getX())
					return tempLeaf.getPrev();
				return tempLeaf;
			}else {
				if(current.getPrev() != null) {
					return helpSearch1(current.getPrev());
				}
				return tempLeaf;
			}
		}
	}

	public Leaf ownSearch(Leaf f, int x) {
		ownStorage = new OwnStorage();
		ownStorage.setX(x);
		IntermediateNodeLevel1 upNode = f.getUpNode();
		// The leaf f has the same value as x
		if (f.getValue() == x) {
			return f;
		}
		// The leaf f has smaller value than x, x is located on the right side of f
		if (f.getValue() < x) {
			ownStorage.setLeftBarrier(f);
			return helpOwnSearch1(upNode, true);
		}

		// The leaf f has larger value than x, x is located on the left side of f
		ownStorage.setLeftBarrier(firstLeaf);
		ownStorage.setRightBarrier(f);
		return helpOwnSearch1(upNode, false);
	}

	public Leaf helpOwnSearch1(IntermediateNodeLevel1 current, boolean rightDirection) {
		ownSearchCount++;
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		IntermediateNodeLevel2 tempINL2;
		Leaf tempLeaf;
		if (rightDirection) {
			tempINL1 = current;
			// Already check the rightmost sub node
			if (tempINL1.getRightMost() == ownStorage.getLeftIN()) {
				ownStorage.setLeftINL1(current);
				return helpOwnSearch2(current.getUpNode(), true);
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

			if (tempLeaf.getValue() == ownStorage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < ownStorage.getX()) {
				// x is beyond the range spanned by the current node
				// => Update the new base leaf f and search in the subtree of the current up node
				ownStorage.setLeftINL1(current);
				ownStorage.setLeftBarrier(tempLeaf);
				if (current.getUpNode() != ownStorage.getFailINL2())
					return helpOwnSearch2(current.getUpNode(), true);
				if (ownStorage.getRightINL1() != null) {
					IntermediateNodeLevel1 rightINL1 = ownStorage.getRightINL1();
					if (rightINL1 != null && rightINL1.getPrev() != ownStorage.getLeftINL1()) {
						return helpOwnSearch1(rightINL1.getPrev(), false);
					} else {
						return ownStorage.getLeftBarrier();
					}
				}
				return ownStorage.getLeftBarrier();
			} else {
				// The rightmost leaf spanned by the current intermediate node level 1 is larger
				if (ownStorage.getRightBarrier() != null && tempLeaf.getValue() >= ownStorage.getRightBarrier().getValue()) {
					ownStorage.setFailINL1(current);
					if (current.getLeftMost() instanceof InternalNode) {
						InternalNode rightIN = ownStorage.getRightIN();
						if (rightIN != null && rightIN.getPrev() != null && rightIN.getPrev() != ownStorage.getLeftIN()) {
							return helpOwnSearch(rightIN.getPrev(), false);
						} else {
							return ownStorage.getLeftBarrier();
						}
					}
					if (ownStorage.getLeftBarrier().getUpNode() == ownStorage.getRightBarrier().getUpNode())
						return ownStorage.getLeftBarrier();
				}

				ownStorage.setRightBarrier(tempLeaf);
				return helpOwnSearch1(tempLeaf.getUpNode(), false);
			}
		} else {
			// Search on the left side
			tempINL1 = current;
			// Already check the leftmost sub node
			if (tempINL1.getLeftMost() == ownStorage.getRightIN()) {
				ownStorage.setRightINL1(current);
				return helpOwnSearch2(current.getUpNode(), false);
			}
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == ownStorage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < ownStorage.getX()) {
				if (ownStorage.getLeftBarrier() != null && tempLeaf.getValue() <= ownStorage.getLeftBarrier().getValue()) {
					ownStorage.setFailINL1(current);
					if (current.getLeftMost() instanceof InternalNode) {
						InternalNode leftIN = ownStorage.getLeftIN();
						if (leftIN != null && leftIN.getNext() != null && leftIN.getNext() != ownStorage.getRightIN()) {
							return helpOwnSearch(leftIN.getNext(), true);
						} else {
							return ownStorage.getLeftBarrier();
						}
					}
					if (ownStorage.getRightBarrier().getUpNode() == ownStorage.getLeftBarrier().getUpNode())
						return ownStorage.getLeftBarrier();
				}
				ownStorage.setLeftBarrier(tempLeaf);
				return helpOwnSearch1(tempLeaf.getUpNode(), true);
			} else {
				// x is beyond the range spanned by the current node
				ownStorage.setRightINL1(current);
				ownStorage.setRightBarrier(tempLeaf);
				if (current.getUpNode() != ownStorage.getFailINL2())
					return helpOwnSearch2(current.getUpNode(), false);
				if (ownStorage.getLeftINL1() != null) {
					IntermediateNodeLevel1 leftINL1 = ownStorage.getLeftINL1();
					if (leftINL1 != null && leftINL1.getNext() != ownStorage.getRightINL1()) {
						return helpOwnSearch1(leftINL1.getNext(), true);
					} else {
						return ownStorage.getLeftBarrier();
					}
				}
				return ownStorage.getLeftBarrier();
			}
		}
	}

	public Leaf helpOwnSearch2(IntermediateNodeLevel2 current, boolean rightDirection) {
		ownSearchCount++;
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
			if (tempINL1 == ownStorage.getLeftINL1()) {
				ownStorage.setLeftINL2(current);
				return helpOwnSearch(current.getUpNode(), true);
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

			if (tempLeaf.getValue() == ownStorage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < ownStorage.getX()) {
				ownStorage.setLeftINL2(current);
				ownStorage.setLeftBarrier(tempLeaf);
				if (current.getUpNode() != ownStorage.getFailIN())
					return helpOwnSearch(current.getUpNode(), true);
				if (ownStorage.getRightINL2() != null && ownStorage.getRightINL2().getPrev() != ownStorage.getLeftINL2()) {
					return helpOwnSearch2(ownStorage.getRightINL2().getPrev(), false);
				}
				return ownStorage.getLeftBarrier();
			} else {
				// The rightmost leaf spanned by the current intermediate node level 2 is larger than x
				if (ownStorage.getRightBarrier() != null && tempLeaf.getValue() > ownStorage.getRightBarrier().getValue()) {
					ownStorage.setFailINL2(current);
					IntermediateNodeLevel1 rightINL1 = ownStorage.getRightINL1();
					if (rightINL1 != null && rightINL1.getPrev() != ownStorage.getLeftINL1()) {
						return helpOwnSearch1(rightINL1.getPrev(), false);
					} else {
						return ownStorage.getLeftBarrier();
					}
				}
				ownStorage.setRightBarrier(tempLeaf);
				return helpOwnSearch1(tempINL1, false);
			}
		} else {
			// Search on the left side
			tempINL1 = current.getLeftINL1();
			// Already check the leftmost sub node
			if (tempINL1 == ownStorage.getRightINL1()) {
				ownStorage.setRightINL2(current);
				return helpOwnSearch(current.getUpNode(), false);
			}
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == ownStorage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < ownStorage.getX()) {
				if (ownStorage.getLeftBarrier() != null && tempLeaf.getValue() < ownStorage.getLeftBarrier().getValue()) {
					ownStorage.setFailINL2(current);
					IntermediateNodeLevel1 leftINL1 = ownStorage.getLeftINL1();
					if (leftINL1 != null && leftINL1.getNext() != ownStorage.getRightINL1())
						return helpOwnSearch1(leftINL1.getNext(), true);
					else {
						return ownStorage.getLeftBarrier();
					}
				}

				ownStorage.setLeftBarrier(tempLeaf);
				return helpOwnSearch1(tempINL1, true);
			} else {
				ownStorage.setRightINL2(current);
				ownStorage.setRightBarrier(tempLeaf);
				if (current.getUpNode() != ownStorage.getFailIN())
					return helpOwnSearch(current.getUpNode(), false);
				if (ownStorage.getLeftINL2() != null && ownStorage.getLeftINL2().getNext() != ownStorage.getRightINL2())
					return helpOwnSearch2(ownStorage.getLeftINL2().getNext(), true);
				return ownStorage.getLeftBarrier();
			}
		}
	}

	public Leaf helpOwnSearch(InternalNode current, boolean rightDirection) {
		ownSearchCount++;
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
			if (tempINL2 == ownStorage.getLeftINL2()) {
				if (current.getUpNode() != null) {
					ownStorage.setLeftIN(current);
					return helpOwnSearch1(current.getUpNode(), true);
				} else {
					return ownStorage.getLeftBarrier();
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

			if (tempLeaf.getValue() == ownStorage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < ownStorage.getX()) {
				ownStorage.setLeftIN(current);
				if (current.getUpNode() != null) {
					ownStorage.setLeftBarrier(tempLeaf);
					if (current.getUpNode() != ownStorage.getFailINL1())
						return helpOwnSearch1(current.getUpNode(), true);
					if (ownStorage.getRightIN() != null && ownStorage.getRightIN().getPrev() != ownStorage.getLeftIN())
						return helpOwnSearch(ownStorage.getRightIN().getPrev(), false);
					return ownStorage.getLeftBarrier();
				} else {
					return tempLeaf;
				}
			} else {
				// the rightmost leaf spanned by the current internal node is larger than x
				if (ownStorage.getRightBarrier() != null && tempLeaf.getValue() > ownStorage.getRightBarrier().getValue()) {
					ownStorage.setFailIN(current);
					IntermediateNodeLevel2 rightINL2 = ownStorage.getRightINL2();
					if (rightINL2 != null && rightINL2.getPrev() != ownStorage.getLeftINL2())
						return helpOwnSearch2(rightINL2.getPrev(), false);
					else {
						return ownStorage.getLeftBarrier();
					}
				}

				ownStorage.setRightBarrier(tempLeaf);
				return helpOwnSearch1(tempINL1, false);

			}
		} else {
			// Search on the left side
			tempINL2 = current.getLeftINL2();
			// Already check the leftmost sub node
			if (tempINL2 == ownStorage.getRightINL2()) {
				if (current.getUpNode() != null) {
					ownStorage.setRightIN(current);
					return helpOwnSearch1(current.getUpNode(), false);
				} else {
					return ownStorage.getLeftBarrier();
				}
			}
			tempINL1 = tempINL2.getLeftINL1();
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == ownStorage.getX())
				return tempLeaf;

			if (tempLeaf.getValue() < ownStorage.getX()) {
				if (ownStorage.getLeftBarrier() != null && tempLeaf.getValue() < ownStorage.getLeftBarrier().getValue()) {
					ownStorage.setFailIN(current);
					IntermediateNodeLevel2 leftINL2 = ownStorage.getLeftINL2();
					if (leftINL2 != null && leftINL2.getNext() != ownStorage.getRightINL2())
						return helpOwnSearch2(leftINL2.getNext(), true);
					else {
						return ownStorage.getLeftBarrier();
					}
				}

				ownStorage.setLeftBarrier(tempLeaf);
				return helpOwnSearch1(tempINL1, true);
			} else {
				if (current.getUpNode() != null) {
					ownStorage.setRightIN(current);
					ownStorage.setRightBarrier(tempLeaf);
					if (current.getUpNode() != null)
						return helpOwnSearch1(current.getUpNode(), false);
					if (ownStorage.getLeftIN() != null && ownStorage.getLeftIN().getNext() != ownStorage.getRightIN())
						return helpOwnSearch(ownStorage.getLeftIN().getNext(), true);
					return ownStorage.getLeftBarrier();
				} else {
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
		ancestor = f.updateTriple();
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
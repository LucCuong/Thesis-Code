package Paint;

import java.awt.*;
import javax.swing.*;

import Tree.*;

public class PanelBackup extends JPanel {

	// Image image;

	/**
	 * 
	 */
	private FSTree tree;
	private static final long serialVersionUID = 1L;
	private int width = 1900;
	private int height = 1000;

//	public MyPanel(FSTree tree) {
//
//		this.tree = tree;
//		this.setPreferredSize(new Dimension(2000, 1500));
//	}

	public void paint(Graphics g) {
		int verticalDistance, heightDistance;
		int tempINL1X, tempINL1Y, tempINL2X, tempINL2Y, tempINX, tempINY, tempChildX, tempChildY, tempLeafX, tempLeafY; // temporary
																														// co-ordinate
		int leafDistance, INdistance, INL2distance, INL1distance, childDistance;
		int nbOfIN = 0, nbOfINL2 = 0, nbOfINL1 = 0, nbOfChild = 0;
		int INwidth, INL2width, INL1width, childWidth, leavesWidth;

		InternalNode tempIN, firstIN, child;
		IntermediateNodeLevel2 tempINL2;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;

		int middleScreen = (int) width / 2;
		int heightOfTree = tree.getRoot().getHight();
		Graphics2D g2D = (Graphics2D) g;
		int nbOfLeaf = 0;
		tempLeaf = tree.getFirstLeaf();

		// Get the number of all leaves
		while (tempLeaf != null) {
			nbOfLeaf++;
			tempLeaf = tempLeaf.getNext();
		}
		
		// Calculations
		leavesWidth = nbOfLeaf >= 25? 1800: (int) nbOfLeaf * 50 + 200;
		leafDistance = (int) leavesWidth / nbOfLeaf;
		tempLeafX = nbOfLeaf == 1 ? middleScreen : (int) middleScreen - (leavesWidth / 2);
		heightDistance = (int) (height - 100) / heightOfTree;
		verticalDistance = (int) heightDistance / 3; 

		// Paint
		tempIN = tree.getRoot();
		firstIN = tempIN;
		// Go up-bottom
		for (int i = heightOfTree; i > 0; i--) {
			// Assign co-ordinate and distance according to the current height
			tempINY = 50 + heightDistance * (heightOfTree - i); 
			

			// Count number of nodes in layers
			tempINL2 = firstIN.getLeftINL2();
			tempINL1 = tempINL2.getLeftINL1();
			while (tempINL1 != null) {
				nbOfINL1++;
				tempINL1 = tempINL1.getNext();
			}
			while (tempINL2 != null) {
				nbOfINL2++;
				tempINL2 = tempINL2.getNext();
			}
			while (firstIN != null) {
				nbOfIN++;
				firstIN = firstIN.getNext();
			}

			// Width of a layer
			INL1width = (int)leavesWidth * 11 / 13 / (i * 3/ 2);
			INL2width = (int)leavesWidth * 9 / 13 / (i * 3/ 2);
			INwidth = (int)leavesWidth * 7 / 13 / (i * 3/ 2);

			// Distance between nodes in a layer according to width of layer
			INdistance = nbOfIN == 1 ? INwidth : (int) INwidth / (nbOfIN - 1);
			INL2distance = nbOfINL2 == 1 ? INwidth : (int) INL2width / (nbOfINL2 - 1);
			INL1distance = nbOfINL1 == 1 ? INwidth : (int) INL1width / (nbOfINL1 - 1);

			tempINX = nbOfIN == 1 ? middleScreen : (int) middleScreen - (INwidth / 2);
			tempINL2X = nbOfINL2 == 1 ? middleScreen : (int) middleScreen - (INL2width / 2);
			tempINL1X = nbOfINL1 == 1 ? middleScreen : (int) middleScreen - (INL1width / 2);
			nbOfIN = 0;
			nbOfINL2 = 0;
			nbOfINL1 = 0;

			firstIN = tempIN;
			if (i != 1) {
				tempINL2 = firstIN.getLeftINL2();
				tempINL1 = tempINL2.getLeftINL1();
				child = (InternalNode) tempINL1.getLeftMost();
				while (child != null) {
					nbOfChild++;
					child = child.getNext();
				}

				// Calculate for the child layer
				childWidth = INwidth = (int)leavesWidth * 7 / 13 / ((i-1) * 3/ 2);
				childDistance = nbOfChild == 1 ? childWidth : childWidth / (nbOfChild - 1);
				tempChildX = nbOfChild == 1 ? middleScreen : (int) middleScreen - (childWidth / 2);
				nbOfChild = 0;
				// INTERNAL NODE LAYER
				while (tempIN != null) {

					// Paint the InternalNode
					g2D.setPaint(Color.black);
					g2D.drawOval(tempINX, tempINY, 15, 15);
					g2D.fillOval(tempINX, tempINY, 15, 15);

					// Paint the ID of the InternalNode
					g2D.setPaint(Color.black);
					g2D.drawString("" + tempIN.hashCode() % 1000, tempINX - 10, tempINY + 25);

					tempINL2Y = tempINY + verticalDistance;
					tempINL2 = tempIN.getLeftINL2();
					// Intermediate level 2 node layer
					while (tempINL2 != null && tempINL2.getUpNode() == tempIN) {
						g2D.setPaint(Color.red);
						g2D.drawOval(tempINL2X, tempINL2Y, 15, 15);
						g2D.fillOval(tempINL2X, tempINL2Y, 15, 15);

						// Draw line
						g2D.setPaint(Color.blue);
						g2D.setStroke(new BasicStroke(1));
						g2D.drawLine(tempINX + 7, tempINY + 7, tempINL2X + 7, tempINL2Y + 7);

						// Paint the ID of the IntermediateNodeLevel2
						g2D.setPaint(Color.red);
						g2D.drawString("" + tempINL2.hashCode() % 1000, tempINL2X - 10, tempINL2Y + 25);

						// Draw connection between two pair nodes
						if (tempINL2.getPair() != null && (tempINL2.getPair() == tempINL2.getNext())) {
							g2D.setPaint(Color.red);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 7, tempINL2Y + 7, tempINL2X + INL2distance + 7, tempINL2Y + 7);
						}

						tempINL1Y = tempINL2Y + verticalDistance;
						tempINL1 = tempINL2.getLeftINL1();
						while (tempINL1 != null && tempINL1.getUpNode() == tempINL2) {
							g2D.setPaint(Color.orange);
							g2D.drawOval(tempINL1X, tempINL1Y, 15, 15);
							g2D.fillOval(tempINL1X, tempINL1Y, 15, 15);

							// Draw line
							g2D.setPaint(Color.blue);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 7, tempINL2Y + 7, tempINL1X + 7, tempINL1Y + 7);

							// Paint the ID of the IntermediateNodeLevel1
							g2D.setPaint(Color.orange);
							g2D.drawString("" + tempINL1.hashCode() % 1000, tempINL1X - 10, tempINL1Y + 25);

							// Draw connection between two pair nodes
							if (tempINL1.getPair() != null && (tempINL1.getPair() == tempINL1.getNext())) {
								g2D.setPaint(Color.orange);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X, tempINL1Y + 7, tempINL1X + INL1distance + 7, tempINL1Y + 7);
							}

							child = (InternalNode) tempINL1.getLeftMost();
							tempChildY = tempINL1Y + verticalDistance;
							while (child != null && child.getUpNode() == tempINL1) {
//								System.out.println("Printing child node in co-ordinate (" + tempChildX + "," + tempChildY + ")");
								g2D.setPaint(Color.black);
								g2D.drawOval(tempChildX, tempChildY, 15, 15);
								g2D.fillOval(tempChildX, tempChildY, 15, 15);

								// Draw line
								g2D.setPaint(Color.blue);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X + 7, tempINL1Y + 7, tempChildX + 7, tempChildY + 7);

								tempChildX += childDistance;
								child = child.getNext();
							}
							tempINL1X += INL1distance;
							tempINL1 = tempINL1.getNext();
						}
						// Intermediate level 1 node layer
						tempINL2X += INL2distance;
						tempINL2 = tempINL2.getNext();
					}
					tempINX += INdistance;
					tempIN = tempIN.getNext();
				}

				tempIN = (InternalNode) firstIN.getLeftINL2().getLeftINL1().getLeftMost();
				firstIN = tempIN;
			} else {
				// INTERNAL NODE LAYER
				while (tempIN != null) {
//					System.out.println("Printing INTERNAL NODE in co-ordinate (" + tempINX + "," + tempINY + ")");

					// Paint the InternalNode
					g2D.setPaint(Color.black);
					g2D.drawOval(tempINX, tempINY, 15, 15);
					g2D.fillOval(tempINX, tempINY, 15, 15);

					// Paint the ID of the InternalNode
					g2D.setPaint(Color.black);
					g2D.drawString("" + tempIN.hashCode() % 1000, tempINX - 10, tempINY + 25);

					tempINL2Y = tempINY + verticalDistance;
					tempINL2 = tempIN.getLeftINL2();
					// Intermediate level 2 node layer
					while (tempINL2 != null && tempINL2.getUpNode() == tempIN) {
						g2D.setPaint(Color.red);
						g2D.drawOval(tempINL2X, tempINL2Y, 15, 15);
						g2D.fillOval(tempINL2X, tempINL2Y, 15, 15);

						// Draw line
						g2D.setPaint(Color.blue);
						g2D.setStroke(new BasicStroke(1));
						g2D.drawLine(tempINX + 7, tempINY + 7, tempINL2X + 7, tempINL2Y + 7);

						// Paint the ID of the IntermediateNodeLevel2
						g2D.setPaint(Color.red);
						g2D.drawString("" + tempINL2.hashCode() % 1000, tempINL2X - 10, tempINL2Y + 25);

						// Draw connection between two pair nodes
						if (tempINL2.getPair() != null && (tempINL2.getPair() == tempINL2.getNext())) {
							g2D.setPaint(Color.red);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 7, tempINL2Y + 7, tempINL2X + INL2distance + 7, tempINL2Y + 7);
						}

						tempINL1Y = tempINL2Y + verticalDistance;
						tempINL1 = tempINL2.getLeftINL1();
						while (tempINL1 != null && tempINL1.getUpNode() == tempINL2) {
							g2D.setPaint(Color.orange);
							g2D.drawOval(tempINL1X, tempINL1Y, 15, 15);
							g2D.fillOval(tempINL1X, tempINL1Y, 15, 15);

							// Draw line
							g2D.setPaint(Color.blue);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 7, tempINL2Y + 7, tempINL1X + 7, tempINL1Y + 7);
							
							// Draw connection between two pair nodes
							if (tempINL1.getPair() != null && (tempINL1.getPair() == tempINL1.getNext())) {
								g2D.setPaint(Color.orange);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X + 7, tempINL1Y + 7, tempINL1X + INL1distance + 7,
										tempINL1Y + 7);
							}
							
							// Paint the ID of the IntermediateNodeLevel1
							g2D.setPaint(Color.orange);
							g2D.drawString("" + tempINL1.hashCode() % 1000, tempINL1X - 10, tempINL1Y + 25);

							tempLeaf = (Leaf) tempINL1.getLeftMost();
							tempLeafY = tempINL1Y + verticalDistance;
							while (tempLeaf != null && tempLeaf.getUpNode() == tempINL1) {
								g2D.setPaint(Color.green);
								g2D.drawOval(tempLeafX, tempLeafY, 15, 15);
								g2D.fillOval(tempLeafX, tempLeafY, 15, 15);

								// Draw line
								g2D.setPaint(Color.blue);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X + 7, tempINL1Y + 7, tempLeafX + 7, tempLeafY + 7);

								// Draw value of leaves
								g2D.setPaint(Color.magenta);
								g2D.drawString("" + tempLeaf.getValue(), tempLeafX + 1, tempLeafY + 27);

								tempLeafX += leafDistance;
								tempLeaf = tempLeaf.getNext();
							}
							tempINL1X += INL1distance;
							tempINL1 = tempINL1.getNext();
						}
						// Intermediate level 1 node layer
						tempINL2X += INL2distance;
						tempINL2 = tempINL2.getNext();
					}
					tempINX += INdistance;
					tempIN = tempIN.getNext();
				}
			}

		}
	}
	public Leaf binarySearch(Leaf f, int x) {
		// The leaf f has the same value as x
		if (f.getValue() == x) {
			return f;
		}
		// The leaf f has smaller value than x, x is located on the right side of f
		if (f.getValue() < x)
			return helpSearch1(f, x, null, f.getUpNode(), true);
		// The leaf f has larger value than x, x is located on the left side of f
		return helpSearch1(null, x, f, f.getUpNode(), false);
	}

	public Leaf helpSearch1(Leaf leftBarrier, int x, Leaf rightBarrier, IntermediateNodeLevel1 current, boolean rightDirection) {
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;
		if (rightDirection) {
			tempINL1 = current;
			while (tempINL1.getRightMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getRightMost();
				tempINL1 = tempIN.getRightINL2().getRightINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getRightMost(); // Rightmost leaf spanned by current INL1

			if (tempLeaf.getValue() == x)
				return tempLeaf;

			if (tempLeaf.getValue() < x) {
				System.out.println("INL1: The rightmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + x);
				// x is beyond the range spanned by the current node
				// => Update the new base leaf f and search in the subtree of the current node's
				// father
				return helpSearch2(tempLeaf, x, null, current.getUpNode(), true);
			} else {
				// the rightmost leaf spanned by the current intermediate node level 2 is larger
				// than x
				System.out.println("INL1: The rightmost leaf is " + tempLeaf.getValue() + ",bigger than X:" + x);
				if (rightBarrier != null && tempLeaf.getValue() > rightBarrier.getValue()) {
					if (current.getLeftMost() instanceof InternalNode)
						return helpSearch(leftBarrier, x, rightBarrier, (InternalNode) current.getRightMost(), false);
					System.out.println("The leaf " + " doen't exist!!! returning..." + leftBarrier.getValue());
					return leftBarrier;
				}

				return helpSearch1(leftBarrier, x, tempLeaf, tempIN.getUpNode(), false);
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

			if (tempLeaf.getValue() == x)
				return tempLeaf;

			if (tempLeaf.getValue() < x) {
				System.out.println("INL1: The leftmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + x);
				if (leftBarrier != null && tempLeaf.getValue() < leftBarrier.getValue()) {
					if (current.getLeftMost() instanceof InternalNode)
						return helpSearch(leftBarrier, x, rightBarrier, (InternalNode) current.getLeftMost(), false);
					System.out.println("The leaf " + " doen't exist!!! returning..." + rightBarrier.getValue());
					return rightBarrier;
				}
				return helpSearch1(tempLeaf, x, rightBarrier, tempIN.getUpNode(), true);
			} else {
				System.out.println("INL1: The leftmost leaf is " + tempLeaf.getValue() + ",larger than X:" + x);
				// x is beyond the range spanned by the current node
				// => Update the new base leaf f and search in the subtree of the current node's
				// father
				return helpSearch2(leftBarrier, x, tempLeaf, current.getUpNode(), false);
			}
		}
	}
	
	public Leaf helpSearch2(Leaf leftBarrier, int x, Leaf rightBarrier, IntermediateNodeLevel2 current,
			boolean rightDirection) {
		InternalNode tempIN;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;

		// Go on the right side
		if (rightDirection) {
			tempINL1 = current.getRightINL1();
			while (tempINL1.getRightMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getRightMost();
				tempINL1 = tempIN.getRightINL2().getRightINL1();
			}

			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getRightMost(); // Rightmost leaf spanned by current INL1

			if (tempLeaf.getValue() == x)
				return tempLeaf;

			if (tempLeaf.getValue() < x) {
				System.out.println("INL2: The rightmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + x);
				return helpSearch(tempLeaf, x, null, current.getUpNode(), true);
			} else {
				// the rightmost leaf spanned by the current intermediate node level 2 is larger
				// than x
				System.out.println("INL2: The rightmost leaf is " + tempLeaf.getValue() + ",bigger than X:" + x);
				if (rightBarrier != null && tempLeaf.getValue() > rightBarrier.getValue()) {
					// TODO: it reaches best range spanned by current intermediate node level 2,
					// call helpSearch1
					IntermediateNodeLevel1 rightINL1 = current.getRightINL1();
					return helpSearch1(leftBarrier, x, rightBarrier, rightINL1, false);
				}

				return helpSearch2(leftBarrier, x, tempLeaf, tempINL1.getUpNode(), false);
			}
		} else {
			// Search on the left side
			tempINL1 = current.getLeftINL1();
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempIN = tempINL1.getUpNode().getUpNode();
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == x)
				return tempLeaf;

			if (tempLeaf.getValue() < x) {
				System.out.println("INL2: The leftmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + x);
				if (leftBarrier != null && tempLeaf.getValue() < leftBarrier.getValue()) {
					IntermediateNodeLevel1 leftINL1 = current.getLeftINL1();
					return helpSearch1(leftBarrier, x, rightBarrier, leftINL1, true);
				}
				return helpSearch2(tempLeaf, x, rightBarrier, tempINL1.getUpNode(), true);
			} else {
				System.out.println("INL2: The leftmost leaf is " + tempLeaf.getValue() + ",larger than X:" + x);
				return helpSearch(leftBarrier, x, tempLeaf, current.getUpNode(), false);
			}
		}
	}
	
	public Leaf helpSearch(Leaf leftBarrier, int x, Leaf rightBarrier, InternalNode current, boolean rightDirection) {
		InternalNode tempIN = current;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;

		// Go on the right side
		if (rightDirection == true) {
			tempINL1 = current.getRightINL2().getRightINL1();
			while (tempINL1.getRightMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getRightMost();
				tempINL1 = tempIN.getRightINL2().getRightINL1();
			}
			// Get the rightmost leaf spanned by the current internal node
			tempLeaf = (Leaf) tempINL1.getRightMost();

			if (tempLeaf.getValue() == x)
				return tempLeaf;

			if (tempLeaf.getValue() < x) {
				System.out.println("IN: The rightmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + x);
				// The leaf x is not in the tree
				if (current.getUpNode() != null)
					return helpSearch1(tempLeaf, x, null, current.getUpNode(), true);
				else {
					System.out.println("The leaf x doesn't exist!!!");
					tempLeaf = leftBarrier;
					while (tempLeaf.getNext() != null && tempLeaf.getValue() < x) {
						tempLeaf = tempLeaf.getNext();
					}
					return tempLeaf;
				}
			} else {
				// the rightmost leaf spanned by the current internal node is larger than x
				System.out.println("IN: The rightmost leaf is " + tempLeaf.getValue() + ",bigger than X:" + x);
				if (rightBarrier != null && tempLeaf.getValue() > rightBarrier.getValue()) {
					// TODO: it reaches best range spanned by current internal node, call
					// helpSearch2
					IntermediateNodeLevel2 rightINL2 = current.getRightINL2();
					return helpSearch2(leftBarrier, x, rightBarrier, rightINL2, false);
				}

				return helpSearch(leftBarrier, x, tempLeaf, tempIN, false);

			}
		} else {
			// Search on the left side
			tempINL1 = current.getLeftINL2().getLeftINL1();
			while (tempINL1.getLeftMost() instanceof InternalNode) {
				tempIN = (InternalNode) tempINL1.getLeftMost();
				tempINL1 = tempIN.getLeftINL2().getLeftINL1();
			}
			tempLeaf = (Leaf) tempINL1.getLeftMost();

			if (tempLeaf.getValue() == x)
				return tempLeaf;

			if (tempLeaf.getValue() < x) {
				System.out.println("IN: The leftmost leaf is " + tempLeaf.getValue() + ",smaller than X:" + x);
				if (leftBarrier != null && tempLeaf.getValue() < leftBarrier.getValue()) {
					IntermediateNodeLevel2 leftINL2 = current.getLeftINL2();
					return helpSearch2(leftBarrier, x, rightBarrier, leftINL2, true);
				}
				return helpSearch(tempLeaf, x, rightBarrier, tempIN, true);
			} else {
				System.out.println("IN: The leftmost leaf is " + tempLeaf.getValue() + ",larger than X:" + x);
				if (current.getUpNode() != null)
					return helpSearch(leftBarrier, x, tempLeaf, current.getUpNode().getUpNode().getUpNode(), false);
				else {
					System.out.println("The leaf x doesn't exist!!!");
					tempLeaf = leftBarrier;
					while (tempLeaf.getNext() != null && tempLeaf.getValue() < x) {
						tempLeaf = tempLeaf.getNext();
					}
					return tempLeaf;
				}
			}
		}

		// Find x recursively through intermediate node levels
	}

}



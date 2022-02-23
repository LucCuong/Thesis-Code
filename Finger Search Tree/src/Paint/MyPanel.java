package Paint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Tree.*;

public class MyPanel extends JPanel {

	// Image image;

	/**
	 * 
	 */
	private FSTree tree;
	private static final long serialVersionUID = 1L;
	private int width = 1900;
	private int height = 1000;

	public MyPanel(FSTree tree) {

		this.tree = tree;
		this.setPreferredSize(new Dimension(1900, 1000));
	}

	public void paint(Graphics g) {
		BufferedImage bi = new BufferedImage(1900, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = bi.createGraphics();
//		Graphics2D g2D = (Graphics2D) g;
		
		int verticalDistance, heightDistance;
		int tempINL1X, tempINL1Y, tempINL2X, tempINL2Y, tempINX, tempINY, tempChildX, tempChildY, tempLeafX, tempLeafY; // temporary co-ordinate
																														
		int leafDistance, INdistance, INL2distance, INL1distance, childDistance;
		int nbOfIN = 0, nbOfINL2 = 0, nbOfINL1 = 0, nbOfChild = 0;
		int INwidth, INL2width, INL1width, childWidth, leavesWidth;

		InternalNode tempIN, firstIN, child;
		IntermediateNodeLevel2 tempINL2;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;

		int middleScreen = (int) width / 2;
		int heightOfTree = tree.getRoot().getHight();
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
		try {
			ImageIO.write(bi, "PNG", new File("C:\\Users\\dinhc\\OneDrive\\Desktop\\Thesis\\Images\\TreeImage.PNG"));
			g2D.dispose();
		} catch (IOException e) {
			e.printStackTrace();
			g2D.dispose();
		}
	}
}

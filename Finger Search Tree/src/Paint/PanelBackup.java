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
//		// image = new ImageIcon("sky.png").getImage();
//		this.setPreferredSize(new Dimension(2000, 1500));
//	}

	public void paint(Graphics g) {
		int leafDistance, verticalDistance, heightDistance, nodesDistance;
		int tempINL1X, tempINL1Y,tempINL2X, tempINL2Y, tempINX, tempINY, tempChildX, tempChildY, tempLeafX, tempLeafY;		//temporary co-ordinate
		InternalNode tempIN, firstIN, child;
		IntermediateNodeLevel2 tempINL2;
		IntermediateNodeLevel1 tempINL1;
		Leaf tempLeaf;
		
		int heightOfTree = tree.getRoot().getHight();
		Graphics2D g2D = (Graphics2D) g;
		int numberOfLeaves = 0;
		Leaf temp = tree.getFirstLeaf();
		
		// Get the number of all leaves
		while(temp != null) {
			numberOfLeaves++;
			temp = temp.getNext();
		}
		System.out.print("number of leaves is: " + numberOfLeaves);
		
		// Calculations 
		leafDistance = (int) (width - 100) / numberOfLeaves;
		heightDistance = (int) (height - 100) / heightOfTree;
		verticalDistance = (int) heightDistance / 3; 							// distance between two layer in a height
		
		// Paint
		tempIN = tree.getRoot();
		firstIN = tempIN;
		// Go up-bottom
		for(int i = 0; i < heightOfTree; i++) {
			System.out.println("Loop in the height " + (heightOfTree - i) + " of the tree!!!");
			// Assign co-ordinate and distance according to the current height 
			nodesDistance = leafDistance + i * 20;								// distance of two nodes in the same line
			tempINY = 50 + heightDistance * i;									// vertical co-ordinate of internal node
			tempINX = 50; 														// horizontal co-ordinate of internal node
			tempINL1X = 50;
			tempINL2X = 50;
			tempChildX = 50;
			tempLeafX = 50;

			if(i != (heightOfTree-1)) {
				// INTERNAL NODE LAYER
				while(tempIN != null) {
					System.out.println("Printing INTERNAL NODE in co-ordinate (" + tempINX + "," + tempINY + ")");
					
					// Paint the InternalNode
					g2D.setPaint(Color.black);
					g2D.drawOval(tempINX, tempINY, 10, 10);
					g2D.fillOval(tempINX, tempINY, 10, 10);
					
					
					tempINL2Y = tempINY + verticalDistance;
					tempINL2 = tempIN.getLeftINL2();
					// Intermediate level 2 node layer 
					while(tempINL2 != null && tempINL2.getUpNode() == tempIN) {
//						System.out.println("Printing intermediate LEVEL 2 node in co-ordinate (" + tempINL2X + "," + tempINL2Y + ")");
						g2D.setPaint(Color.red);
						g2D.drawOval(tempINL2X, tempINL2Y, 10, 10);
						g2D.fillOval(tempINL2X, tempINL2Y, 10, 10);
						 
						// Draw line
						g2D.setPaint(Color.blue);
						g2D.setStroke(new BasicStroke(1));
						g2D.drawLine(tempINX + 4, tempINY + 4, tempINL2X, tempINL2Y);
						
						// Draw connection between two pair nodes
						if(tempINL2.getPair() != null && (tempINL2.getPair() == tempINL2.getNext())) {
							g2D.setPaint(Color.red);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 4, tempINL2Y, tempINL2X + nodesDistance, tempINL2Y);
						}
						
						tempINL1Y = tempINL2Y + verticalDistance;
						tempINL1  = tempINL2.getLeftINL1();
						while(tempINL1 != null && tempINL1.getUpNode() == tempINL2) {
//							System.out.println("Printing intermediate LEVEL 1 node in co-ordinate (" + tempINL1X + "," + tempINL1Y + ")");
							g2D.setPaint(Color.orange);
							g2D.drawOval(tempINL1X, tempINL1Y, 10, 10);
							g2D.fillOval(tempINL1X, tempINL1Y, 10, 10);
							
							// Draw line
							g2D.setPaint(Color.blue);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 4, tempINL2Y + 4, tempINL1X, tempINL1Y);
							
							// Draw connection between two pair nodes
							if(tempINL1.getPair() != null && (tempINL1.getPair() == tempINL1.getNext())) {
								g2D.setPaint(Color.orange);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X + 4, tempINL1Y, tempINL1X + nodesDistance, tempINL1Y);
							}
							
							child = (InternalNode) tempINL1.getLeftMost();
							tempChildY = 50 + heightDistance * (i+1);
							while(child != null && child.getUpNode() == tempINL1) {
								System.out.println("Printing child node in co-ordinate (" + tempChildX + "," + tempChildY + ")");
//								g2D.setPaint(Color.black);
//								g2D.drawOval(tempChildX, tempChildY, 10, 10);
//								g2D.fillOval(tempChildX, tempChildY, 10, 10);
								
								// Draw line
								g2D.setPaint(Color.blue);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X + 4, tempINL1Y + 4, tempChildX, tempChildY);
								
								tempChildX += nodesDistance;
								child = child.getNext();
							}
							tempINL1X += nodesDistance;
							tempINL1 = tempINL1.getNext();
						}
						// Intermediate level 1 node layer
						tempINL2X += nodesDistance;
						tempINL2 = tempINL2.getNext();
					}
					tempINX += nodesDistance;
					tempIN = tempIN.getNext();
				}
				
				tempIN = (InternalNode) firstIN.getLeftINL2().getLeftINL1().getLeftMost();
				firstIN = tempIN;
			}else {
				// INTERNAL NODE LAYER
				while(tempIN != null) {
					System.out.println("Printing INTERNAL NODE in co-ordinate (" + tempINX + "," + tempINY + ")");
					
					// Paint the InternalNode
					g2D.setPaint(Color.black);
					g2D.drawOval(tempINX, tempINY, 10, 10);
					g2D.fillOval(tempINX, tempINY, 10, 10);
					
					
					tempINL2Y = tempINY + verticalDistance;
					tempINL2 = tempIN.getLeftINL2();
					// Intermediate level 2 node layer 
					while(tempINL2 != null && tempINL2.getUpNode() == tempIN) {
//						System.out.println("Printing intermediate LEVEL 2 node in co-ordinate (" + tempINL2X + "," + tempINL2Y + ")");
						g2D.setPaint(Color.red);
						g2D.drawOval(tempINL2X, tempINL2Y, 10, 10);
						g2D.fillOval(tempINL2X, tempINL2Y, 10, 10);
						 
						// Draw line
						g2D.setPaint(Color.blue);
						g2D.setStroke(new BasicStroke(1));
						g2D.drawLine(tempINX + 4, tempINY + 4, tempINL2X, tempINL2Y);
						
						// Draw connection between two pair nodes
						if(tempINL2.getPair() != null && (tempINL2.getPair() == tempINL2.getNext())) {
							g2D.setPaint(Color.red);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 4, tempINL2Y, tempINL2X + nodesDistance, tempINL2Y);
						}
						
						tempINL1Y = tempINL2Y + verticalDistance;
						tempINL1  = tempINL2.getLeftINL1();
						while(tempINL1 != null && tempINL1.getUpNode() == tempINL2) {
//							System.out.println("Printing intermediate LEVEL 1 node in co-ordinate (" + tempINL1X + "," + tempINL1Y + ")");
							g2D.setPaint(Color.orange);
							g2D.drawOval(tempINL1X, tempINL1Y, 10, 10);
							g2D.fillOval(tempINL1X, tempINL1Y, 10, 10);
							
							// Draw line
							g2D.setPaint(Color.blue);
							g2D.setStroke(new BasicStroke(1));
							g2D.drawLine(tempINL2X + 4, tempINL2Y + 4, tempINL1X, tempINL1Y);
							
							tempLeaf = (Leaf) tempINL1.getLeftMost();
							tempLeafY = 50 + heightDistance * (i+1);
							while(tempLeaf != null && tempLeaf.getUpNode() == tempINL1) {
								g2D.setPaint(Color.green);
								g2D.drawOval(tempLeafX, tempLeafY, 10, 10);
								g2D.fillOval(tempLeafX, tempLeafY, 10, 10);
								
								// Draw line
								g2D.setPaint(Color.blue);
								g2D.setStroke(new BasicStroke(1));
								g2D.drawLine(tempINL1X + 4, tempINL1Y + 4, tempLeafX, tempLeafY);
								
								// Draw connection between two pair nodes
								if(tempINL1.getPair() != null && (tempINL1.getPair() == tempINL1.getNext())) {
									g2D.setPaint(Color.orange);
									g2D.setStroke(new BasicStroke(1));
									g2D.drawLine(tempINL1X + 4, tempINL1Y, tempINL1X + nodesDistance, tempINL1Y);
								}
								
								tempLeafX += leafDistance;
								tempLeaf = tempLeaf.getNext();
							}
							tempINL1X += nodesDistance;
							tempINL1 = tempINL1.getNext();
						}
						// Intermediate level 1 node layer
						tempINL2X += nodesDistance;
						tempINL2 = tempINL2.getNext();
					}
					tempINX += nodesDistance;
					tempIN = tempIN.getNext();
				}
			}
					
		}
	}
}

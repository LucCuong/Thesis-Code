package Paint;

import java.awt.*;
import javax.swing.*;

import Tree.*;

public class MyPanel extends JPanel {

	// Image image;

	/**
	 * 
	 */
	private FSTree tree;
	private static final long serialVersionUID = 1L;
	private int width = 2000;
	private int height = 1500;
	public MyPanel(FSTree tree) {

		this.tree = tree;
		// image = new ImageIcon("sky.png").getImage();
		this.setPreferredSize(new Dimension(width, height));
	}

	public void paint(Graphics g) {
		int horizalDistance, verticalDistance, sizeOfLeaf, heightDistance, nodesDistance;
		int temp1X, temp1Y,temp2X, temp2Y, tempPX, tempPY;		//temporary co-ordinate
		InternalNode tempIN, tempIN2;
		IntermediateNodeLevel2 tempINL2;
		IntermediateNodeLevel1 tempINL1;

		int heightOfTree = tree.getRoot().getHight();
		Graphics2D g2D = (Graphics2D) g;
		int numberOfLeaves = 0;
		Leaf temp = tree.getFirstLeaf();
		// Get the number of all leaves
		while(temp != null) {
			numberOfLeaves++;
		}
		// Calculations 
		if(numberOfLeaves != 0)
			horizalDistance = (int) (width - 100) / numberOfLeaves;
		horizalDistance = (int) (width - 100) / numberOfLeaves;
		sizeOfLeaf = (int) numberOfLeaves / 5 * 2;
		heightDistance = (int) (height - 100) / heightOfTree;
		verticalDistance = (int) heightDistance / 3;
		
		// Paint
		tempIN = tree.getRoot();
		tempIN2 = tempIN;
		for(int i = 0; i < heightOfTree; i++) {
			nodesDistance = horizalDistance + i * 20;
			tempPY = 50 + heightDistance * i;
			tempPX = 50 + nodesDistance; 
			while(tempIN != null) {
				tempINL2 = tempIN.getLeftINL2();
				tempINL1  = tempINL2.getLeftINL1();
				
				// Paint the InternalNode
				g2D.setPaint(Color.black);
				g2D.drawOval(tempPX, tempPY, 20, 20);
				g2D.fillOval(tempPX, tempPY, 20, 20);
				
				temp2Y = tempPY + verticalDistance;
				// Paint intermediate nodes 
				while(tempINL2 != null) {
//					temp2X = 
					g2D.setPaint(Color.red);
					g2D.drawOval(tempPX, tempPY, 20, 20);
					g2D.fillOval(tempPX, tempPY, 20, 20);
				}
				tempPX += nodesDistance;
				tempIN = tempIN.getNext();
			}
			if(i != 0)
				tempIN = (InternalNode) tempIN2.getLeftINL2().getLeftINL1().getLeftMost();
		}
		
		//  g2D.drawImage(image, 0, 0, null);



		g2D.setPaint(Color.pink);
		g2D.drawRect(0, 0, 100, 200);
		g2D.fillRect(0, 0, 100, 200);

		g2D.setPaint(Color.orange);
		g2D.drawOval(1000, 550, 20, 20);
		g2D.fillOval(0, 0, 20, 20);

		g2D.setPaint(Color.red);
		g2D.drawArc(0, 0, 100, 100, 0, 180);
		g2D.fillArc(0, 0, 100, 100, 0, 180);
		g2D.setPaint(Color.white);
		g2D.fillArc(0, 0, 100, 100, 180, 180);

		int[] xPoints = { 150, 250, 350 };
		int[] yPoints = { 300, 150, 300 };
		g2D.setPaint(Color.yellow);
		g2D.drawPolygon(xPoints, yPoints, 3);
		g2D.fillPolygon(xPoints, yPoints, 3);

		g2D.setPaint(Color.magenta);
		g2D.setFont(new Font("Ink Free", Font.BOLD, 50));
		g2D.drawString("U R A WINNER! :D", 50, 50);
		
		g2D.setPaint(Color.blue);
		g2D.setStroke(new BasicStroke(5));
		g2D.drawLine(50, 50, 400, 400);
	}
}

package Paint;

import javax.swing.*;

import Tree.FSTree;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyPanel panel;

	public MyFrame(FSTree tree) {

		panel = new MyPanel(tree);
		this.setTitle("Finger Search Tree");
		ImageIcon icon = new ImageIcon("tree.png");
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		this.setUndecorated(true);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}

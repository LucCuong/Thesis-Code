package Tree;

import Paint.MyFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
		//new MyFrame(tree);
		InternalNode test = new InternalNode(3, null, null, null, null, null);
		System.out.println("hight " + test.getHight() + ": deltaD = " + test.getDeltaD()
		+ ", thetaD = "  + test.getThetaD());
	}
	

}

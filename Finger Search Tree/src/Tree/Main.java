package Tree;

import Paint.MyFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
//		InternalNode root = tree.getRoot();
		Leaf first = tree.getFirstLeaf();
		for(int i = 1000; i >= 0; i--) {
			tree.insert(first, i);
		}
		new MyFrame(tree);
		
	}
	

}

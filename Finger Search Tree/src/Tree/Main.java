package Tree;

import Paint.MyFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		for(int i = 80; i >= 0; i--) {
			tree.insert(first, i);
		}
//		tree.insert(first, 0);
//		InternalNode root = tree.getRoot();
//		System.out.println(root == first.getTriples().getFirst().getAncestor());
		new MyFrame(tree);
		
	}
	

}

package Tree;

import Paint.MyFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
		for(int i = 0; i <100; i++) {
			tree.insert(tree.getFirstLeaf(), i);
		}
//		new MyFrame(tree);
		
	}
	

}

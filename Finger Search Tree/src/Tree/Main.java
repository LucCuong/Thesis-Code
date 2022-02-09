package Tree;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for(int i = 100; i >= 0; i--) {
			temp = tree.insert(temp, i);
		}
		
		temp = first.getNext();
		for(int i = 100; i >= 1; i--) {
			System.out.println("deleting " + i);
			tree.delete(temp);
			temp = temp.getNext();
			
		}
		tree.delete(temp);
//		tree.insert(first, 0);
//		InternalNode root = tree.getRoot();
//		System.out.println(root == first.getTriples().getFirst().getAncestor());
		tree.paintImage();
		
	}
	

}

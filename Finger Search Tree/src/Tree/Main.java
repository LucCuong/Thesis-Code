package Tree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 100; i >= 0; i--) {
			if (i == 0)
				temp = tree.insert(first, i*2);
			else
				tree.insert(first, i*2);
		}
		first = tree.binarySearch(temp, 83);
		System.out.println("the found leaf: " + first.getValue());
//		temp = first.getNext();
//		for(int i = 100; i >= 44; i--) {
////			System.out.println("deleting " + i);
//			tree.delete(temp);
//			temp = temp.getNext();
//			
//		}
//		tree.delete(temp);
//		tree.insert(first, 0);
//		InternalNode root = tree.getRoot();
//		System.out.println(root == first.getTriples().getFirst().getAncestor());
		tree.paintImage();

	}

}

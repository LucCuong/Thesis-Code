package Tree;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 7100000; i++) {
			temp = tree.insert(temp, i);
		}
		System.out.println("Finished insertion... ");
		first = tree.ownSearch(first, 3600005);
		System.out.println("the found leaf: " + first.getValue() + ", called funtions " + FSTree.count + " times");
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

package Tree;

import java.util.Random;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Random rand = new Random();
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
//		int upperbound = Integer.MAX_VALUE;
		int upperbound = 50000;
		int int_random;
		long start = System.nanoTime();
		for (int i = 0; i <= 10000; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			System.out.println(int_random);
			temp = tree.ownSearch(first, int_random);
			temp = tree.insert(temp, int_random);
		}
		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		System.out.println("Finished insertions in " + (double)timeElapsed/1000000000);
		
		
		start = System.nanoTime();
//		for(int i = 0; i <= 1000000; i++ ){
//			tree.ownSearch(first, i);
//		}
		temp = tree.ownSearch(first, 458579);
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished search in " + (double)timeElapsed/1000000000);
		System.out.println("the found leaf: " + temp.getValue() + ", called funtions " + FSTree.count + " times");
		
//		temp = first.getNext();
//		start = System.nanoTime();
//		for(int i = 0; i <= 7000000; i++ ){
//			tree.delete(temp);
//			temp = first.getNext();
//		}
//		finish = System.nanoTime();
//		timeElapsed = finish - start;
//		System.out.println("Deleted search in " + (double)timeElapsed/1000000000);
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
//		tree.paintImage();

	}

}

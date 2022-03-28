package Tree;

import java.util.Random;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Test.TestSuite;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		splitRandomCounter(4000);

	}
	
	public static void splitCount(int times) {
		FSTree tree = new FSTree();
		Leaf temp = tree.getFirstLeaf();
		for (int i = 0; i <= times; i++) {
			temp = tree.insert(temp, i );
		}
		System.out.println("Level 1 node: split " + FSTree.splitINL1 + " times");
		System.out.println("Level 2 node: split " + FSTree.splitINL2 + " times");
		System.out.println("Level 1 & 2 node: split " + FSTree.splitTotal + " times");
	}
	
	public static void splitRandomCounter(int times) {
		FSTree tree = new FSTree();
		Random rand = new Random();
		int upperbound = Integer.MAX_VALUE;
		int int_random;
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i <= times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			temp = tree.insert(temp, int_random );
		}
		System.out.println("Level 1 node: split " + FSTree.splitINL1 + " times");
		System.out.println("Level 2 node: split " + FSTree.splitINL2 + " times");
		System.out.println("Level 1 & 2 node: split " + FSTree.splitTotal + " times");
	}
	public static void mergeCounter(int times) {
		FSTree tree = new FSTree();
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i <= 3*times; i++) {
			temp = tree.insert(temp, i );
		}
		for (int i = 0; i <= times; i++) {
			first = temp;
			temp = temp.getPrev();
			tree.delete(first);
		}
		System.out.println("Level 1 node: merged " + FSTree.mergeINL1 + " times");
		System.out.println("Level 2 node: merged " + FSTree.mergeINL2 + " times");
		System.out.println("Level 1 & 2 node: merged " + FSTree.mergeTotal + " times");
	}
	
	public static void mergeRandomCounter(int times) {
		FSTree tree = new FSTree();
		Random rand = new Random();
		int upperbound = Integer.MAX_VALUE - 1;
		int int_random;
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i <= 3*times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			temp = tree.insert(temp, int_random );
		}
		for (int i = 0; i <= times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			System.out.println("Deleting " + int_random);
			tree.delete(temp);
		}
		System.out.println("Level 1 node: merged " + FSTree.mergeINL1 + " times");
		System.out.println("Level 2 node: merged " + FSTree.mergeINL2 + " times");
		System.out.println("Level 1 & 2 node: merged " + FSTree.mergeTotal + " times");
	}
	
	public static void searchRunTime(int times) {
		FSTree tree = new FSTree();
		long start, finish, timeElapsed;
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i <= times; i++) {
			temp = tree.insert(temp, i );
		}
		start = System.nanoTime();
		for (int i = 0; i <= times; i++) {
			tree.search(first, i );
		}
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished original search in " + (double) timeElapsed / 1000000000 + "s");
		start = System.nanoTime();
		for (int i = 0; i <= times; i++) {
			tree.ownSearch(first, i );
		}
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished alternative search in " + (double) timeElapsed / 1000000000 + "s");
		
	}
	
	public static void searchRepetition(int times) {
		FSTree tree = new FSTree();
		int x = times / 2;
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i <= times; i++) {
			temp = tree.insert(temp, i );
		}
		
		tree.search(first, x);
		System.out.println("The original search called itself " + tree.searchCount + " times searching the element " + x);
		tree.ownSearch(first, x);
		System.out.println("The alternative search called itself " + tree.ownSearchCount + " times searching the element " + x);
	}
	
	public static void callTestSuite() {
		Result result = JUnitCore.runClasses(TestSuite.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}

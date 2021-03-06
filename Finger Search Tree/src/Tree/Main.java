package Tree;

import java.util.Random;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Test.TestSuite;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		maxSizeTree(4);

	}
	
	public static void splitCount(int times) {
		FSTree tree = new FSTree();
		Leaf temp = tree.getFirstLeaf();
		for (int i = 0; i < times; i++) {
			temp = tree.insert(temp, i );
		}
		System.out.println("Level 1 node: split " + FSTree.splitINL1 + " times");
		System.out.println("Level 2 node: split " + FSTree.splitINL2 + " times");
		System.out.println("Internal node: split " + FSTree.splitIN + " times");
		System.out.println("Total: split " + FSTree.splitTotal + " times");
	}
	
	public static void splitRandomCounter(int times) {
		FSTree tree = new FSTree();
		Random rand = new Random();
		int upperbound = Integer.MAX_VALUE;
		int int_random;
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i < times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			if(temp.getValue() == int_random) {
				--i;
			}else
				temp = tree.insert(temp, int_random );
		}
		System.out.println("Level 1 node: split " + FSTree.splitINL1 + " times");
		System.out.println("Level 2 node: split " + FSTree.splitINL2 + " times");
		System.out.println("Internal node: split " + FSTree.splitIN + " times");
		System.out.println("Level 1 & 2 node: split " + FSTree.splitTotal + " times");
	}
	public static void mergeCounter(int times) {
		FSTree tree = new FSTree();
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i < 2*times; i++) {
			temp = tree.insert(temp, i );
		}
		for (int i = 0; i < times; i++) {
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
		for (int i = 0; i < 2*times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			if(temp.getValue() == int_random) {
				--i;
			}else
				tree.insert(temp, int_random );
		}
		for (int i = 0; i < times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			if(temp.getValue() == -1)
				--i;
			else
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
		for (int i = 0; i < times; i++) {
			temp = tree.insert(temp, i );
		}
		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			tree.search(first, i );
		}
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished original search in " + (double) timeElapsed / 1000000000 + "s");
		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			tree.ownSearch(first, i );
		}
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished alternative search in " + (double) timeElapsed / 1000000000 + "s");
		
	}
	
	public static void searchRepetition(int times) {
		FSTree tree = new FSTree();
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i < times; i++) {
			temp = tree.insert(temp, i );
		}
		for (int i = 0; i < times; i++) {
			tree.search(first, i );
		}
		System.out.println("The original search called itself " + tree.searchCount);
		for (int i = 0; i < times; i++) {
			tree.ownSearch(first, i );
		}
		System.out.println("The alternative search called itself " + tree.ownSearchCount);
	}
	public static void searchRandomRunTime(int times) {
		FSTree tree = new FSTree();
		Random rand = new Random();
		long start, finish, timeElapsed;
		int upperbound = Integer.MAX_VALUE;
		int int_random;
		Leaf temp, first = tree.getFirstLeaf();
		temp = first;
		for (int i = 0; i < times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			temp = tree.search(tree.getFirstLeaf(), int_random );
			if(temp.getValue() == int_random) {
				--i;
			}else
				temp = tree.insert(temp, int_random );
		}
		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			tree.search(first, int_random );
		}
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished original search in " + (double) timeElapsed / 1000000000 + "s");
		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			tree.ownSearch(first, int_random );
		}
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished alternative search in " + (double) timeElapsed / 1000000000 + "s");
		
	}
	public static void callTestSuite() {
		Result result = JUnitCore.runClasses(TestSuite.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
	
	public static long maxSizeTree(int height) {
		long result = 1;
		for(int i = 1; i <= height; i++) {
			result *= internalDegree(i)*GammaD(i)*DeltaD(i);
		}
		System.out.println("The maximal size of the tree of height " + height + " is: " + result);
		return result;
	}
	public static long DeltaD(int d) {
		long result = 2;
		for (int i = 1; i < d; i++) {
			result = (long) Math.pow(2, result);
		}
		System.out.println("Delta of height " + d + " is: " + result);
		return result;
	}
	private static long GammaD(int d) {
		long result;
		long power2d1 = (long) Math.pow(2, (d - 1));
		long power1 = 3 * (d - 1) * power2d1;
		long power2 = (long) Math.pow(2, d) + power2d1 - 2;
		result = (long) Math.pow(2, power1) * (long) Math.pow(2, power2);
		System.out.println("Gamma of height " + d + " is: " + result);
		return result;
	}
	public static long internalDegree(int d) {
		long result;
		long power2d = (long) Math.pow(2, d);
		long power = 3*d*power2d;
		long delta = DeltaD(d);
		result = (long) Math.pow(2, power) * delta;
		System.out.println("Internal degree of height " + d + " is: " + result);
		return result;
	}
}

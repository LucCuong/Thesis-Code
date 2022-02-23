package Tree;

import java.util.Random;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Test.TestSuite;

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
		for (int i = 0; i <= 65; i++) {
//			int_random = rand.nextInt(upperbound);
//			int_random = int_random < 0 ? int_random* (-1) : int_random;
//			System.out.println(int_random);
//			temp = tree.ownSearch(first, i);
			temp = tree.insert(temp, i);
		}
		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		System.out.println("Finished insertions in " + (double) timeElapsed / 1000000000);

		start = System.nanoTime();
//		for(int i = 0; i <= 65; i++ ){
//			tree.search(first, i);
//		}
		temp = tree.search(first, 33);
		finish = System.nanoTime();
		timeElapsed = finish - start;
		System.out.println("Finished search in " + (double) timeElapsed / 1000000000);
		System.out.println("the found leaf: " + temp.getValue() + ", called funtions " + FSTree.count + " times");

		Result result = JUnitCore.runClasses(TestSuite.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		tree.paintImage();

	}

}

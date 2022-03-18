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
		Leaf temp2,temp = first;
//		int upperbound = Integer.MAX_VALUE;
		int upperbound = 100;
		int int_random;
		int current = 0;
		temp2 = temp;
//		tree.paintImage();
//		long start = System.nanoTime();
		for (int i = 1; i <= 1; i++) {
			int_random = rand.nextInt(upperbound);
			int_random = int_random < 0 ? int_random* (-1) : int_random;
			System.out.println(int_random);
			current += int_random;
			temp = tree.ownSearch(first, current );
			temp = tree.insert(temp, current );
			if(i >= 14)
				temp2 = temp;
		}
//		
//		long finish = System.nanoTime();
//		long timeElapsed = finish - start;
//		System.out.println("Finished insertions in " + (double) timeElapsed / 1000000000);
		tree.insert(first, 7);
		tree.paintImage();
		
//		tree.delete(temp2);
//		tree.paintImage();
//		start = System.nanoTime();
//		for(int i = 0; i <= 65; i++ ){
//			tree.search(first, i);
//		}
//		temp = tree.search(first, 33);
//		finish = System.nanoTime();
//		timeElapsed = finish - start;
//		System.out.println("Finished search in " + (double) timeElapsed / 1000000000);
//		System.out.println("the found leaf: " + temp.getValue() + ", called funtions " + FSTree.count + " times");
//
//		Result result = JUnitCore.runClasses(TestSuite.class);
//
//		for (Failure failure : result.getFailures()) {
//			System.out.println(failure.toString());
//		}
//		System.out.println(result.wasSuccessful());
//		tree.paintImage();

	}

}

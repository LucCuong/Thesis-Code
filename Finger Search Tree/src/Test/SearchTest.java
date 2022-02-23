package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Tree.FSTree;
import Tree.Leaf;

public class SearchTest {

	@Test
	public void testSearch1() {
		// Search the element that is in the list
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 100; i++) {
			temp = tree.insert(temp, i);
		}
		temp = tree.search(first, 35);
		assertEquals(35, temp.getValue());
		temp = tree.search(first, 12);
		assertEquals(12, temp.getValue());
	}

	
	@Test
	public void testSearch2() {
		
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 100; i++) {
			temp = tree.insert(temp, i*2);
		}
		// Search the element that is NOT in the list
		temp = tree.search(first, 35);
		assertEquals(34, temp.getValue());
		
		temp = tree.search(first, 101);
		assertEquals(100, temp.getValue());
		
		temp = tree.search(first, -100);
		assertEquals(-1, temp.getValue());
	}

}

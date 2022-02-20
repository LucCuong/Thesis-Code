package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Tree.FSTree;
import Tree.IntermediateNodeLevel1;
import Tree.IntermediateNodeLevel2;
import Tree.InternalNode;
import Tree.Leaf;

public class InsertTest {

	@Test
	public void testInsert() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf newLeaf = tree.insert(first, 15);
		assertEquals(15, newLeaf.getValue());
	}
	
	@Test
	public void testSplit() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		InternalNode father = tree.getRoot();
		for (int i = 0; i <= 6; i++) {
			temp = tree.insert(temp, i);
		}
		// Create a node next to the old node
		assertNotNull(father.getNext());
	}
	
	@Test
	public void testINL1Pair() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 1; i++) {
			temp = tree.insert(temp, i);
		}
		IntermediateNodeLevel1 upNode = first.getUpNode();
		IntermediateNodeLevel1 pair = temp.getUpNode();
		// Create a node next to the old node
		assertEquals(upNode.getPair(), pair);
	}
	
	@Test
	public void testINL2Pair() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 4; i++) {
			temp = tree.insert(temp, i);
		}
		IntermediateNodeLevel2 upNode = first.getUpNode().getUpNode();
		IntermediateNodeLevel2 pair = temp.getUpNode().getUpNode();
		// Create a node next to the old node
		assertEquals(upNode.getPair(), pair);
	}
	
	@Test
	public void testNewRoot() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		InternalNode oldRoot = tree.getRoot();
		for (int i = 0; i <= 6; i++) {
			temp = tree.insert(temp, i);
		}
		InternalNode newRoot = tree.getRoot();
		// Update the new root after split
		assertNotEquals(newRoot, oldRoot);
		// The height of the tree is increased after split
		assertEquals(2, tree.getRoot().getHight());
	}

}

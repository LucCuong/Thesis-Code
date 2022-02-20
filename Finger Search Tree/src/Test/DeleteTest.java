package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Tree.FSTree;
import Tree.IntermediateNodeLevel1;
import Tree.IntermediateNodeLevel2;
import Tree.InternalNode;
import Tree.Leaf;

public class DeleteTest {

	@Test
	public void testDelete() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf newLeaf = tree.insert(first, 15);
		tree.delete(newLeaf);
		assertEquals(null, first.getNext());
	}
	
	@Test
	public void testUnlinkINL1Pair() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 1; i++) {
			temp = tree.insert(temp, i);
		}
		IntermediateNodeLevel1 upNode = first.getUpNode();
		IntermediateNodeLevel1 pair = temp.getUpNode();
		// Delete the last leaf and unlink the pair  of two INL1 nodes
		tree.delete(temp);
		// Unlink the pair node and delete INL1 node that has no sub node
		assertNotEquals(upNode.getPair(), pair);
	}
	
	@Test
	public void testUnlinkINL2Pair() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 3; i++) {
			temp = tree.insert(temp, i);
		}
		IntermediateNodeLevel2 upNode = first.getUpNode().getUpNode();
		IntermediateNodeLevel2 pair = temp.getUpNode().getUpNode();
		tree.delete(temp);
		// Create a node next to the old node
		assertNotEquals(upNode.getPair(), pair);
	}
	
	@Test
	public void testReduceHeight() {
		FSTree tree = new FSTree();
		Leaf first = tree.getFirstLeaf();
		Leaf temp = first;
		for (int i = 0; i <= 6; i++) {
			temp = tree.insert(temp, i);
		}
		InternalNode oldRoot = tree.getRoot();
		tree.delete(temp);
		InternalNode newRoot = tree.getRoot();
		// Update the new root after split
		assertNotEquals(newRoot, oldRoot);
		// The height of the tree is decreased after split
		assertEquals(1, tree.getRoot().getHight());
		// Delete the internal node next to new root
		assertEquals(null, newRoot.getNext());
		
	}
}

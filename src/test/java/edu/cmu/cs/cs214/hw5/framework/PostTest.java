package edu.cmu.cs.cs214.hw5.framework;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PostTest {
	Post post = new Post("aaa", null);

	@Test
	public void test() {
		assertTrue(post.getContent().equals("aaa"));
		assertTrue(post.getDate() == null);
	}
}

package edu.cmu.cs.cs214.hw5.framework;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class UserDataTest {
	private UserData userData = new UserData("Lakers", "Twitter");

	@Test
	public void testPosts() {
		Post post = new Post("aaa", null);
		userData.addPost(post);
		userData.addPost(null);
		assertTrue(userData.getPosts().size() == 1);
		assertTrue(userData.getName().equals("Lakers"));
		assertTrue(userData.getPlatform().equals("Twitter"));
		ArrayList<Post> posts = new ArrayList<Post>();
		posts.add(post);
		userData.setPostList(posts);
		assertTrue(userData.getPosts().size() == 2);
	}

	@Test
	public void testFollowees() {
		userData.setFollowees(10);
		assertTrue(userData.getFollowees() == 10);
	}

	@Test
	public void testFollowers() {

		userData.setFollowers(5);
		assertTrue(userData.getFollowers() == 5);
	}

	@Test
	public void testLocation() {
		userData.setLocation("here");
		assertTrue(userData.getLocation().equals("here"));
	}

	@Test
	public void testID() {
		userData.setId("me");
		assertTrue(userData.getId().equals("me"));
	}

	@Test
	public void testErrorMsg() {
		userData.setErroMsg("error");
		assertTrue(userData.getErroMsg().equals("error"));
	}
}

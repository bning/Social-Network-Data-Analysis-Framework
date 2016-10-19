package edu.cmu.cs.cs214.hw5.framework;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AnalyzedDataTest {
	private UserData userData = new UserData("Lakers", "Twitter");
	private AnalyzedData analyzedData;

	@Before
	public void setup() {

		userData.setFollowees(10);
		userData.setFollowers(5);
		userData.setLocation("here");
		Post post = new Post("aaa @bbb aaa", null);
		ArrayList<Post> posts = new ArrayList<Post>();
		posts.add(post);

		userData.setPostList(posts);
		userData.setId("abc");
		analyzedData = new AnalyzedData(userData);
	}

	@Test
	public void test() {
		assertTrue(analyzedData.getFollowees() == 10);
		assertTrue(analyzedData.getFollowers() == 5);
		assertTrue(analyzedData.getLocation().equals("here"));
		assertTrue(analyzedData.getName().equals("Lakers"));
		assertTrue(analyzedData.getPlatform().equals("Twitter"));
		assertTrue(analyzedData.getPosts().size() == 1);
		assertTrue(analyzedData.getWordsCount().size() == 1);
		assertTrue(analyzedData.getNonWordsCount().get("@") == 1);
		assertTrue(analyzedData.getUserID().equals("abc"));
	}
}

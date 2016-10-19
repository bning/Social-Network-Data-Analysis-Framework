package edu.cmu.cs.cs214.hw5.framework;

import edu.cmu.cs.cs214.hw5.plugin.DataPlugin;

public class FakeTwitterDataPlugin implements DataPlugin {
	private String name = "Twitter";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public UserData getUserData(String selectedUserName) {
		UserData userData = new UserData("Lakers", "Twitter");
		userData.setFollowees(10);
		userData.setFollowers(5);
		Post post = new Post("aaa bbb", null);
		userData.addPost(post);
		return userData;
	}

}

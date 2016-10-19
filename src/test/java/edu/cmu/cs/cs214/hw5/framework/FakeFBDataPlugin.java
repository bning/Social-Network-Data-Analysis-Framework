package edu.cmu.cs.cs214.hw5.framework;

import edu.cmu.cs.cs214.hw5.plugin.DataPlugin;

public class FakeFBDataPlugin implements DataPlugin {
	private String name = "Facebook";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public UserData getUserData(String selectedUserName) {

		return null;
	}

}

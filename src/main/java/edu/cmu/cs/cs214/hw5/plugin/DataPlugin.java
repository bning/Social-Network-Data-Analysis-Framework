package edu.cmu.cs.cs214.hw5.plugin;

/**
 * the data plugin interface
 * 
 * @author Zhiqi Xu
 *
 */
import edu.cmu.cs.cs214.hw5.framework.UserData;

/**
 * The data plug interface. The framework will call these methods for data plug
 * in.
 */

public interface DataPlugin {

	/**
	 * Get data plugin's name. GUI will call different plugin by name, and
	 * display the result accordingly
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * This method is for plugin to get user's Data and information from. This
	 * method will return an ArrayList of UserData.
	 * 
	 * @param userName
	 *            user name
	 * @return UserData
	 */

	UserData getUserData(String userName);

}

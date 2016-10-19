package edu.cmu.cs.cs214.hw5.framework;

import java.util.HashMap;

import javax.swing.JPanel;

/**
 * The framework listener interface.
 *
 * @see FrameworkEvent
 */
public interface FrameworkListener {

	/**
	 * Draw panels.
	 *
	 * @param nameJPanelPair
	 *          the name j panel pair
	 */
	void results(HashMap<String, JPanel> nameJPanelPair);

	/**
	 * No data is given.
	 *
	 * @param brokenName
	 *          the broken name
	 */
	void noData(String brokenName);

	/**
	 * Fetched data.
	 */
	void fetchedData();

	/**
	 * Clear all.
	 */
	void clearAll();

}

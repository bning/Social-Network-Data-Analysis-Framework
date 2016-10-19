package edu.cmu.cs.cs214.hw5.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw5.plugin.AnalysisPlugin;
import edu.cmu.cs.cs214.hw5.plugin.DataPlugin;

/**
 * The Interface Framework.
 */
public interface Framework {

	/**
	 * Adds the data plugin.
	 *
	 * @param dataPlugin
	 *          the data plugin
	 */
	void addDataPlugin(DataPlugin dataPlugin);

	/**
	 * Adds the analysis plugin.
	 *
	 * @param analysisPlugin
	 *          the analysis plugin
	 */
	void addAnalysisPlugin(AnalysisPlugin analysisPlugin);

	/**
	 * Sets the name platform pairs. Name is user name.
	 *
	 * @param namePlatformPairs
	 *          the name platform pairs
	 */
	void setNamePlatformPairs(HashMap<String, String> namePlatformPairs);

	/**
	 * Run analysis after get selected analysis names
	 *
	 * @param selectedAnalysisNames
	 *          the selected analysis names
	 */
	void setSelectedAnalysis(ArrayList<String> selectedAnalysisNames);

	/**
	 * Sets the all analyzed data. Analyzed data has two more fields than user
	 * data, which are used by analysis plugins.
	 */
	void setAllAnalyzedData();

	/**
	 * Clear all stored info.
	 */
	void clearAll();

	/**
	 * Gets the data plugin names.
	 *
	 * @return the data plugin names
	 */
	Set<String> getDataPluginNames();

	/**
	 * Gets the analysis plugin names.
	 *
	 * @return the analysis plugin names
	 */
	Set<String> getAnalysisPluginNames();

	/**
	 * Subscribe FrameworkListeners.
	 *
	 * @param frameworkListener
	 *          the framework listener
	 */
	void subscribe(FrameworkListener frameworkListener);

	/**
	 * Notify FrameworkListeners to draw panels.
	 *
	 * @param nameJPanelPair
	 *          the name j panel pair
	 */
	void notifyToDrawPanels(HashMap<String, JPanel> nameJPanelPair);

	/**
	 * Notify FrameworkListeners this user has no data.
	 *
	 * @param brokenName
	 *          the broken name
	 */
	void notifyNoData(String brokenName);

	/**
	 * Notify data given.
	 */
	void notifyDataGiven();

	/**
	 * Notify clear all.
	 */
	void notifyClearAll();
}

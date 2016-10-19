package edu.cmu.cs.cs214.hw5.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw5.plugin.AnalysisPlugin;
import edu.cmu.cs.cs214.hw5.plugin.DataPlugin;

/**
 * The Class FrameworkImpl.
 */
public class FrameworkImpl implements Framework {

	/** The data plugins. */
	private HashMap<String, DataPlugin> dataPlugins = new HashMap<String, DataPlugin>();

	/** The analysis plugins. */
	private HashMap<String, AnalysisPlugin> analysisPlugins = new HashMap<String, AnalysisPlugin>();

	/** The name platform pairs. */
	private HashMap<String, String> namePlatformPairs = new HashMap<String, String>();

	/** The all analyzed data. */
	private ArrayList<AnalyzedData> allAnalyzedData = new ArrayList<AnalyzedData>();

	/** The subscribed framework listeners. */
	private ArrayList<FrameworkListener> subscribedFrameworkListeners = new ArrayList<FrameworkListener>();

	@Override
	public void addDataPlugin(DataPlugin dataPlugin) {
		dataPlugins.put(dataPlugin.getName(), dataPlugin);
	}

	@Override
	public void addAnalysisPlugin(AnalysisPlugin analysisPlugin) {
		analysisPlugins.put(analysisPlugin.getName(), analysisPlugin);
	}

	@Override
	public void setNamePlatformPairs(HashMap<String, String> namePlatformPairs1) {
		if (namePlatformPairs != null) {
			this.namePlatformPairs.clear();
		}
		this.namePlatformPairs.putAll(namePlatformPairs1);

		setAllAnalyzedData();
	}

	@Override
	public void setSelectedAnalysis(ArrayList<String> selectedAnalysisNames) {
		HashMap<String, JPanel> nameJPanelsPair = new HashMap<String, JPanel>();

		for (String analysisPluginName : selectedAnalysisNames) {

			nameJPanelsPair.put(analysisPluginName,
			    analysisPlugins.get(analysisPluginName).analyzeData(allAnalyzedData));
		}
		notifyToDrawPanels(nameJPanelsPair);
	}

	@Override
	public void setAllAnalyzedData() {
		if (allAnalyzedData != null) {
			allAnalyzedData.clear();
		}

		for (String userNamePlatform : namePlatformPairs.keySet()) {
			DataPlugin dataPlugin = dataPlugins.get(namePlatformPairs
			    .get(userNamePlatform));

			String[] userNameArray = userNamePlatform.split(" ");
			String userName = userNameArray[0];
			UserData userData = dataPlugin.getUserData(userName);
			if (userData == null ) {
				notifyNoData("We can't get the data from the user: "+userName);
				clearAll();
				return;
			}
			if( userData.getErroMsg() != null){
				notifyNoData(userData.getErroMsg());
				clearAll();
				return;
			}

			// no valid pure word in all posts
			AnalyzedData analyzedData = new AnalyzedData(userData);
			if (analyzedData.getWordsCount().size() == 0) {
				notifyNoData("The posts of the user: "+userName+"is invalid!");
				clearAll();
				return;
			}
			allAnalyzedData.add(analyzedData);
		}
		notifyDataGiven();
	}

	@Override
	public void notifyDataGiven() {
		for (FrameworkListener frameworkListener : subscribedFrameworkListeners) {
			frameworkListener.fetchedData();
		}
	}

	@Override
	public void notifyNoData(String errorMsg) {

		for (FrameworkListener frameworkListener : subscribedFrameworkListeners) {
			frameworkListener.noData(errorMsg);
		}

	}

	@Override
	public void clearAll() {
		namePlatformPairs.clear();
		allAnalyzedData.clear();

	}

	@Override
	public void notifyClearAll() {
		for (FrameworkListener frameworkListener : subscribedFrameworkListeners) {
			frameworkListener.clearAll();
		}
	}

	@Override
	public Set<String> getDataPluginNames() {
		return dataPlugins.keySet();

	}

	/**
	 * Gets the name platform pairs. This method has no actual use but can be
	 * helpful when testing.
	 *
	 * @return the name platform pairs
	 */
	public HashMap<String, String> getNamePlatformPairs() {
		return namePlatformPairs;
	}

	/**
	 * Gets the all analyzed data. This method has no actual use but can be
	 * helpful when testing.
	 *
	 * @return the all analyzed data
	 */
	public ArrayList<AnalyzedData> getAllAnalyzedData() {
		return allAnalyzedData;
	}

	/**
	 * Gets the subscribed framework listeners. This method has no actual use but
	 * can be helpful when testing.
	 *
	 * @return the subscribed framework listeners
	 */
	public ArrayList<FrameworkListener> getSubscribedFrameworkListeners() {
		return subscribedFrameworkListeners;
	}

	@Override
	public Set<String> getAnalysisPluginNames() {
		return analysisPlugins.keySet();

	}

	@Override
	public void subscribe(FrameworkListener frameworkListener) {
		subscribedFrameworkListeners.add(frameworkListener);
	}

	@Override
	public void notifyToDrawPanels(HashMap<String, JPanel> nameJPanelPair) {
		for (FrameworkListener frameworkListener : subscribedFrameworkListeners) {
			frameworkListener.results(nameJPanelPair);
		}
	}
}

package edu.cmu.cs.cs214.hw5.plugin;

/**
 * the analysis plugin interface
 * 
 * @author Zhiqi Xu
 *
 */
import java.util.List;

import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw5.framework.AnalyzedData;

/**
 * The analyze plug in interface. There will be three implements of this
 * interface.
 */
public interface AnalysisPlugin {

	/**
	 * This method inputs a list of UserInfo objects and selects different
	 * pieces of information for different analysis plug-ins.
	 * 
	 * @param analyzedDatas
	 *            analyzed data
	 * @return JPanel for the GUI.
	 */

	JPanel analyzeData(List<AnalyzedData> analyzedDatas);

	/**
	 * Get analyze plug in's name. This method is used for GUI. GUI will call
	 * this method to get plug-in's name, in order to generate plug-in display
	 * list.
	 * 
	 * @return a String of name.
	 */
	String getName();

}

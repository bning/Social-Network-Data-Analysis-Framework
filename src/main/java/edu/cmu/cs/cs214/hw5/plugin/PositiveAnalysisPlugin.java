package edu.cmu.cs.cs214.hw5.plugin;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import edu.cmu.cs.cs214.hw5.framework.AnalyzedData;
import edu.cmu.cs.cs214.hw5.framework.WordBank;

/**
 * the analysis plugin class to analyze the positive and negative attitude
 * 
 * @author Jiyu Zhu
 *
 */
public class PositiveAnalysisPlugin implements AnalysisPlugin {
	private static final int FOURHUNDFIFTY = 380;
	private static final int FOURHUNDTHIRTY = 320;

	/**
	 * the override class to analyze the data, with the input of the data list
	 */
	@Override
	public JPanel analyzeData(List<AnalyzedData> allData1) {
		JPanel totalResultPanel = new JPanel();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (AnalyzedData data : allData1) {
			HashMap<String, Double> eachPair = getPostClassify(data
					.getWordsCount());
			String userName = data.getName();
			for (String describe : eachPair.keySet()) {
				dataSet.addValue(eachPair.get(describe), describe, userName);
			}
		}
		totalResultPanel.add(drawHistogram(dataSet, null, "Attitude",
				"Percentage"));
		return totalResultPanel;
	}

	/**
	 * the method to draw the histogram
	 * 
	 * @param dataSet
	 *            the input dataset for the histogram
	 * @param title
	 *            the title of the plot
	 * @param xlabel
	 *            xlabel of the plot
	 * @param ylabel
	 *            ylabel of the plot
	 * @return the charpanel for the histogram
	 */
	private ChartPanel drawHistogram(CategoryDataset dataSet, String title,
			String xlabel, String ylabel) {
		JFreeChart chart = ChartFactory.createBarChart3D(title, xlabel, ylabel,
				dataSet, PlotOrientation.VERTICAL, true, false, false);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel
				.setPreferredSize(new Dimension(FOURHUNDFIFTY, FOURHUNDTHIRTY));
		return chartPanel;
	}

	/**
	 * get the post classfyication
	 * 
	 * @param wordsCount
	 *            the input words count, which is the word and the frequency
	 * @return the word and percentage pair
	 */
	private HashMap<String, Double> getPostClassify(
			HashMap<String, Integer> wordsCount) {
		HashMap<String, Double> postClassify = new HashMap<String, Double>();
		if (wordsCount.keySet().size() == 0) {

			postClassify.put("positive", 0.0);
			postClassify.put("negative", 0.0);
			return postClassify;
		}

		int posNum = 0;
		int negNum = 0;
		WordBank wordBank = new WordBank();
		Set<String> positiveWords = wordBank.getPositiveWords();
		Set<String> negativeWords = wordBank.getNegativeWords();
		for (String word : wordsCount.keySet()) {
			if (positiveWords.contains(word)) {
				posNum++;
			}
			if (negativeWords.contains(word)) {
				negNum++;
			}
		}
		postClassify.put("positive", 1.0 * posNum / wordsCount.keySet().size());
		postClassify.put("negative", 1.0 * negNum / wordsCount.keySet().size());
		return postClassify;
	}

	/**
	 * the override method to get the name of the plugin
	 */
	@Override
	public String getName() {
		return "Positive/Negative Words";
	}
}

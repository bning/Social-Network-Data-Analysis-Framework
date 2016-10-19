package edu.cmu.cs.cs214.hw5.plugin;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import edu.cmu.cs.cs214.hw5.framework.AnalyzedData;

/**
 * the analysis plugin class to analyze the user wolrd interaction
 * 
 * @author Jiyu Zhu
 * 
 */
public class ConnectiveActivityAnalysisPlugin implements AnalysisPlugin {
	private static final int CNT1 = 15;
	private static final double CNT2 = 0.02;

	/**
	 * the override method to analyze the data, with the input of the data class
	 */
	@Override
	public JPanel analyzeData(List<AnalyzedData> allData1) {
		JPanel totalResult = new JPanel();
		totalResult.setLayout(new BoxLayout(totalResult, BoxLayout.X_AXIS));
		ArrayList<PieDataset> allPies = new ArrayList<PieDataset>();
		ArrayList<String> userNames = new ArrayList<String>();

		for (AnalyzedData data : allData1) {
			allPies.add(createDataset(data));
			userNames.add(data.getName());
		}
		int cnt = 0;
		for (PieDataset eachPie : allPies) {
			String title = userNames.get(cnt++);
			JFreeChart chart = ChartFactory.createPieChart(title, eachPie,
					true, true, false);
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("Arial", Font.ITALIC, CNT1));
			plot.setCircular(false);
			plot.setLabelGap(CNT2);
			ChartPanel chartPanel = new ChartPanel(chart);
			totalResult.add(chartPanel);
		}
		return totalResult;
	}

	/**
	 * create the data set for the plot
	 * 
	 * @param data
	 *            the input data class
	 * @return the pie data set to draw the pie chart
	 */
	private PieDataset createDataset(AnalyzedData data) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		HashMap<String, Integer> nonwordNumPair = data.getNonWordsCount();
		int totalNum = 0;
		for (String s : nonwordNumPair.keySet()) {
			totalNum += nonwordNumPair.get(s);
		}
		int webCnt = nonwordNumPair.get("://");
		int atCnt = nonwordNumPair.get("@");
		int sharpCnt = nonwordNumPair.get("#");
		int other = totalNum - webCnt - atCnt - sharpCnt;
		dataset.setValue("http://", new Double(webCnt));
		dataset.setValue("@", new Double(atCnt));
		dataset.setValue("#", new Double(sharpCnt));
		dataset.setValue("Others", new Double(other));
		return dataset;
	}

	/**
	 * the override method to get the analysis plugin name
	 */
	@Override
	public String getName() {
		return "User World Interaction";
	}

}

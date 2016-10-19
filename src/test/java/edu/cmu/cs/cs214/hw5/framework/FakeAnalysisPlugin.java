package edu.cmu.cs.cs214.hw5.framework;

import java.util.List;

import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw5.plugin.AnalysisPlugin;

public class FakeAnalysisPlugin implements AnalysisPlugin {
	private String name = "Analysis";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public JPanel analyzeData(List<AnalyzedData> allAnalyzedData) {
		// TODO Auto-generated method stub
		return null;
	}

}

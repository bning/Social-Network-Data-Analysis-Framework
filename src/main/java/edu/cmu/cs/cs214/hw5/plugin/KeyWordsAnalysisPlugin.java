package edu.cmu.cs.cs214.hw5.plugin;

/**
 * the Key word analysis plugin interface
 * Get the top 9 key words from the post and print it on the panel, the bigger
 * the word appears, the more it appears in the posts.
 * 
 * @author Zhiqi Xu
 *
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw5.framework.AnalyzedData;
import edu.cmu.cs.cs214.hw5.framework.WordBank;

/**
 * The Class KeyWordsAnalysisPlugin.
 */
public class KeyWordsAnalysisPlugin implements AnalysisPlugin {

	/** The Constant PLUGIN_NAME. */
	private static final String PLUGIN_NAME = "Key Words Analysis";

	/** The Constant insets. */
	private static final Insets INSETS = new Insets(0, 0, 0, 0);
	private static final int FOUR = 4;

	private static final int TF = 25;

	private static final int TWENTY = 20;

	private static final int THREE = 3;

	private static final int ZERO = 0;

	private static final int ONE = 1;

	private static final int TWO = 2;

	private static final int NINETEEN = 19;

	private static final int EIGHTEEN = 18;

	private static final int SEVENTEEN = 17;

	private static final int FIVE = 5;

	private static final int SIX = 6;

	private static final int SIXTEEN = 16;

	private static final int SEVEN = 7;

	private static final int FIFTEEN = 15;

	private static final int EIGHT = 8;

	private static final int FOURTEEN = 14;

	private static final int TWOHUND = 500;

	private static final int FIXHUND = 500;

	private static final int NINE = 9;

	// give a list of words
	/**
	 * Pre analyze.
	 *
	 * @param analyzedData
	 *          the analyzed data
	 * @return the array list
	 */
	public ArrayList<String> preAnalyze(AnalyzedData analyzedData) {
		HashMap<String, Integer> map = analyzedData.getWordsCount();
		ArrayList<String> removeList = new ArrayList<String>();
		WordBank wordBank = new WordBank();
		Set<String> prepositions = wordBank.getPrepositionWords();
		for (String key : map.keySet()) {
			if (prepositions.contains(key) || key.length() < FOUR) {
				removeList.add(key);
			}
		}
		for (String key : removeList) {
			map.remove(key);
		}

		List<Integer> countList = new ArrayList<Integer>(map.values());
		ArrayList<String> wordList = new ArrayList<String>();

		Collections.sort(countList, Collections.reverseOrder());
		List<Integer> top9 = new ArrayList<Integer>();
		if (countList.size() > NINE) {
			top9 = countList.subList(0, NINE);
		} else {
			top9 = countList;
		}

		for (int i = 0; i < top9.size(); i++) {
			Integer value = top9.get(i);
			for (Entry<String, Integer> entry : map.entrySet()) {
				String key = entry.getKey();
				if (value.equals(entry.getValue())) {
					if (!wordList.contains(key)) {
						wordList.add(key);
					}
				}
			}

		}
		return wordList;

	}

	/**
	 * Sets the panel.
	 *
	 * @param freqWordList
	 *          the freq word list
	 * @return the j panel
	 */
	public JPanel setPanel(List<String> freqWordList) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JLabel label;
		if (freqWordList.size() >= 1) {
			// row 1 (1, 5, 6, 2)
			label = new JLabel(freqWordList.get(0));
			label.setFont(new java.awt.Font("Dialog", ONE, TF));
			label.setForeground(Color.red);
			addComponent(panel, label, ONE, ONE, TWO, ONE, GridBagConstraints.CENTER,
			    GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= 2) {
			label = new JLabel(freqWordList.get(1));
			label.setFont(new java.awt.Font("Dialog", ONE, TWENTY));
			label.setForeground(Color.green);
			addComponent(panel, label, ZERO, ZERO, ONE, TWO,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= THREE) {
			label = new JLabel(freqWordList.get(2));
			label.setFont(new java.awt.Font("Dialog", ONE, TWENTY));
			label.setForeground(Color.blue);
			addComponent(panel, label, THREE, ZERO, ONE, TWO,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= FOUR) {
			label = new JLabel(freqWordList.get(THREE));
			label.setFont(new java.awt.Font("Dialog", 1, NINETEEN));
			label.setForeground(Color.orange);
			addComponent(panel, label, 0, 2, 1, 2, GridBagConstraints.CENTER,
			    GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= FIVE) {

			label = new JLabel(freqWordList.get(FOUR));
			label.setFont(new java.awt.Font("Dialog", 1, EIGHTEEN));
			label.setForeground(Color.black);
			addComponent(panel, label, THREE, TWO, ONE, TWO,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= SIX) {

			label = new JLabel(freqWordList.get(FIVE));
			label.setFont(new java.awt.Font("Dialog", ONE, SEVENTEEN));
			label.setForeground(Color.pink);
			addComponent(panel, label, 1, 0, 1, 1, GridBagConstraints.CENTER,
			    GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= SEVEN) {
			label = new JLabel(freqWordList.get(SIX));
			label.setFont(new java.awt.Font("Dialog", 1, SIXTEEN));
			label.setForeground(Color.red);
			addComponent(panel, label, 2, 0, 1, 1, GridBagConstraints.CENTER,
			    GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= EIGHT) {
			label = new JLabel(freqWordList.get(SEVEN));
			label.setFont(new java.awt.Font("Dialog", 1, FIFTEEN));
			label.setForeground(Color.blue);
			addComponent(panel, label, 1, THREE, ONE, ONE, GridBagConstraints.CENTER,
			    GridBagConstraints.BOTH);
		}
		if (freqWordList.size() >= NINE) {
			label = new JLabel(freqWordList.get(EIGHT));
			label.setFont(new java.awt.Font("Dialog", ONE, FOURTEEN));
			label.setForeground(Color.green);
			addComponent(panel, label, TWO, THREE, ONE, ONE,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH);
			panel.setSize(FIXHUND, TWOHUND);
			panel.setVisible(true);
		}

		return panel;

	}

	/**
	 * Adds the component.
	 *
	 * @param container
	 *          the container
	 * @param component
	 *          the component
	 * @param gridx
	 *          the gridx
	 * @param gridy
	 *          the gridy
	 * @param gridwidth
	 *          the gridwidth
	 * @param gridheight
	 *          the gridheight
	 * @param anchor
	 *          the anchor
	 * @param fill
	 *          the fill
	 */
	private static void addComponent(Container container, Component component,
	    int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
		    gridheight, ONE, ONE, anchor, fill, INSETS, 0, 0);
		container.add(component, gbc);
	}

	@Override
	public String getName() {

		return PLUGIN_NAME;
	}

	@Override
	public JPanel analyzeData(List<AnalyzedData> analyzedDatas) {
		int size = analyzedDatas.size();
		JPanel fullPanel = new JPanel();
		fullPanel.setLayout(new GridLayout(size, ONE));
		for (int i = 0; i < size; i++) {
			AnalyzedData freqMap = analyzedDatas.get(i);
			String userName = analyzedDatas.get(i).getName().toUpperCase();
			List<String> freqWordList = preAnalyze(freqMap);
			JPanel panel = setPanel(freqWordList);
			panel.setBorder(BorderFactory.createTitledBorder(userName));
			fullPanel.add(panel);
		}
		return fullPanel;
	}

}

package edu.cmu.cs.cs214.hw5.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.cmu.cs.cs214.hw5.framework.Framework;

/**
 * the class to choose the different analysis plugins
 * 
 * @author Jiyu Zhu
 * 
 */
public class ChooseAnalysisPluginGui extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 390;
	private static final int HEIGHT = 590;
	private static final int MAX_ANALYSIS_PLUG = 4;
	private static final int R = 255;
	private static final int G = 147;
	private static final int B = 30;
	private static final int CNT1 = 15;
	private static final int CNT2 = 105;
	private static final int CNT3 = 4;
	private static final int CNT4 = 40;
	private static final int CNT5 = 300;
	private static final int CNT6 = 30;
	private static final int CNT7 = 50;
	private static final int CNT9 = 23;
	private static final int CNT10 = 325;
	private static final int CNT11 = 350;
	private static final int CNT12 = 400;
	private static final int CNT13 = 325;
	private static final int CNT14 = 17;
	private static final int CNT15 = 19;
	private static final int CNT16 = 120;
	private static final int CNT17 = 35;
	private static final int FOUR = 4;

	private Framework framework;
	private JPanel prevPanel;

	private JPanel infoPanel;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private JPanel resultPanel;
	private JButton startAnalyzeButton;
	private JButton goBackButton;
	private MainMenuGui menu;

	private JPanel choosePanel;
	private ArrayList<JCheckBox> analysisPlugins;

	private String[] analysisPluginsName;
	private ArrayList<String> selectedAnalysisPluginsName;

	private ImageIcon image = new ImageIcon("assets/SNA_background_2.png");

	/**
	 * the constructor of the class
	 * 
	 * @param p
	 *          the input jpanel
	 * @param fw
	 *          the input framework for the gui
	 * @param m
	 *          the input mainmenugui for the gui
	 * 
	 */
	public ChooseAnalysisPluginGui(JPanel p, Framework fw, MainMenuGui m) {
		// set analysisPluginsName
		this.menu = m;
		boolean exceedMaxPlugins = false;
		this.framework = fw;
		Set<String> plugins = framework.getAnalysisPluginNames();
		if (plugins.size() <= MAX_ANALYSIS_PLUG) {
			analysisPluginsName = new String[plugins.size()];
			int cnt = 0;
			for (String s : plugins) {
				analysisPluginsName[cnt++] = s;
			}
		} else {
			exceedMaxPlugins = true;
			analysisPluginsName = new String[MAX_ANALYSIS_PLUG];
			int cnt = 0;
			for (String s : plugins) {
				if (cnt < FOUR) {
					analysisPluginsName[cnt++] = s;
				} else {
					break;
				}
			}
		}

		this.prevPanel = p;

		mainPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
			}
		};
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel emptyPanel1 = this.createEmpty(CNT2);

		// check box
		analysisPlugins = new ArrayList<JCheckBox>();
		choosePanel = new JPanel();
		choosePanel.setOpaque(false);
		choosePanel.setLayout(new BoxLayout(choosePanel, BoxLayout.X_AXIS));
		JPanel emptyL = new JPanel();
		emptyL.setOpaque(false);
		emptyL.setPreferredSize(new Dimension(CNT4, CNT5));
		choosePanel.add(emptyL);

		JPanel center = new JPanel();
		GridLayout grid = new GridLayout(CNT3, 1);
		grid.setVgap(CNT6);
		center.setLayout(grid);
		center.setOpaque(false);

		for (int i = 0; i < analysisPluginsName.length; i++) {
			JCheckBox box = new JCheckBox(analysisPluginsName[i]);
			box.setOpaque(false);
			box.setPreferredSize(new Dimension(CNT5, CNT4));
			box.setFont(new java.awt.Font("Arial", Font.BOLD, CNT9));
			box.setForeground(new Color(R, G, B));
			analysisPlugins.add(box);
			center.add(box);
		}
		choosePanel.add(center);
		JPanel emptyR = new JPanel();
		emptyR.setOpaque(false);
		emptyR.setPreferredSize(new Dimension(CNT6, CNT5));
		choosePanel.add(emptyR);

		JPanel emptyPanel2 = this.createEmpty(CNT14);

		infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setPreferredSize(new Dimension(CNT5, CNT7));
		infoPanel.setOpaque(true);
		if (exceedMaxPlugins) {
			updateInfo("Get data Successfully!\nWe can only show first 4 analysis plugins!");
		}

		JPanel emptyPanel3 = this.createEmpty(CNT1);

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout());
		goBackButton();
		startAnalyzeButton();

		JPanel emptyPanel4 = this.createEmpty(CNT7);

		mainPanel.add(emptyPanel1);
		mainPanel.add(choosePanel);
		mainPanel.add(emptyPanel2);
		mainPanel.add(infoPanel);
		mainPanel.add(emptyPanel3);
		mainPanel.add(buttonPanel);
		mainPanel.add(emptyPanel4);

		add(mainPanel);
	}

	/**
	 * the override method to paint the compoent
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		image.paintIcon(this, g, 0, 0);
	}

	/**
	 * the method to create empty jpanel
	 * 
	 * @param h
	 *          the width of the jpanel
	 * @return the empty jpanel
	 */
	private JPanel createEmpty(int h) {
		JPanel empty = new JPanel();
		empty.setOpaque(false);
		empty.setPreferredSize(new Dimension(WIDTH, h));
		return empty;
	}

	/**
	 * the method to add the start analyze button, including the action listener
	 */
	private void startAnalyzeButton() {
		startAnalyzeButton = new JButton("Analyze");
		startAnalyzeButton.setPreferredSize(new Dimension(CNT16, CNT17));
		startAnalyzeButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT15));
		startAnalyzeButton.setForeground(new Color(R, G, B));
		startAnalyzeButton.setEnabled(true);
		startAnalyzeButton.setOpaque(false);
		startAnalyzeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedAnalysisPluginsName = selectedAnalysis();
				if (selectedAnalysisPluginsName.size() == 0) {
					updateInfo("Choose at least one analysis plugin !");
				} else {
					framework.setSelectedAnalysis(selectedAnalysisPluginsName);
					// framework run all analysis, and show the results
				}
			}
		});
		buttonPanel.add(startAnalyzeButton);
	}

	/**
	 * the method to select the analysis plugins
	 * 
	 * @return the arraylist of string of the plugin names
	 */
	private ArrayList<String> selectedAnalysis() {
		ArrayList<String> selectedAnalysis = new ArrayList<String>();
		for (JCheckBox box : this.analysisPlugins) {
			if (box.isSelected()) {
				selectedAnalysis.add(box.getText());
			}
		}
		return selectedAnalysis;
	}

	/**
	 * clear the analysis data input, the main menu input, the framework
	 */
	private void clearAnalysisAndData() {
		this.menu.clearAll();
		for (JCheckBox box : this.analysisPlugins) {
			box.setSelected(false);
		}
		this.framework.clearAll();
	}

	/**
	 * add the go back button, including the action listener
	 */
	private void goBackButton() {
		goBackButton = new JButton("Go Back");
		goBackButton.setPreferredSize(new Dimension(CNT16, CNT17));
		goBackButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT15));
		goBackButton.setForeground(new Color(R, G, B));
		goBackButton.setEnabled(true);
		goBackButton.setOpaque(false);
		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAnalysisAndData();
				mainPanel.setVisible(false);
				prevPanel.setVisible(true);
			}
		});
		buttonPanel.add(goBackButton);
	}

	/**
	 * show the result of the analysis plugins, given various jpanels
	 * 
	 * @param namePanels
	 *          the input jpanels
	 */
	public void results(HashMap<String, JPanel> namePanels) {
		mainPanel.setVisible(false);

		resultPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
			}
		};
		resultPanel.setOpaque(false);
		resultPanel.setBackground(Color.white);
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

		JPanel empty = createEmpty(CNT2);
		resultPanel.add(empty);

		JTabbedPane results = new JTabbedPane();
		results.setOpaque(true);
		results.setPreferredSize(new Dimension(CNT11, CNT12));
		results.setBackground(Color.WHITE);

		for (String name : namePanels.keySet()) {
			JPanel onePanel = namePanels.get(name);
			onePanel.setPreferredSize(new Dimension(CNT10, CNT13));
			results.add(name, onePanel);
		}
		resultPanel.add(results);

		JPanel bPanel = new JPanel();
		bPanel.setOpaque(false);
		bPanel.setLayout(new FlowLayout());
		// add the exit go back button
		JButton exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(CNT16, CNT17));
		exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT15));
		exitButton.setForeground(new Color(R, G, B));
		exitButton.setEnabled(true);
		exitButton.setOpaque(false);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		JButton backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(CNT16, CNT17));
		backButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT15));
		backButton.setForeground(new Color(R, G, B));
		backButton.setEnabled(true);
		backButton.setOpaque(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resultPanel.setVisible(false);
				mainPanel.setVisible(true);
			}

		});
		bPanel.add(backButton);
		bPanel.add(exitButton);
		resultPanel.add(bPanel);
		resultPanel.revalidate();

		add(resultPanel);
	}

	/**
	 * the method to show the main panel
	 */
	public void show() {
		mainPanel.setVisible(true);
	}

	/**
	 * the method to update the infomaiton in the info panel
	 * 
	 * @param s
	 *          the input info, which need to be updated
	 */
	private void updateInfo(String s) {
		JTextArea info = new JTextArea(s);
		info.setForeground(Color.GRAY);
		info.setFont(new java.awt.Font("Arial", Font.ITALIC, CNT1));
		infoPanel.removeAll();
		infoPanel.add(info);
		infoPanel.repaint();
		infoPanel.revalidate();
	}

}
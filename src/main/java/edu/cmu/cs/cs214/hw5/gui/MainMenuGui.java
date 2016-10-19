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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.cmu.cs.cs214.hw5.framework.Framework;
import edu.cmu.cs.cs214.hw5.framework.FrameworkListener;

/**
 * The first panel of the GUI, which show the main menu, including data plugin,
 * user names.
 * 
 * @author Jiyu Zhu
 *
 */
public class MainMenuGui extends JPanel implements FrameworkListener {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 390;
	private static final int HEIGHT = 590;
	private static final int MAX_USERS = 4;

	private static final int CNT1 = 105;
	private static final int CNT2 = 4;
	private static final int CNT3 = 14;
	private static final int CNT4 = 300;
	private static final int CNT5 = 50;
	private static final int CNT6 = 15;
	private static final int CNT7 = 17;
	private static final int R = 255;
	private static final int G = 147;
	private static final int B = 30;
	private static final int CNT8 = 120;
	private static final int CNT9 = 35;
	private static final int CNT10 = 17;

	private Framework framework;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private ChooseAnalysisPluginGui analysis;

	private ArrayList<InputPanel> users;
	private JPanel usersPanel;
	private JPanel infoPanel;
	private JPanel buttonPanel;
	private JButton clearAllButton;
	private JButton getDataButton;
	private JButton toAnalysisButton;
	private ImageIcon image = new ImageIcon("assets/SNA_background.png");

	/**
	 * the constructor of the class
	 * 
	 * @param frame
	 *            the input JFrame for the gui
	 * @param fw
	 *            the input framework for the gui
	 */
	public MainMenuGui(JFrame frame, Framework fw) {
		this.framework = fw;
		this.mainFrame = frame;

		mainPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
			}
		};
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel emptyPanel1 = this.createEmpty(CNT1);

		users = new ArrayList<InputPanel>(MAX_USERS);
		usersPanel = new JPanel();
		usersPanel.setOpaque(false);
		GridLayout grid = new GridLayout(CNT2, 1);
		grid.setVgap(CNT3);
		usersPanel.setLayout(grid);
		for (int i = 0; i < MAX_USERS; i++) {
			addUser();
		}

		JPanel emptyPanel2 = this.createEmpty(CNT7);

		infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setPreferredSize(new Dimension(CNT4, CNT5));
		infoPanel.setOpaque(true);

		JPanel emptyPanel3 = this.createEmpty(CNT6);

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout());
		addClearAllButton();
		addGetDataButton();
		addAnalysisButton();

		JPanel emptyPanel4 = this.createEmpty(CNT5);

		mainPanel.add(emptyPanel1);
		mainPanel.add(usersPanel);
		mainPanel.add(emptyPanel2);
		mainPanel.add(infoPanel);
		mainPanel.add(emptyPanel3);
		mainPanel.add(buttonPanel);
		mainPanel.add(emptyPanel4);
		add(mainPanel);
	}

	/**
	 * the override method to paint the component
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		image.paintIcon(this, g, 0, 0);
	}

	/**
	 * create the empty JPanel
	 * 
	 * @param h
	 *            the height of the panel 
	 * @return the empty JPanel
	 */
	private JPanel createEmpty(int h) {
		JPanel empty = new JPanel();
		empty.setPreferredSize(new Dimension(WIDTH, h));
		empty.setOpaque(false);
		return empty;
	}

	/**
	 * add the clear all button, including add the action listener
	 */
	private void addClearAllButton() {
		clearAllButton = new JButton("Clear All");
		clearAllButton.setPreferredSize(new Dimension(CNT8, CNT9));
		clearAllButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT10));
		clearAllButton.setForeground(new Color(R, G, B));
		clearAllButton.setEnabled(true);
		clearAllButton.setOpaque(false);
		clearAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllPrivate();
			}
		});
		buttonPanel.add(clearAllButton);
	}

	/**
	 * add the get data button, including add the action listener
	 */
	private void addGetDataButton() {
		getDataButton = new JButton("Get Data");
		getDataButton.setPreferredSize(new Dimension(CNT8, CNT9));
		getDataButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT10));
		getDataButton.setForeground(new Color(R, G, B));
		getDataButton.setEnabled(true);
		getDataButton.setOpaque(false);
		getDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> hm = new HashMap<String, String>();
				storeUserApiInfo(hm);
				getData(hm);
			}
		});
		buttonPanel.add(getDataButton);
	}

	/**
	 * add the analysis button to go the the choose analysis panel
	 */
	private void addAnalysisButton() {
		toAnalysisButton = new JButton("Analysis");
		toAnalysisButton.setPreferredSize(new Dimension(CNT8, CNT9));
		toAnalysisButton.setFont(new java.awt.Font("Arial", Font.BOLD, CNT10));
		toAnalysisButton.setForeground(new Color(R, G, B));
		toAnalysisButton.setEnabled(false);
		toAnalysisButton.setOpaque(false);
		toAnalysisButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAnalysis();
			}
		});
		buttonPanel.add(toAnalysisButton);
	}

	private boolean getData(HashMap<String, String> hm) {
		if (hm.isEmpty()) {
			updateInfo("Please input at least one user name and social network!");
			return false;
		}
		return true;
	}

	/**
	 * store the user name and the social network api name into the hashmap
	 * 
	 * @param hm
	 *            the input hashmap to store the user name and api name
	 * @return true if store successfully, otherwise false
	 */
	private boolean storeUserApiInfo(HashMap<String, String> hm) {
		for (InputPanel input : users) {
			if (input.addUserNameToFramework(hm) == 1) {
				updateInfo("Duplicate input of the user name and social network!");
				return false;
			}
			if (input.addUserNameToFramework(hm) == 2) {
				updateInfo("Please input at least one user name and social network!");
				return false;
			}
		}
		return true;
	}

	/**
	 * add the user into the gui
	 */
	private void addUser() {
		InputPanel input = new InputPanel(framework);
		users.add(input);
		usersPanel.add(users.get(users.size() - 1));
		usersPanel.revalidate();
	}

	/**
	 * clear all info
	 */
	@Override
	public void clearAll() {
		updateInfo("Clear All Input!");
		this.toAnalysisButton.setEnabled(false);
		for (InputPanel p : users) {
			p.clear();
		}
	}
	
	/**
	 * the private method to clear all the input
	 */
	private void clearAllPrivate(){
		updateInfo("Clear All Input!");
		for (InputPanel p : users) {
			p.clear();
		}
	}

	/**
	 * the override no data method to output no data condition
	 */
	@Override
	public void noData(String mesg) {
		this.toAnalysisButton.setEnabled(false);
		updateInfo(mesg + "\nPlease try again!");
	}

	/**
	 * the override method fetch data successfully condition
	 */
	@Override
	public void fetchedData() {
		this.toAnalysisButton.setEnabled(true);
		updateInfo("Get data Successfully!\nClick Analysis to choose your analysis plugins!");
	}

	/**
	 * the method to show the analysis panels
	 */
	private void showAnalysis() {
		// show another JPanel to display analysis plugins
		mainPanel.setVisible(false);
		if (analysis == null) {
			analysis = new ChooseAnalysisPluginGui(mainPanel, framework, this);
			mainFrame.add(analysis);
		}
		analysis.show();
		mainFrame.revalidate();
	}

	/**
	 * the method to update the infomation in the info Panel
	 * 
	 * @param s
	 *            the input string to update
	 */
	private void updateInfo(String s) {
		JTextArea info = new JTextArea(s);
		info.setForeground(Color.GRAY);
		info.setFont(new java.awt.Font("Arial", Font.ITALIC, CNT6));
		infoPanel.removeAll();
		infoPanel.add(info);
		infoPanel.repaint();
		infoPanel.revalidate();
	}

	/**
	 * the override method to show the result panels
	 */
	@Override
	public void results(HashMap<String, JPanel> namePanels) {
		this.analysis.results(namePanels);
	}

}
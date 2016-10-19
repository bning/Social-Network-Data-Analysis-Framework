package edu.cmu.cs.cs214.hw5.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.cmu.cs.cs214.hw5.framework.Framework;

/**
 * the input panel class, including type user name and choose social network api
 * name
 * 
 * @author Jiyu Zhu
 *
 */
public class InputPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String NOT_SELECTABLE_OPTION = "-Select an Item-";
	private static final int R = 255;
	private static final int G = 147;
	private static final int B = 30;
	private static final int CNT1 = 17;
	private static final int CNT2 = 160;
	private static final int CNT3 = 28;

	private Framework framework;
	private JPanel panel;

	private JTextField userName;
	private JComboBox<String> apiList;
	private String[] apiNameList;

	/**
	 * the constructor of the class
	 * 
	 * @param fw
	 *            the input framework for the gui
	 */
	public InputPanel(Framework fw) {
		this.framework = fw;

		// set apiNameList
		Set<String> dataPluginsName = framework.getDataPluginNames();
		apiNameList = new String[dataPluginsName.size()];
		int cnt = 0;
		for (String s : dataPluginsName) {
			apiNameList[cnt++] = s;
		}

		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		userName = new JTextField("Type in KeyWord: ");
		userName.setFont(new java.awt.Font("Arial", Font.BOLD, CNT1));
		userName.setPreferredSize(new Dimension(CNT2, CNT3));
		userName.setForeground(Color.GRAY);
		userName.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				userName.setText("");
				userName.setForeground(new Color(R, G, B));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				userName.setText("");
				userName.setForeground(new Color(R, G, B));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		apiList = new JComboBox<String>();
		apiList.addItem(NOT_SELECTABLE_OPTION);
		for (String name : apiNameList) {
			apiList.addItem(name);
		}

		apiList.setForeground(new Color(R, G, B));
		apiList.setFont(new java.awt.Font("Arial", Font.BOLD, CNT1));
		apiList.setPreferredSize(new Dimension(CNT2, CNT3));

		panel.add(userName);
		panel.add(apiList);

		this.add(panel);
	}

	/**
	 * the override method to paint the component
	 */
	@Override
	protected void paintComponent(Graphics g) {

	}

	/**
	 * the method to clear the words in the user name field
	 */
	public void clear() {
		userName.setText("Type in KeyWord: ");
		userName.setFont(new java.awt.Font("Arial", Font.BOLD, CNT1));
		userName.setForeground(Color.GRAY);
		userName.revalidate();
		apiList.setSelectedItem(NOT_SELECTABLE_OPTION);
		apiList.revalidate();
	}

	/**
	 * add the user name and api name to the framework
	 * 
	 * @param hm
	 *            the hashmap to store the user name and api name
	 * @return 0 if store successfully, 1 for duplicate input, 2 for empty user
	 *         name, 3 for empty api name
	 */
	public int addUserNameToFramework(HashMap<String, String> hm) {
		if (!userName.getText().equals("")
				&& !userName.getText().equals("Type in KeyWord: ")) {
			String keyWord = userName.getText();
			String apiName = apiList.getSelectedItem().toString();
			String pair = keyWord + " " + apiName;
			if (hm.containsKey(pair)) {
				return 1;
			} else if (apiName.compareTo(NOT_SELECTABLE_OPTION) == 0) {
				return 2;
			} else {
				hm.put(pair, apiName);
			}
			// set the hash map to the framework
			framework.setNamePlatformPairs(hm);
		}
		return 0;
	}

}
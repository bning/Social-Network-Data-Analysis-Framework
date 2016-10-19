package edu.cmu.cs.cs214.hw5.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.cmu.cs.cs214.hw5.framework.FrameworkImpl;
import edu.cmu.cs.cs214.hw5.plugin.ConnectiveActivityAnalysisPlugin;
import edu.cmu.cs.cs214.hw5.plugin.FacebookDataPlugin;
import edu.cmu.cs.cs214.hw5.plugin.KeyWordsAnalysisPlugin;
import edu.cmu.cs.cs214.hw5.plugin.PositiveAnalysisPlugin;
import edu.cmu.cs.cs214.hw5.plugin.TwitterDataPlugin;

/** 
 * The main class to run the framework
 * 
 * @author Jiyu Zhu
 *
 */
public class Main {
	/**
	 * main method
	 * 
	 * @param args
	 *            input
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				init();
			}
		});
	}

	/**
	 * initialize the main method, including create the framework, register the
	 * data plugin and analysis plugins, and show the GUI
	 */
	public static void init() {
		// create the framework
		FrameworkImpl framework = new FrameworkImpl();
		
		// register the data-plugins
		FacebookDataPlugin fb = new FacebookDataPlugin();
		TwitterDataPlugin tw = new TwitterDataPlugin();
		framework.addDataPlugin(fb);
		framework.addDataPlugin(tw);
		
		// register the analysis-plugins
		KeyWordsAnalysisPlugin kw = new KeyWordsAnalysisPlugin();
		PositiveAnalysisPlugin positive = new PositiveAnalysisPlugin();
		ConnectiveActivityAnalysisPlugin activity = new ConnectiveActivityAnalysisPlugin();
		framework.addAnalysisPlugin(kw);
		framework.addAnalysisPlugin(positive);
		framework.addAnalysisPlugin(activity);

		// create the JFrame
		JFrame frame = new JFrame("Social Network Analysis FrameWork");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		// add the panel
		MainMenuGui gui = new MainMenuGui(frame, framework);
		frame.add(gui);
		frame.setContentPane(gui);
		frame.setVisible(true);
		frame.pack();

		framework.subscribe(gui);
	}

}
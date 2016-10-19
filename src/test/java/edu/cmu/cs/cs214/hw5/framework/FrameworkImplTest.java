package edu.cmu.cs.cs214.hw5.framework;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw5.plugin.AnalysisPlugin;

public class FrameworkImplTest {
	private FrameworkImpl frameworkImpl;

	@Before
	public void setup() {
		frameworkImpl = new FrameworkImpl();
	}

	@Test
	public void testAddDataPlugin() {
		FakeFBDataPlugin fakeFBDataPlugin = new FakeFBDataPlugin();
		frameworkImpl.addDataPlugin(fakeFBDataPlugin);
		assertTrue(frameworkImpl.getDataPluginNames().size() == 1);

		HashMap<String, String> namePlatformPairs = new HashMap<String, String>();
		namePlatformPairs.put("Lakers Facebook", "Facebook");
		frameworkImpl.setNamePlatformPairs(namePlatformPairs);
		assertTrue(frameworkImpl.getNamePlatformPairs() != null);

	}

	@Test
	public void testAddAnalysisPlugin() {
		FakeAnalysisPlugin fakeAnalysisPlugin = new FakeAnalysisPlugin();
		frameworkImpl.addAnalysisPlugin(fakeAnalysisPlugin);
		assertTrue(frameworkImpl.getAnalysisPluginNames().size() == 1);
	}

	@Test
	public void testAddGUI() {
		FakeGUI fakeGUI = new FakeGUI();
		frameworkImpl.subscribe(fakeGUI);
		assertTrue(frameworkImpl.getSubscribedFrameworkListeners().size() == 1);

	}

	@Test
	public void testSetPair() {
		frameworkImpl = new FrameworkImpl();
		FakeFBDataPlugin fakeFBDataPlugin = new FakeFBDataPlugin();
		frameworkImpl.addDataPlugin(fakeFBDataPlugin);
		assertTrue(frameworkImpl.getDataPluginNames().size() == 1);

		FakeAnalysisPlugin fakeAnalysisPlugin = new FakeAnalysisPlugin();
		frameworkImpl.addAnalysisPlugin(fakeAnalysisPlugin);
		assertTrue(frameworkImpl.getAnalysisPluginNames().size() == 1);

		FakeGUI fakeGUI = new FakeGUI();
		frameworkImpl.subscribe(fakeGUI);
		assertTrue(frameworkImpl.getSubscribedFrameworkListeners().size() == 1);

		HashMap<String, String> namePlatformPairs = new HashMap<String, String>();
		namePlatformPairs.put("Lakers Facebook", "Facebook");
		frameworkImpl.setNamePlatformPairs(namePlatformPairs);
		assertTrue(frameworkImpl.getNamePlatformPairs() != null);
	}

	@Test
	public void testSetPair2() {
		frameworkImpl = new FrameworkImpl();
		FakeTwitterDataPlugin fakeTwitterDataPlugin = new FakeTwitterDataPlugin();
		frameworkImpl.addDataPlugin(fakeTwitterDataPlugin);
		AnalysisPlugin fakeAnalysisPlugin = new FakeAnalysisPlugin();
		frameworkImpl.addAnalysisPlugin(fakeAnalysisPlugin);
		FrameworkListener fakeGUI = new FakeGUI();
		frameworkImpl.subscribe(fakeGUI);
		HashMap<String, String> namePlatformPairs = new HashMap<String, String>();
		namePlatformPairs.put("Lakers Twitter", "Twitter");
		frameworkImpl.setNamePlatformPairs(namePlatformPairs);
		assertTrue(frameworkImpl.getNamePlatformPairs().size() == 1);

	}

	@Test
	public void testSetSelectedAnalysis() {
		frameworkImpl = new FrameworkImpl();
		FakeTwitterDataPlugin fakeTwitterDataPlugin = new FakeTwitterDataPlugin();
		frameworkImpl.addDataPlugin(fakeTwitterDataPlugin);
		AnalysisPlugin fakeAnalysisPlugin = new FakeAnalysisPlugin();
		frameworkImpl.addAnalysisPlugin(fakeAnalysisPlugin);
		FrameworkListener fakeGUI = new FakeGUI();
		frameworkImpl.subscribe(fakeGUI);
		HashMap<String, String> namePlatformPairs = new HashMap<String, String>();
		namePlatformPairs.put("Lakers Twitter", "Twitter");
		frameworkImpl.setNamePlatformPairs(namePlatformPairs);
		assertTrue(frameworkImpl.getNamePlatformPairs().size() == 1);

		ArrayList<String> selectedAnalysisNames = new ArrayList<String>();
		selectedAnalysisNames.add("Analysis");
		frameworkImpl.setSelectedAnalysis(selectedAnalysisNames);
		assertTrue(frameworkImpl.getAllAnalyzedData().size() == 1);
		assertTrue(frameworkImpl.getNamePlatformPairs().size() == 1);
	}
}

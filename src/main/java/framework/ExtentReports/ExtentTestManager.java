package framework.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class ExtentTestManager {
	private static ExtentTest test;
	private static ExtentTest node;
	// private static ThreadLocal<ExtentTest> extentTest;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> extentnode = new ThreadLocal<ExtentTest>();
	private static ExtentReports extent = ExtentManagerV1.getInstance();

	public synchronized static ExtentTest getTest() {
		return extentTest.get();
	}

	public synchronized static ExtentTest getNode() {

		return extentnode.get();
	}

	public synchronized static ExtentTest createTest(String name, String description, String category) {

		test = extent.createTest(name, description);

		if (category != null && !category.isEmpty()) {
			test.assignCategory(category);
		}

		extentTest.set(test);

		return getTest();
	}

	public synchronized static ExtentTest createTest(String name, String description) {

		return createTest(name, description, null);
	}

	public synchronized static ExtentTest createTest(String name) {

		return createTest(name, null);
	}

	public synchronized static void log(String message) {
		getTest().info(message);
	}

	public synchronized static void log(Object message) {
		getTest().info(message.toString());
	}

	/** Extent Report Create Node */
	public synchronized static ExtentTest createNode(String name, String description) {

		node = getTest().createNode(name, description);

		extentnode.set(node);

		return getNode();
	}

	public synchronized static ExtentTest createNode(String name) {
		return createNode(name, null);
	}

	/** Extent Report log to Node */
	public synchronized static void log_node(String message) {
		getNode().info(message);
	}

	public synchronized static void log_node(Object message) {
		getNode().info(message.toString());
	}

}
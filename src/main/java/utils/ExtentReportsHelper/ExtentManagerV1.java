package utils.ExtentReportsHelper;

import java.io.File;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManagerV1 {
	// OB: ExtentReports extent instance created here. That instance can be
	// reachable by getReporter() method.
	static String logDirectory = new File(System.getProperty("user.dir")) + "/ExtentReport/Marriages_Automation.html";
	static String extentconfig = new File(System.getProperty("user.dir")) + "/extent-config.xml";
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	// private static ExtentTest logger;

	public static ExtentReports getInstance() {

		return extent;
	}

	public static synchronized ExtentReports createInstance() {

		if (htmlReporter == null) {

			htmlReporter = new ExtentHtmlReporter(logDirectory);

			// htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setDocumentTitle("Marriages Automation");
			htmlReporter.config().setReportName("Marriages Test Automation Report");
			htmlReporter.loadXMLConfig(extentconfig);
			// allow automatic saving of media files relative to the report
			// class view:
			htmlReporter.setAnalysisStrategy(AnalysisStrategy.CLASS);
			htmlReporter.config().enableTimeline(true);
			// htmlReporter.Configuration().JS = "$('.brand-logo').text('').append('<img
			// src=D:\\Users\\jloyzaga\\Documents\\FrameworkForJoe\\FrameworkForJoe\\name.jpg>')";

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

		}

		return extent;
	}

	public static synchronized ExtentReports createInstance(String ReportPath, String Title, String ReportName) {

		if (htmlReporter == null) {

			htmlReporter = new ExtentHtmlReporter(ReportPath);

			// htmlReporter.config().setChartVisibilityOnOpen(true);
			// htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setDocumentTitle(Title);
			htmlReporter.config().setReportName(ReportName);
			htmlReporter.loadXMLConfig(extentconfig);
			// class view:
			htmlReporter.setAnalysisStrategy(AnalysisStrategy.CLASS);
			htmlReporter.config().enableTimeline(true);

			// allow automatic saving of media files relative to the report

			// htmlReporter.Configuration().JS = "$('.brand-logo').text('').append('<img
			// src=D:\\Users\\jloyzaga\\Documents\\FrameworkForJoe\\FrameworkForJoe\\name.jpg>')";

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

		}

		return extent;
	}

}
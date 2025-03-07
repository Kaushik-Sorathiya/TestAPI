package API_TestCases;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import utils.DateTimeHelper.DateTimeHelper;
import utils.ExtentReportsHelper.ExtentManagerV1;
import utils.ExtentReportsHelper.ExtentTestManager;
import io.restassured.RestAssured;

public class BaseTest {

	public static StringWriter requestwriter;
	public static PrintStream requestcapture;
	
	public static StringWriter responsewriter;
	public static PrintStream responsecapture;
	
	public static String reportDirectory = new File(System.getProperty("user.dir")) + "/API/Test_Execution_Report"
			+ DateTimeHelper.getCurrentDateTime() + ".html";


	@SuppressWarnings("deprecation")
	@BeforeMethod
	@Parameters({ "BaseURI"})
	public void Before_Method(String BaseURI) throws Exception {

		ExtentManagerV1.createInstance(reportDirectory,
				"Test Execution Report " + DateTimeHelper.getCurrentDateTime("- dd/MM/yyyy_HH:mm:ss"),
				"Test Execution Report " + DateTimeHelper.getCurrentDateTime("- dd/MM/yyyy_HH:mm:ss"));
		
		requestwriter = new StringWriter();
		requestcapture = new PrintStream(new WriterOutputStream(requestwriter), true);

		responsewriter = new StringWriter();
		responsecapture = new PrintStream(new WriterOutputStream(responsewriter), true);
		
			RestAssured.baseURI = BaseURI;
			System.out.println("Getting Connection Ready....");


	}

	@AfterMethod
	public void After_Method(ITestResult result) throws Exception {

		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				ExtentTestManager.getNode().log(Status.PASS,
						MarkupHelper.createLabel(result.getName() + " Test case PASSED", ExtentColor.GREEN));

			} else if (result.getStatus() == ITestResult.FAILURE) {
				ExtentTestManager.getNode().log(Status.FAIL, MarkupHelper.createLabel(
						" Method : " + result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
				ExtentTestManager.getNode().fail(result.getThrowable());

			} else if (result.getStatus() == ITestResult.SKIP) {
				ExtentTestManager.getNode().log(Status.SKIP,
						MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
				ExtentTestManager.getNode().skip(result.getThrowable());
			}
			ExtentManagerV1.getInstance().flush();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@AfterSuite
	public void Classteardown() throws InterruptedException{
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			ExtentManagerV1.getInstance().flush();

		}
	}
	
}

package API_TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import API_Methods.GetCall;
import framework.ExtentReports.ExtentTestManager;
import framework.JSONUtil.JSON;


public class API_Tests extends TestBase {
	GetCall Get = new GetCall();
	JSON json = new JSON();

	@Test(description = "Get Category Details")
	public void Test1(){
		try {

			ExtentTestManager.createTest("Get Category Details");
			String path = "Categories/6327/Details.json?catalogue=false";
			ExtentTestManager.createNode("Get Category Details").log(Status.INFO,
					MarkupHelper.createLabel("GET "+path, ExtentColor.BLUE));
			
			String Response = Get.API_get(path);
			json.Log_Response(Response);
			
			String Name = JSON.getData(Response, "$.Name");
			json.ValidateResponseCode("Name","Carbon credits", Name);
			
			String CanRelist = JSON.getData(Response, "$.CanRelist");
			json.ValidateResponseCode("CanRelist","true", CanRelist);

			String arrayName = "Promotions";
			String objectName = "Gallery";
			String descriptionValue = "Good position in category";
			if (json.ValidateDescriptionforName(arrayName, json.getJsonArray(Response, arrayName) ,objectName,descriptionValue)) {
				
				ExtentTestManager.getNode().log(Status.PASS,
						MarkupHelper.createLabel(arrayName+" has Object "+objectName+ " with Description contains "+descriptionValue,
								ExtentColor.GREEN));
			} else {
				ExtentTestManager.getNode().log(Status.FAIL,
						MarkupHelper.createLabel(arrayName+" has Object "+objectName+ " with Description does not contains "+descriptionValue,
								ExtentColor.RED));
			};

		} catch (Exception e) {
			org.testng.Assert.fail("Test Failed");
		}

	}

}

package API_Methods;

import org.apache.log4j.Logger;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import utils.ExtentReportsHelper.ExtentTestManager;
import utils.LoggerHelper.LoggerHelper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetCall	 {

	private Logger oLog = LoggerHelper.getLogger(GetCall.class);

	public String API_get(String path){
		oLog.info("Request Endpoint is : "+RestAssured.baseURI+path);
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json");
		Response response = httpRequest.request(Method.GET, path);
		int Statuscode = response.getStatusCode();

		if (Statuscode == 200) {
			oLog.info("Response Status code is "+response.getStatusCode());

			ExtentTestManager.getNode().log(Status.PASS,
					MarkupHelper.createLabel("Successfully received response", ExtentColor.GREEN));

			String responseBody = response.getBody().asString();
			return responseBody;
		}
		else {

			ExtentTestManager.getNode().log(Status.INFO, MarkupHelper.createLabel(
					"Failed to get Data ; Response Code is :" + Statuscode, ExtentColor.RED));
		}
		return "No Response";
		}
}
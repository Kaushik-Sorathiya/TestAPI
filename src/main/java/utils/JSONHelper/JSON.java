package utils.JSONHelper;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import utils.ExtentReportsHelper.ExtentTestManager;
import utils.LoggerHelper.LoggerHelper;
import org.apache.log4j.Logger;


public class JSON {
	private Logger oLog = LoggerHelper.getLogger(JSON.class);
	public JSON() {

	}

	public Boolean ValidateResponseCode(String Value, String expected, String actual) {
		String Expected = expected.toString().trim().replaceAll("\\P{Print}", "");
		String Actual = actual.toString().trim().replaceAll("\\P{Print}", "");
		try {
		if (Expected.equalsIgnoreCase(Actual)) {
			System.out.println("Data Matches for "+Value+ " : "+ Expected);
			ExtentTestManager.getNode().log(Status.PASS,
					MarkupHelper.createLabel("Data validated successfully for key : "+Value
							+ System.lineSeparator() + "<br>" + "Expected Value: " + Expected
							+ System.lineSeparator() + "<br>" + "Actual Value  : " + Actual,
							ExtentColor.GREEN));
			return true;
		} 
		else {
			ExtentTestManager.getNode().log(Status.FAIL,
					MarkupHelper.createLabel("Data does not Match for key : "+Value
							+ System.lineSeparator() + "<br>" + "Expected Value: " + Expected
							+ System.lineSeparator() + "<br>" + "Actual Value  : " + Actual,
							ExtentColor.RED));
			return false;
		}
		}catch (Exception | AssertionError e) {
			ExtentTestManager.getNode().log(Status.FAIL,
					MarkupHelper.createLabel("Data does not Match for key : "+Value
							+ System.lineSeparator() + "<br>" + "Expected Value: " + Expected
							+ System.lineSeparator() + "<br>" + "Actual Value  : " + Actual
							+ System.lineSeparator() + "<br>" + "Error Message :" + e.getMessage(),
							ExtentColor.RED));
			
			return false;
		}
	}
	
	public JsonArray getJsonArray(String Response, String ArrayName) {

		JsonArray promotionsObject = new JsonArray();
		 if(!Response.isEmpty())
		 {
			promotionsObject= JSON.createJsonArray(JSON.findMemberValue(ArrayName, Response));
		 }
	
		return promotionsObject;
	}
	
	public int getindexForName(String Value, JsonArray jsonarray) {
		for (int i = 0; i < jsonarray.size(); i++) {
			if (jsonarray.get(i).getAsJsonObject().get("Name").getAsString().equals(Value)) {
				return i;
			}
		}
		return -1;
	}

	public boolean ValidateDescriptionforName(String arrayName, JsonArray jsonarray, String value, String description) {
		
		boolean found = false;
		if (jsonarray.size()>0) {
			JsonElement element;
			String descvalue;
			
			int i = getindexForName(value,jsonarray);
			element = jsonarray.get(i);
			descvalue = element.getAsJsonObject().get("Description").getAsString();
			if (descvalue.contains(description)) {
				found = true;
				oLog.info(arrayName+" has Object "+value+ " with Description contains "+description);
				return found;
			}
			
		}
		else {
			found = false;
		}
		return found;
	}

	public static String getData(String Input, String JSONpath) {
		 String DetailsValue = null;

		try {
			if (Input != null) {

				DetailsValue = JsonPath.read(Input, JSONpath).toString();
				return DetailsValue;

			} else {
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return DetailsValue;
	}

	public static String PrettyPrintJSON(String json) {
		String prettyJsonString = null;
		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

			@SuppressWarnings("deprecation")
			JsonParser jp = new JsonParser();

			@SuppressWarnings("deprecation")
			JsonElement je = jp.parse(json);
			prettyJsonString = gson.toJson(je);

			return prettyJsonString;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prettyJsonString;
	}

	public void Log_Response(String AppData) throws Exception {

		if (AppData != null) {
			
			ExtentTestManager.getNode().log(Status.INFO, "Response Body is : "
					+ System.lineSeparator() + "<br>" + "<pre>" + (PrettyPrintJSON(AppData)) + "</pre>");
			oLog.info("Response Body is "+ (PrettyPrintJSON(AppData)));

		} else {
			throw new Exception("Data not found");
		}
		
	}

	public static String findMemberValue(String memberName,String jsonString)
	{
		JsonObject jsonobject = new Gson().fromJson(jsonString, JsonObject.class);
		return jsonobject.has(memberName) ? jsonobject.get(memberName).toString():"" ;
	}
	
	public static JsonArray createJsonArray(String jsonBody)
	{
		JsonParser parser = new JsonParser();
		return parser.parse(jsonBody).getAsJsonArray();
	}
}

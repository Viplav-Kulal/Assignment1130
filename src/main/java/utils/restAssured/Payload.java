package utils.restAssured;

import java.io.File;

import org.json.simple.JSONObject;

import io.restassured.path.json.JsonPath;

public class Payload {

	public static String getJSON(String filePath) {
		try {
			JsonPath jp = new JsonPath(new File(filePath));
			return jp.toString();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static JSONObject createBillPayload(String toAcc, String name, String phone, String street, String city,
			String state, String zip) {
		JSONObject address = new JSONObject();
		address.put("street", street);
		address.put("city", city);
		address.put("state", state);
		address.put("zipCode", zip);

		JSONObject js = new JSONObject();
		js.put("name", name);
		js.put("accountNumber", toAcc);
		js.put("phoneNumber", phone);
		js.put("address", address);

		return js;
	}
}

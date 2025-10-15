package com.restassured;


import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class ClientCredentialsOAuth {
	@Test
	public void clientCredOAuth() {
//		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all().extract()
				.response().asString();
		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");

		String courseDetails = given().queryParam("access_token", accessToken).when()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().extract().response().asString();
		
		System.out.println(courseDetails);
	}
}

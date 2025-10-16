import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

public class ECommerceApiE2E {

	public static void main(String[] args) {
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType("application/json").build();
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();

		// Login API
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("naveennagarajan34@gmail.com");
		loginRequest.setUserPassword("Test@123");
		LoginResponse loginRes = given().spec(req).body(loginRequest).when().post("/api/ecom/auth/login").then()
				.spec(res).extract().response().as(LoginResponse.class);
		System.out.println(loginRes.getToken() + " " + loginRes.getUserId() + " " + loginRes.getMessage());

		// Add new product API
		RequestSpecification createProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", loginRes.getToken()).build();
		String response = given().spec(createProduct).param("productName", "Hp Elitebook")
				.param("productAddedBy", loginRes.getUserId()).param("productCategory", "Computers and Laptops")
				.param("productSubCategory", "Laptops").param("productPrice", "64000")
				.param("productDescription", "Core i7, 16GB RAM, 1TB SSD").param("productFor", "All")
				.multiPart("productImage", new File("D:\\Udemy\\RestAssured\\elite.jpg")).when()
				.post("/api/ecom/product/add-product").then().log().all().extract().response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String product_id = js.get("productId");

		// Place Order API

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", loginRes.getToken()).setContentType(ContentType.JSON).build();

		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(product_id);

		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		orderDetailsList.add(orderDetails);
		Orders orders = new Orders();
		orders.setOrder(orderDetailsList);

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);

		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().asString();
		System.out.println(responseAddOrder);

		// Delete product API
		RequestSpecification deleteProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", loginRes.getToken()).build();
		String deleteResponse = given().spec(deleteProduct).pathParam("productId", product_id).when()
				.delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response()
				.asString();
		System.out.println(deleteResponse);

	}

}
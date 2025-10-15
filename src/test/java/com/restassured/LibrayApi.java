package com.restassured;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class LibrayApi {
    private String bookId;

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        String response = given()
            .baseUri("http://216.10.245.166") 
            .header("Content-Type", "application/json")
            .body(payload.AddBook(isbn, aisle))
        .when()
            .post("/Library/Addbook.php")
        .then()
            .assertThat().statusCode(200)
            .extract().response().asString();	

        System.out.println(response);
        // Parse response with JsonPath
        JsonPath js = new JsonPath(response);
        this.bookId = js.getString("ID");
        System.out.println("Book ID: " + this.bookId);
    }

//    @Test(dependsOnMethods = "addBook")
    public void getBook() {
        String response = given()
            .baseUri("http://216.10.245.166")
            .queryParam("ID", this.bookId)
            .header("Content-Type", "application/json")
        .when()
            .get("/Library/GetBook.php")
        .then()
            .assertThat().statusCode(200)
            .extract().response().asString();

        System.out.println("Get Book Response: " + response);
    }
    
    @DataProvider(name="BooksData")
    public Object[][] getData() {
		return new Object[][] {{"naveen", "12345"}, {"nagarajan", "54321"}, {"shan", "67890"}};
	}
    
    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
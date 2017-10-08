package mystuff;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class MyRestTest {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost/restfulbook";
        RestAssured.port = 8080;
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    
	//body("greeting.firstName", equalTo("John"));    	
	/*
	
	expect().body("title", 
			equalTo("Hello world!")).when().
	get("http://localhost:8080/restfulbook/timezone");
	*/

	/*
    get("/restfulbook/timezone")
    .then()
    .body("id", equalTo(12))
    .body("firstName", equalTo("Vinod"))
    .body("lastName", equalTo("Kashyap"))
    .body("designation", equalTo("CEO"));
    */
	private void getACustomer() {
		// just created customer 1, now get it
    	given().
        when().
        get("/customers/1").
        then().
        statusCode(200).
        body("customer.lastName" , equalTo("Smith1"));
	}
    
    
    @Test
    public void testGetOtherPage() {
    	
    	when().
        request("GET", "/timezone").
        then().
        statusCode(200);
    }
    	
    @Test
    public void testPutCustomer1() {
    	given().
    	contentType(ContentType.XML).
    	body("<lastName>Doe</lastName>").
        when().
        put("/customers/1").
        then().
        statusCode(204);
    	
    	getACustomer();
    	
    }

    @Test
    public void testPostCustomers() {
    	given().
        when().
        post("/customers").
        then().
        statusCode(201);
    	
    	getACustomer();
    	
    }
}

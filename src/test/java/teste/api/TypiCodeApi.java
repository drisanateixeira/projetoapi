package teste.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static teste.api.utils.Constants.*;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class TypiCodeApi{
  
	private static String body =
			"{\n" + 
			"    \"name\": \"Drisana\",\n" + 
			"    \"username\": \"Bret\",\n" + 
			"    \"email\": \"Sincere@april.biz\",\n" + 
			"    \"address\": {\n" + 
			"        \"street\": \"Kulas Light\",\n" + 
			"        \"suite\": \"Apt. 556\",\n" + 
			"        \"city\": \"Gwenborough\",\n" + 
			"        \"zipcode\": \"92998-3874\",\n" + 
			"        \"geo\": {\n" + 
			"            \"lat\": \"-37.3159\",\n" + 
			"            \"lng\": \"81.1496\"\n" + 
			"        }\n" + 
			"    },\n" + 
			"    \"phone\": \"1-770-736-8031 x56442\",\n" + 
			"    \"website\": \"hildegard.org\",\n" + 
			"    \"company\": {\n" + 
			"        \"name\": \"Romaguera-Crona\",\n" + 
			"        \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" + 
			"        \"bs\": \"harness real-time e-markets\"\n" + 
			"    }\n" + 
			"}";
	
	
	@Test
	public void usersGet() {
		given()
			.log().all()
		.when()
			.get(URL + PATH_USERS )
		.then()
			.log().all()
			.statusCode(200)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"));
	}
	
	@Test
	public void usersPost() {
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body(body)
		.when()
			.post(URL + PATH_USERS )
		.then()
			.log().all()
			.statusCode(201)
			.body("id",is(notNullValue()))
			.body("name",is("Drisana"))
		;
	}
	
	@Test
	public void usersDelete() {
		given()
			.log().all()
		.when()
			.delete(URL + PATH_USERS + ID )
		.then()
			.log().all()
			.statusCode(200)
			;
	}
	

	@Test
	public void usersPut() {
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body(body)
		.when()
			.put(URL + PATH_USERS + ID )
		.then()
			.log().all()
			.statusCode(200)
			.body("id",is(1))
			.body("name",is("Drisana"))
			;
	}
	
}

package com.workspace;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class creteWorkSpaceUsingHashMapForPost {
    @BeforeTest
    public void init() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("X-Api-Key", "PMAK-644e4773322914002a52543a-f58b1df48eced899d777a0cb04085ccede").
                setContentType(ContentType.JSON);
        RestAssured.requestSpecification = requestSpecBuilder.build().log().all();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).expectStatusCode(200);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void postCallFromObject() {
        HashMap<String, Object> mainObject = new HashMap<String, Object>();

        HashMap<String, String> nestedobject = new HashMap<String, String >();

        nestedobject.put("name", "From hashmap Object");
        nestedobject.put("type", "personal");
        nestedobject.put("description", "Adding from hashmap with nested object");

        mainObject.put("workspace", nestedobject);

       System.out.println(mainObject.get("workspace"));
        System.out.println(mainObject.toString());
        given().body(mainObject).
                when().post("/workspaces").
                then().log().all().assertThat().body("workspace.id", not(empty()),
                        "workspace.name", equalTo("From hashmap Object"));

    }
}


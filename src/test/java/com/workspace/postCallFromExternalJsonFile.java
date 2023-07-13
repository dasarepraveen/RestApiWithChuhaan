package com.workspace;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class postCallFromExternalJsonFile {
    @BeforeTest
    public void init(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("X-Api-Key","PMAK-644e4773322914002a52543a-f58b1df48eced899d777a0cb04085ccede")
                .setContentType(ContentType.JSON);
        RestAssured.requestSpecification =  requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON);
        RestAssured.responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void postCallTestFromExternalJsonFile(){
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/CreateworkLoadPayload.json");
        given().body(file)
                .when().post("/workspaces").
                then().log().all().
                assertThat().body("workspace.id",not(empty()),
                "workspace.name",equalTo("From File"));
    }
}

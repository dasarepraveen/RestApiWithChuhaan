package com.workspace;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class post {
    //RequestSpecification requestSpecification;
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
    public void tvalidatePostCall(){
        given().body("{\n" +
                        "    \"workspace\": {\n" +
                        "        \"name\": \"eno louda\",\n" +
                        "        \"description\": \"asdas\",\n" +
                        "        \"type\": \"personal\"\n" +
                        "    }\n" +
                        "}").when().post("/workspaces")
                .then().log().all().assertThat().body("workspace.id",not(empty()),
                        "workspace.name",equalTo("eno louda"));
    }

    @Test
    public void updateCall(){
        given().body("{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"eno louda update\",\n" +
                "        \"description\": \"asdas updated\",\n" +
                "        \"type\": \"personal\"\n" +
                "    }\n" +
                "}").
                when().put("/workspaces/f8b0d516-fcc9-4a4e-8a9c-7d4e407f6b64")
                .then().log().all().assertThat().body("workspace.name",equalTo("eno louda update"));
    }

    @Test
    public void vDeleteCall(){
        given().when().delete("/workspaces/f8b0d516-fcc9-4a4e-8a9c-7d4e407f6b64")
                .then().log().all().assertThat().body("workspace.id",equalTo("f8b0d516-fcc9-4a4e-8a9c-7d4e407f6b64"));
    }
}

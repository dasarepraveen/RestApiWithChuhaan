package com.workspace;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class createAPostCallUsingMock {
    ResponseSpecification custom;

    @BeforeTest
    public void init(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://a25a0504-b3cf-4fbe-838e-7a33af99a699.mock.pstmn.io")
                .setContentType(ContentType.JSON)
                .addHeader("x-mock-match-request-body","true");
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).log(LogDetail.ALL);

        custom = responseSpecBuilder.build();

    }

    @Test
    public void mockRequestCall(){
        HashMap<String,String> object1 = new HashMap<>();
        HashMap<String,String> object2 = new HashMap<>();
        object1.put("id","5001");
        object1.put("typw","none");
        object2.put("id","5002");
        object2.put("typw","glazed");
        List<HashMap<String,String>> al  = new ArrayList<>();
        al.add(object1);
        al.add(object2);
        given().
                body(al).log().all()
                .when().post("/post").
                then().spec(custom).log().all().assertThat().body("message",equalTo("Success"));

    }
}

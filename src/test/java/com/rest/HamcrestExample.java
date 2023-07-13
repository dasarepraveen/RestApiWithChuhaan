package com.rest;

import io.restassured.config.LogConfig;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HamcrestExample {
    @Test
    public void HamcrestAssertions(){

              Response response = given().baseUri("https://api.postman.com").header
                ("X-Api-Key","PMAK-644e4773322914002a52543a-f58b1df48eced899d777a0cb04085ccede").
                      config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))).log().all().
                when().get("workspaces").
                then().log().all()
                .assertThat().statusCode(200).
                       body("workspaces.name",contains("Team Workspace","My Workspace"),
                               "workspaces[0]",hasKey("id"),
                               "workspaces[0]",hasValue("58403759-fc60-4c81-8466-4c4dc2482bdb"))
                      .extract().response();
             System.out.println("using path "+response.path("workspaces.name"));
             JsonPath jsonPath = new JsonPath(response.asString());
             System.out.println("from json path "+jsonPath.get("workspaces[0].name"));


               //contains is a strict match
               //has items is not a strict match
              //hamcrest assertions are applied in the response body


    }
}

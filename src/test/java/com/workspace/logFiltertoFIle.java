package com.workspace;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class logFiltertoFIle {
    @Test
    public void logging() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File("restAssured.log"));
        given().baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.ALL,ps))
                .filter(new ResponseLoggingFilter(LogDetail.ALL,ps))
                .when().get("/get").
                then().assertThat().statusCode(200);
    }
}

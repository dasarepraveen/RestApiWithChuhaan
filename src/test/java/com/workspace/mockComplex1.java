package com.workspace;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.List.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class mockComplex1 {
    ResponseSpecification custom;
    @BeforeTest
    public void init(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON)
                .setBaseUri("https://a25a0504-b3cf-4fbe-838e-7a33af99a699.mock.pstmn.io")
                .addHeader("x-mock-match-request-body","true");
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder =  new ResponseSpecBuilder().
        expectStatusCode(200).expectContentType(ContentType.JSON);

        custom = responseSpecBuilder.build();
    }


    @Test
    public void complex1()
    {
        List<Integer> idIntegers = new ArrayList<Integer>();
        idIntegers.add(5);
        idIntegers.add(9);
        HashMap<String,Object> hashMapBatter2 = new HashMap<String,Object>();
        hashMapBatter2.put("id",idIntegers);
        hashMapBatter2.put("type","choclate");

        HashMap<String,Object> hashMapBatter1 = new HashMap<String,Object>();
        hashMapBatter1.put("id","1011");
        hashMapBatter1.put("type","regular");

       List<HashMap<String,Object>> batterArrayList = new ArrayList<HashMap<String,Object>>();
       batterArrayList.add(hashMapBatter1);
        batterArrayList.add(hashMapBatter2);

        HashMap<String, List<HashMap<String, Object>>> battersHashMap = new HashMap<String, List<HashMap<String, Object>>>();
        battersHashMap.put("batter",batterArrayList);

        List<String> typeList = new ArrayList<>();
        typeList.add("test1");
        typeList.add("test2");
        HashMap<String,Object> toppling2 = new HashMap<>();
        toppling2.put("id","5002");
        toppling2.put("type",typeList);

        HashMap<String,Object> toppling1 = new HashMap<>();
        toppling1.put("id","5001");
        toppling1.put("type","none");

       List<HashMap<String,Object>> hashMaps = new ArrayList<>();
       hashMaps.add(toppling1);
        hashMaps.add(toppling2);

       HashMap<String,Object> mainHashMap = new HashMap<>();

       mainHashMap.put("id","0001");
       mainHashMap.put("type","donut");
       mainHashMap.put("name","Cake");
       mainHashMap.put("ppu","0.55");
       mainHashMap.put("batters",battersHashMap);
        mainHashMap.put("topping",hashMaps);

        given().body(mainHashMap).
                when().post("/postcomplex1").
                then().spec(custom).log().all().assertThat().body("message",equalTo("success"));



    }
}

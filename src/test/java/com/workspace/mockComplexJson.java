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
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class mockComplexJson {
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
    public void complexJson(){
        List<Integer> rgba1 = new ArrayList<>();
        rgba1.add(255);
        rgba1.add(255);
        rgba1.add(255);
        rgba1.add(1);
        HashMap<String,Object> code1 = new HashMap<>();
        code1.put("rgba",rgba1);
        code1.put("hex","#000");

        HashMap<String,Object> colors1 = new HashMap<>();
        colors1.put("color","black");
        colors1.put("category","hue");
        colors1.put("type","primary");
        colors1.put("code",code1);

        List<Integer> rgba2 = new ArrayList<>();
        rgba2.add(0);
        rgba2.add(0);
        rgba2.add(0);
        rgba2.add(1);
        HashMap<String,Object> code2 = new HashMap<>();
        code2.put("rgba",rgba2);
        code2.put("hex","#FFF");

        HashMap<String,Object> colors2 = new HashMap<>();
        colors2.put("color","white");
        colors2.put("category","value");
        colors2.put("code",code2);

        List<HashMap<String,Object>> colosAeeay = new ArrayList<>();
        colosAeeay.add(colors1);
        colosAeeay.add(colors2);

        HashMap<String,List<HashMap<String,Object>>> mainObject = new HashMap<>();
        mainObject.put("colors",colosAeeay);



//        HashMap<String,Object> colors1 = new HashMap<>();
//        colors1.put("color","black");
//        colors1.put("category","hue");
//        colors1.put("type","primary");
//        List<Integer> al = new ArrayList<>();
//        al.add(255);
//        al.add(255);
//        al.add(255);
//        al.add(1);
//        HashMap<String,Object> code1 = new HashMap<>();
//        code1.put("rgba",al);
//        code1.put("hex","#000");
//        colors1.put("code",code1);
//
//        //-----------------------
//        HashMap<String,Object> colors2 = new HashMap<>();
//        colors2.put("color","white");
//        colors2.put("category","value");
//        List<Integer> al1 = new ArrayList<>();
//        al1.add(0);
//        al1.add(0);
//        al1.add(0);
//        al1.add(1);
//        HashMap<String,Object> code2 = new HashMap<>();
//        code2.put("rgba",al);
//        code2.put("hex","#FFF");
//
//        colors2.put("code",code2);
//
//        HashMap mMap = new HashMap();
//        ArrayList list = new ArrayList();
//        list.add(colors1);
//        list.add(colors2);
//        mMap.put("colors",list);



        given().body(mainObject).log().all()
                .when().post("/postcomplex")
                .then().spec(custom).log().all().assertThat().body("message",equalTo("success"));
    }

}

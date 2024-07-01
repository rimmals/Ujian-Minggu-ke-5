package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginWithJWTTest {
    private static String token;
    String baseUrl = "http://localhost:8081/api";


    @Test
    public void testLogin(){
        String endpoint = baseUrl+"/auth/signin";
        JSONObject requestBody = new JSONObject();
        requestBody.put("usernameOrEmail","rimmzz");
        requestBody.put("password","1234566");
        System.out.println(requestBody.toJSONString());

        RequestSpecification request = RestAssured.given();
        request.header("content-type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        Assert.assertEquals(response.getStatusCode(),200);
        token = response.getBody().jsonPath().getString("accessToken");
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void testLoginInvalid(){
        String endpoint = baseUrl+"/signin";
        JSONObject requestBody = new JSONObject();
        requestBody.put("usernameOrEmail;","rimmzz");
        requestBody.put("password","password");
        System.out.println(requestBody.toJSONString());

        RequestSpecification request = RestAssured.given();
        request.header("content-type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        Assert.assertEquals(response.getStatusCode(), 401);
        String error = response.getBody().jsonPath().getString("error");
        System.out.println(error);
        Assert.assertNotNull(error);
    }
}

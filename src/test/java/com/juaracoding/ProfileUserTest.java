package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileUserTest {
    private static String token;
    String baseUrl = "http://localhost:8081/api";
    @Test
    public void testGetUserProfile() {

        String endpoint = baseUrl+"/users/me";
        JSONObject request = new JSONObject();

        String tokenProfile = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE5NzYyOTA4LCJleHAiOjE3MTk3NjY1MDh9.rFXeKhuk1AtuDxfgRTOkAPQpId6J8-BOnlXzV1M8CpLRr7FWq-l8NYyUv9W4u80-_iJyATnM5HUCbQFUuTjdkA";

        RequestSpecification requestBody = RestAssured.given();
        requestBody.header("Authorization", "Bearer " + tokenProfile);
        requestBody.header("Content-Type", "application/json");

        System.out.println(request.toJSONString());

        Response response = requestBody.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);


        String id = response.getBody().jsonPath().getString("id");
        System.out.println(id);
        String firstName = response.getBody().jsonPath().getString("firstName");
        System.out.println(firstName);
        String lastName = response.getBody().jsonPath().getString("lastName");
        System.out.println(lastName);
        String username = response.getBody().jsonPath().getString("username");
        System.out.println(username);

        Assert.assertEquals(id, "1");
        Assert.assertEquals(firstName, "rimmzz");
        Assert.assertEquals(lastName, "lestari");
        Assert.assertEquals(username, "rimmzz");
    }
}

package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTest {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE5Nzk2MzM1LCJleHAiOjE3MTk3OTk5MzV9.JpnLW6bplj05WVKIcaZKWJiH5RRpG4BqkCcrUl1p0q1YH58POcARpU5DGRwYkFM6RJRcPfyrNfxsJ0dRHu0i-g";


    @Test
    public void testCreateAlbum() {
        String endpoint = baseUrl + "/albums";
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Test ya");
        requestBody.put("description", "Album description");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());
        Response response = request.post(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        String albumId = response.getBody().jsonPath().getString("id");
        Assert.assertNotNull(albumId);
    }
}

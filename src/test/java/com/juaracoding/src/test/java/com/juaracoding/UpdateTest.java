package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateTest {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE5Nzk2MzM1LCJleHAiOjE3MTk3OTk5MzV9.JpnLW6bplj05WVKIcaZKWJiH5RRpG4BqkCcrUl1p0q1YH58POcARpU5DGRwYkFM6RJRcPfyrNfxsJ0dRHu0i-g";


    @Test
    public void testUpdateAlbum() {
        String albumId = "2";
        String endpoint = baseUrl + "/albums/" + albumId;

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "APA ya");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.get(endpoint);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Memastikan status code adalah 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code tidak sesuai!");

        String updatedTitle = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(updatedTitle, "APA ya");
    }
}

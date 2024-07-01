package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteTest {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE5Nzk2MzM1LCJleHAiOjE3MTk3OTk5MzV9.JpnLW6bplj05WVKIcaZKWJiH5RRpG4BqkCcrUl1p0q1YH58POcARpU5DGRwYkFM6RJRcPfyrNfxsJ0dRHu0i-g";
    String albumId = "2";

    @Test
    public void testDeleteAlbum() {
        String endpoint = baseUrl + "/albums/" + albumId;

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        Response response = request.delete(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
    }

}

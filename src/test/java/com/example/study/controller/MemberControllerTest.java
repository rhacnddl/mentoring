package com.example.study.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {

    private static final String address = "http://localhost:8081";

    @Order(3)
    @DisplayName("get 요청")
    @Test
    public void getTest() {
        Response response = given()
                .when()
                .get(address + "/member/getAll");

        response.then()
                .statusCode(200)
                .body("name", equalTo("홍길동"))
                .body("age", equalTo(12));
    }

    @Order(1)
    @DisplayName("post 요청")
    @Test
    public void postTest() {
        given()
                .when()
                .post(address + "/add/role?roleName=기사")
                .then()
                .statusCode(200);
    }

    @Order(2)
    @DisplayName("post 요청2")
    @Test
    public void postTest2() throws IOException {
        File file = new ClassPathResource("test.json").getFile();
        String json = new String(Files.readAllBytes(file.toPath()));
        given()
                .contentType("application/json").body(json)
                .when()
                .post(address + "/join/member")
                .then()
                .statusCode(200);
    }
}
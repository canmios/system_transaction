package com.metro.technical.domain.load;

import com.metro.technical.application.port.in.controllers.models.TransactionRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LoadTest {

    private static final int NUM_TRANSACTIONS = 10000;

    @Test
    void testLoad() {
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i = 0; i < NUM_TRANSACTIONS; i++) {
            int finalI = i;
            executorService.submit(() -> {
                TransactionRequest transaction = new TransactionRequest();
                transaction.setTransactionId(String.valueOf(finalI));
                transaction.setTimestamp(Instant.now());
                transaction.setDeviceNumber("device" + finalI);
                transaction.setUserId("user" + finalI);
                transaction.setGeoPosition("40.7128,-74.0060");
                transaction.setAmount(100.0);

                try {
                    given()
                            .contentType(ContentType.JSON)
                            .body(transaction)
                            .when()
                            .post("http://localhost:8080/transactions")
                            .then()
                            .statusCode(200)
                            .body(is("Transaction processed successfully"));

                } catch (Exception e) {
                    System.err.println("Error processing transaction: " + finalI);
                    e.printStackTrace();
                    Response response = given()
                            .contentType(ContentType.JSON)
                            .body(transaction)
                            .when()
                            .post("http://localhost:8080/transactions")
                            .andReturn();
                    System.out.println("Response: " + response.asString());
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // wait for all tasks to finish
        }
    }
}
package com.metro.technical.application.port.in.controllers;

import com.metro.technical.application.port.in.controllers.models.TransactionRequest;
import com.metro.technical.domain.model.transaction.Transaction;
import com.metro.technical.domain.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionResourceTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionResource transactionResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionId("12345");
        transactionRequest.setTimestamp(Instant.now());
        transactionRequest.setDeviceNumber("device123");
        transactionRequest.setUserId("user123");
        transactionRequest.setGeoPosition("40.7128,-74.0060");
        transactionRequest.setAmount(100.0);

        when(transactionService.processTransactionAsync(any(Transaction.class))).thenReturn(CompletableFuture.completedFuture(null));

        Response response = transactionResource.createTransaction(transactionRequest);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Transaction processed successfully", response.getEntity());
        verify(transactionService, times(1)).processTransactionAsync(any(Transaction.class));
    }
}

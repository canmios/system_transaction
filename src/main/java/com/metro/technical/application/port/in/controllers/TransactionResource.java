package com.metro.technical.application.port.in.controllers;

import com.metro.technical.application.port.in.controllers.doc.TransactionResourceDoc;
import com.metro.technical.application.port.in.controllers.mapper.TransactionMapper;
import com.metro.technical.application.port.in.controllers.models.TransactionRequest;
import com.metro.technical.domain.model.transaction.Transaction;
import com.metro.technical.domain.service.TransactionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.CompletableFuture;

@Path("/transactions")
public class TransactionResource implements TransactionResourceDoc {

    private final TransactionService transactionService;

    @Inject
    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = TransactionMapper.INSTANCE.toTransaction(transactionRequest);
        CompletableFuture<Void> future = transactionService.processTransactionAsync(transaction);
        future.join();
        return Response.ok("Transaction processed successfully").build();
    }
}

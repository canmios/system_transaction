package com.metro.technical.domain.port.in;

import com.metro.technical.domain.model.transaction.Transaction;

import java.util.concurrent.CompletableFuture;

public interface TransactionUseCase {
    CompletableFuture<Void> processTransactionAsync(Transaction transaction);
}

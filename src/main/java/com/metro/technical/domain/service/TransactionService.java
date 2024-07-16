package com.metro.technical.domain.service;

import com.metro.technical.domain.model.transaction.Transaction;
import com.metro.technical.domain.port.in.TransactionUseCase;
import com.metro.technical.infrastructure.messaging.TransactionPublisher;
import com.metro.technical.infrastructure.repository.TransactionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class TransactionService implements TransactionUseCase {

    TransactionRepository transactionRepository;

    TransactionPublisher transactionPublisher;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Inject
    public TransactionService(TransactionRepository transactionRepository, TransactionPublisher transactionPublisher) {
        this.transactionRepository = transactionRepository;
        this.transactionPublisher = transactionPublisher;
    }

    @Override
    @Retry(name = "transactionService")
    @CircuitBreaker(name = "transactionService")
    @Transactional
    public CompletableFuture<Void> processTransactionAsync(Transaction transaction) {
        return CompletableFuture.runAsync(() -> {
            transactionRepository.save(transaction);
            transactionPublisher.publish(transaction);
        }, executorService);
    }
}


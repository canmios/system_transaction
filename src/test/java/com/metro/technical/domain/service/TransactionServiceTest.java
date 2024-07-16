package com.metro.technical.domain.service;

import com.metro.technical.domain.model.transaction.Transaction;
import com.metro.technical.infrastructure.messaging.TransactionPublisher;
import com.metro.technical.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionPublisher transactionPublisher;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processTransactionAsync() {
        Transaction transaction = new Transaction();

        doNothing().when(transactionRepository).save(any(Transaction.class));
        doNothing().when(transactionPublisher).publish(any(Transaction.class));

        CompletableFuture<Void> future = transactionService.processTransactionAsync(transaction);
        future.join();

        verify(transactionRepository, times(1)).save(transaction);
        verify(transactionPublisher, times(1)).publish(transaction);
    }
}

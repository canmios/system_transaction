package com.metro.technical.infrastructure.messaging;

import com.metro.technical.domain.model.transaction.Transaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TransactionProcessorTest {

    @InjectMocks
    TransactionProcessor transactionProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOnStart() {
        transactionProcessor.onStart(null);
    }

    @Test
    void testProcess() {
        Transaction transaction = new Transaction();
        Transaction result = transactionProcessor.process(transaction);
        assertEquals(transaction, result);
    }
}

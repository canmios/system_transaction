package com.metro.technical.infrastructure.messaging;

import com.metro.technical.domain.model.transaction.Transaction;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

@QuarkusTest
class TransactionPublisherTest {

    @InjectMocks
    TransactionPublisher transactionPublisher;

    @Mock
    @Channel("transactions-out")
    Emitter<Transaction> transactionEmitter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPublish() {
        Transaction transaction = new Transaction();
        transactionPublisher.publish(transaction);
        verify(transactionEmitter).send(transaction);
    }
}

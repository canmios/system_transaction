package com.metro.technical.infrastructure.repository;

import com.metro.technical.domain.model.transaction.Transaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class TransactionRepTest {

    @InjectMock
    TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByDateRange() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId("1234");
        transaction1.setTimestamp(Instant.now());

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId("1234");
        transaction2.setTimestamp(Instant.now());

        List<Transaction> expectedTransactions = List.of(transaction1, transaction2);

        when(transactionRepository.findByDateRange(start, end)).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = transactionRepository.findByDateRange(start, end);

        assertEquals(expectedTransactions.size(), actualTransactions.size());
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    void testSave() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1234");
        transaction.setTimestamp(Instant.now());

        doNothing().when(transactionRepository).save(transaction);

        transactionRepository.save(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }
}

package com.metro.technical.domain.service;

import com.metro.technical.domain.model.daily.DailySummary;
import com.metro.technical.domain.model.transaction.Transaction;
import com.metro.technical.infrastructure.repository.DailySummaryRepository;
import com.metro.technical.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class DailySummaryServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private DailySummaryRepository dailySummaryRepository;

    @InjectMocks
    private DailySummaryService dailySummaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateDailySummary() {
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(100.0);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(200.0);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByDateRange(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(transactions);

        dailySummaryService.generateDailySummary();

        verify(transactionRepository, times(1)).findByDateRange(any(LocalDateTime.class), any(LocalDateTime.class));
        verify(dailySummaryRepository, times(1)).save(any(DailySummary.class));
    }
}

package com.metro.technical.domain.service;

import com.metro.technical.domain.model.daily.DailySummary;
import com.metro.technical.domain.model.transaction.Transaction;
import com.metro.technical.domain.port.in.DailySummaryUseCase;
import com.metro.technical.infrastructure.repository.DailySummaryRepository;
import com.metro.technical.infrastructure.repository.TransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class DailySummaryService implements DailySummaryUseCase {

    TransactionRepository transactionRepository;

    DailySummaryRepository dailySummaryRepository;

    @Inject
    public DailySummaryService(TransactionRepository transactionRepository, DailySummaryRepository dailySummaryRepository) {
        this.transactionRepository = transactionRepository;
        this.dailySummaryRepository = dailySummaryRepository;
    }

    @Override
    public void generateDailySummary() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        List<Transaction> transactions = transactionRepository.findByDateRange(yesterday.atStartOfDay(), today.atStartOfDay());
        double totalAmount = transactions.stream().mapToDouble(Transaction::getAmount).sum();

        DailySummary summary = new DailySummary();
        summary.setDate(yesterday);
        summary.setTotalAmount(totalAmount);
        dailySummaryRepository.save(summary);
    }
}

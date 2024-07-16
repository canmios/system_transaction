package com.metro.technical.infrastructure.repository;

import com.metro.technical.domain.model.transaction.Transaction;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheMongoRepository<Transaction> {

    public List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return list("timestamp >= ?1 and timestamp < ?2", start, end);
    }

    public void save(Transaction transaction) {
        persist(transaction);
    }
}

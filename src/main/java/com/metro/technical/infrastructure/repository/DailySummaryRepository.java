package com.metro.technical.infrastructure.repository;

import com.metro.technical.domain.model.daily.DailySummary;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DailySummaryRepository implements PanacheMongoRepository<DailySummary> {

    public void save(DailySummary summary) {
        persist(summary);
    }
}

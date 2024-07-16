package com.metro.technical.application.port.in.scheduler;

import com.metro.technical.application.port.in.scheduler.doc.DailySummarySchedulerDoc;
import com.metro.technical.domain.port.in.DailySummaryUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class DailySummaryScheduler implements DailySummarySchedulerDoc {

    DailySummaryUseCase dailySummaryUseCase;

    @Inject
    public DailySummaryScheduler(DailySummaryUseCase dailySummaryUseCase) {
        this.dailySummaryUseCase = dailySummaryUseCase;
    }

    @Override
    @Scheduled(every="24h")
    public void generateDailySummary() {
        dailySummaryUseCase.generateDailySummary();
    }
}

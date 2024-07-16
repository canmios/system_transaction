package com.metro.technical.application.port.in.scheduler.doc;

import io.swagger.v3.oas.annotations.Operation;

public interface DailySummarySchedulerDoc {
    @Operation(
            summary = "Scheduler generate Daily summary")
    void generateDailySummary();
}

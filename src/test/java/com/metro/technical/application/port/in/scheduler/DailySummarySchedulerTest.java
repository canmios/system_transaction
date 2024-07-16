package com.metro.technical.application.port.in.scheduler;

import com.metro.technical.domain.port.in.DailySummaryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DailySummarySchedulerTest {

    @Mock
    private DailySummaryUseCase dailySummaryUseCase;

    @InjectMocks
    private DailySummaryScheduler dailySummaryScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateDailySummary() {
        dailySummaryScheduler.generateDailySummary();
        verify(dailySummaryUseCase, times(1)).generateDailySummary();
    }
}

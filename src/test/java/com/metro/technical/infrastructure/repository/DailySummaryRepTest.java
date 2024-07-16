package com.metro.technical.infrastructure.repository;

import com.metro.technical.domain.model.daily.DailySummary;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@QuarkusTest
class DailySummaryRepTest {

    @InjectMock
    DailySummaryRepository dailySummaryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        DailySummary dailySummary = new DailySummary();
        dailySummary.setTotalAmount(123);
        dailySummary.setDate(LocalDate.now());

        doNothing().when(dailySummaryRepository).save(dailySummary);

        dailySummaryRepository.save(dailySummary);

        verify(dailySummaryRepository, times(1)).save(dailySummary);
    }
}
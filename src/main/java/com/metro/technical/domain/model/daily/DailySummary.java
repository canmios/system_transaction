package com.metro.technical.domain.model.daily;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailySummary {
    private LocalDate date;
    private double totalAmount;
}

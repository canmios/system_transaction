package com.metro.technical.domain.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String transactionId;
    private Instant timestamp;
    private String deviceNumber;
    private String userId;
    private String geoPosition;
    private double amount;
}

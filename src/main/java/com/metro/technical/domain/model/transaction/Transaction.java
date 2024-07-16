package com.metro.technical.domain.model.transaction;

import lombok.Data;

import java.time.Instant;

@Data
public class Transaction {
    private String transactionId;
    private Instant timestamp;
    private String deviceNumber;
    private String userId;
    private String geoPosition;
    private double amount;
}

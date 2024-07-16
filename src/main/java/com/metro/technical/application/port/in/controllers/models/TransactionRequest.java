package com.metro.technical.application.port.in.controllers.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.Instant;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class TransactionRequest {
    private String transactionId;
    private Instant timestamp;
    private String deviceNumber;
    private String userId;
    private String geoPosition;
    private double amount;
}

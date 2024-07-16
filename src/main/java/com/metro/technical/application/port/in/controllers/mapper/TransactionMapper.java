package com.metro.technical.application.port.in.controllers.mapper;

import com.metro.technical.application.port.in.controllers.models.TransactionRequest;
import com.metro.technical.domain.model.transaction.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toTransaction(TransactionRequest transactionRequest);
}
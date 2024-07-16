package com.metro.technical.infrastructure.messaging;

import com.metro.technical.domain.model.transaction.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TransactionPublisher {

    private static final Logger LOGGER = Logger.getLogger(TransactionPublisher.class);

    @Inject
    @Channel("transactions-out")
    Emitter<Transaction> transactionEmitter;

    public void publish(Transaction transaction) {
        LOGGER.infof("Publishing transaction: %s", transaction);
        transactionEmitter.send(transaction);
    }
}

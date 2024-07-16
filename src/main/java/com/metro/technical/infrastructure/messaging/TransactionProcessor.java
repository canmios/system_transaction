package com.metro.technical.infrastructure.messaging;

import com.metro.technical.domain.model.transaction.Transaction;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TransactionProcessor {

    private static final Logger LOGGER = Logger.getLogger(TransactionProcessor.class);

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("TransactionProcessor started");
    }

    @Incoming("transactions")
    @Outgoing("transactions-out")
    public Transaction process(Transaction transaction) {
        LOGGER.infof("Processing transaction: %s", transaction);
        return transaction;
    }
}

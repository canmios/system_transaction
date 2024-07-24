package com.metro.technical.infrastructure.repository;

import com.metro.technical.domain.model.transaction.Transaction;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheMongoRepository<Transaction> {

    @Inject
    MongoClient mongoClient;

    private MongoCollection<Document> getCollection() {
        MongoDatabase database = mongoClient.getDatabase("transactions");
        return database.getCollection("transaction");
    }

    public List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end) {
        // Convert LocalDateTime to Date in UTC
        Date startDate = convertToDate(start);
        Date endDate = convertToDate(end);

        // Build query
        Document query = new Document("timestamp", new Document("$gte", startDate).append("$lt", endDate));
        List<Document> documents = getCollection().find(query).into(new ArrayList<>());

        List<Transaction> transactions = new ArrayList<>();
        for (Document doc : documents) {
            transactions.add(convertToTransaction(doc));
        }
        return transactions;
    }

    public void save(Transaction transaction) {
        Document document = convertToDocument(transaction);
        getCollection().insertOne(document);
    }

    public double calculateTotalAmount(LocalDateTime start, LocalDateTime end) {
        // Convert LocalDateTime to Date in UTC
        Date startDate = convertToDate(start);
        Date endDate = convertToDate(end);

        // Build aggregation pipeline
        Document match = new Document("$match", new Document("timestamp", new Document("$gte", startDate).append("$lt", endDate)));
        Document group = new Document("$group", new Document("_id", null).append("totalAmount", new Document("$sum", "$amount")));

        List<Document> pipeline = List.of(match, group);

        // Execute the aggregation
        List<Document> results = getCollection().aggregate(pipeline).into(new ArrayList<>());

        // Return the total amount or 0 if no results
        if (!results.isEmpty() && results.get(0).containsKey("totalAmount")) {
            return results.get(0).getDouble("totalAmount");
        }
        return 0.0;
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).toInstant());
    }

    private Transaction convertToTransaction(Document document) {
        return new Transaction(
                document.getString("transaction_id"),
                document.get("timestamp", Instant.class),
                document.getString("device_number"),
                document.getString("user_id"),
                document.getString("geo_position"),
                document.getDouble("amount")
        );
    }

    private Document convertToDocument(Transaction transaction) {
        return new Document("_id", new ObjectId())
                .append("transaction_id", transaction.getTransactionId())
                .append("timestamp", Date.from(transaction.getTimestamp()))
                .append("device_number", transaction.getDeviceNumber())
                .append("user_id", transaction.getUserId())
                .append("geo_position", transaction.getGeoPosition())
                .append("amount", transaction.getAmount());
    }
}

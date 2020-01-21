package com.abc;

import java.time.Instant;

public class Transaction {
    private final double amount;

    private Instant transactionInstant;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionInstant = InstantProvider.getInstance().now();
    }

    public Instant getTransactionInstant() {
        return transactionInstant;
    }

    public double getAmount() {
        return amount;
    }
}

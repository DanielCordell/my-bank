package com.abc;

import java.time.Instant;

public class Transaction {
    public final double amount;

    private Instant transactionInstant;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionInstant = InstantProvider.getInstance().now();
    }

}

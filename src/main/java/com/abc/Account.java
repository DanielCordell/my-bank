package com.abc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Account {

    public enum ACCOUNT_TYPE {
        CHECKING("Checking Account"),
        SAVINGS("Savings Account"),
        MAXI_SAVINGS("Maxi Savings Account");

        private final String enumName;
        ACCOUNT_TYPE(String enumName) { this.enumName = enumName; }
        String getPrettyName() { return enumName; }
    }

    private final ACCOUNT_TYPE accountType;
    public List<Transaction> transactions;

    public Account(ACCOUNT_TYPE accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public boolean anyWithdrawlsInPastDays(int numberOfDays) {
        Instant daysAgo = InstantProvider.getInstance().daysAgo(numberOfDays);
        return transactions
                .stream()
                .anyMatch(it -> it.getTransactionInstant().isAfter(daysAgo));
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
                if (anyWithdrawlsInPastDays(10))
                    return amount * 0.001;
                return 0.05;
            case CHECKING:
                return amount * 0.001;
            default:
                throw new IllegalArgumentException("Either a bad enum value, or savings account has not been given an interest calculation!");
        }
    }

    public double sumTransactions() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public ACCOUNT_TYPE getAccountType() {
        return accountType;
    }

}

package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public enum ACCOUNT_TYPE {
        CHECKING("Checking Account"),
        SAVINGS("Savings Account"),
        SUPER_SAVINGS("Super Savings Account"),
        MAXI_SAVINGS("Maxi Savings Account");

        private final String enumName;
        ACCOUNT_TYPE(String enumName) { this.enumName = enumName; }
        String getPrettyName() { return enumName; }
    }

    private final ACCOUNT_TYPE accountType;
    public List<Transaction> transactions;

    public Account(ACCOUNT_TYPE accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
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

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
           case SUPER_SAVINGS:
                if (amount <= 4000)
                    return 20;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
        return transactions.stream().mapToDouble(it-> it.amount).sum();
    }

    public ACCOUNT_TYPE getAccountType() {
        return accountType;
    }

}

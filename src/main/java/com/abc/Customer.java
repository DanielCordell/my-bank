package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static com.abc.util.Math.toDollars;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        return accounts.stream().mapToDouble(Account::interestEarned).sum();
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for " + name + "\n");
        double total = 0.0;
        for (Account a : accounts) {
            statement.append("\n").append(statementForAccount(a)).append("\n");
            total += a.currentBalance();
        }
        statement.append("\nTotal In All Accounts ").append(toDollars(total));
        return statement.toString();
    }

    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder();

        s.append(a.getAccountType().getPrettyName());
        s.append("\n");

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            double amount = t.getAmount();
            s.append("  ").append(amount < 0 ? "withdrawal" : "deposit").append(" ").append(toDollars(amount)).append("\n");
            total += amount;
        }
        s.append("Total ").append(toDollars(total));
        return s.toString();
    }

}

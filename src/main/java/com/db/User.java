package com.db;

public class User {

    private String userName;
    private double balance;
    private double creditDebitAmount;
    private String transactionType;
    private double transactionId;
    private String accountNumber;
    private String date;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(double transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCreditDebitAmount() {
        return creditDebitAmount;
    }

    public void setCreditDebitAmount(double creditDebitAmount) {
        this.creditDebitAmount = creditDebitAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

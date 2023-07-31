package org.example.lecture08.employee;

import java.util.Date;
import java.util.Random;

public class BankAccount {
    private String ownerName;
    private double accountBalance = 0;
    private String accountNumber;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        ownerName = ownerName.replace(" ", "");
        setAccountNumber(ownerName);

    }

    private void setAccountNumber(String name){
        Date date = new Date();
        Random random = new Random();
        this.accountNumber = name + date.getTime() + random.nextInt();

    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

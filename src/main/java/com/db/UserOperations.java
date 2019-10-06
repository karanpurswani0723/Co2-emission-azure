package com.db;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateUser {

    public User createUser() {
        User user = new User();
        user.setAccountNumber("123");
        user.setBalance("12345");
        //user.setCreditAmount("100");
        //user.setDebitAmount("50");
        user.setTransactionId(Math.random());
        user.setUserName("karan");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        user.setDate(formatter.format(date));

        return user;
    }

    public   DBObject createDBObject(User user){


        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        //this is the primary key--transactionid

        docBuilder.append("_id",String.valueOf(user.getTransactionId()).substring(2));
        docBuilder.append("accountNumber",user.getAccountNumber());
        docBuilder.append("balance",user.getBalance());
        docBuilder.append("creditAmount",user.getCreditAmount());
        docBuilder.append("debitAmount",user.getDebitAmount());
        docBuilder.append("userName",user.getUserName());
        docBuilder.append("date",user.getDate());

        return docBuilder.get();


    }
}

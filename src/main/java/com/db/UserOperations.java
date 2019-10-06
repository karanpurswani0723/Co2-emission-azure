package com.db;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserOperations {

    public User createUser() {
        User user = new User();
        user.setAccountNumber("123");
        user.setBalance(Double.valueOf("0"));
        user.setTransactionId(Math.random());
        user.setUserName("karan");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
        docBuilder.append("creditDebitAmount",user.getCreditDebitAmount());
        docBuilder.append("transactionType",user.getTransactionType());
        docBuilder.append("userName",user.getUserName());
        docBuilder.append("date",user.getDate());

        return docBuilder.get();


    }

    public User updateUser(DBObject latestDBObject,double creditDebitamount){


        User user = new User();
        user.setAccountNumber(latestDBObject.get("accountNumber").toString());
        user.setBalance(Double.valueOf(latestDBObject.get("balance").toString())+creditDebitamount);
        user.setCreditDebitAmount(Math.abs(creditDebitamount));
        user.setTransactionId(Math.random());
        user.setTransactionType(creditDebitamount>=0? "credit" : "debit" );
        user.setUserName(latestDBObject.get("userName").toString());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        user.setDate(formatter.format(date));

        return user;

    }
}

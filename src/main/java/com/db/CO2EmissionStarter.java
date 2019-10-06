package com.db;

import com.mongodb.*;

public class CO2EmissionStarter {

    public static void main(String[] args) {

        //todo: pending validations to check if amount is not less than 0
        
        String accoutNumber=args[0];
        double creditDebitAmout = Double.valueOf(args[1]);

        //creating mongo client connection
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //creating database if not present
        DB database = mongoClient.getDB("C02_Emission_DB");

        //creating collection if not present
        DBCollection collection = database.getCollection("user_collection");


        DatabaseOperations databaseOperations = new DatabaseOperations();
        UserOperations userOperations = new UserOperations();

        //check if account is already present
        DBCursor accountData = databaseOperations.readFromDatabase(collection, "accountNumber", accoutNumber);

        boolean isAccountPresent = accountData.count() > 0;

        //creating user
        if (!isAccountPresent) {

            User createdUser = userOperations.createUser();
            DBObject dbObject = userOperations.createDBObject(createdUser);

            //inserting data of the user
            databaseOperations.writeToDatabase(collection, dbObject);
        } else {
            System.out.println("user already present");
            DBObject latestdbObject = null;
            while (accountData.hasNext()){
                latestdbObject=accountData.next();
            }

            User updatedUser=userOperations.updateUser(latestdbObject, creditDebitAmout);
            DBObject dbObject = userOperations.createDBObject(updatedUser);
            databaseOperations.writeToDatabase(collection, dbObject);

        }

        System.out.println(accountData.count());

    }
}

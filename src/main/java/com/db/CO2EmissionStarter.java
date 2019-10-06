package com.db;

import com.mongodb.*;

public class CO2EmissionStarter {

    public static void main(String[] args) {

        //todo: pending validations to check if balance is  less than 0
        //todo: as of now running on local host--needs to be deployed to azure with actual server details
        //todo: as of now few attributes are hardcoded--make configurable
        //todo: modularizing the code


        if (args.length!=4){
            System.out.println("incorrect number of args..");
            return;
        }

        //this is for new users
        String newUserAccountNumber=args[0];
        String newUserName=args[1];

        //this is for existing users
        String accoutNumber=args[2];
        double creditDebitAmount = Double.valueOf(args[3]);

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
            System.out.println("creating new user..");
            User createdUser = userOperations.createUser(newUserAccountNumber,newUserName);
            DBObject dbObject = userOperations.createDBObject(createdUser);

            //inserting data of the user
            databaseOperations.writeToDatabase(collection, dbObject);
        } else {
            System.out.println("user already present..");
            DBObject latestdbObject = null;
            while (accountData.hasNext()){
                latestdbObject=accountData.next();
            }

            User updatedUser=userOperations.updateUser(latestdbObject, creditDebitAmount);
            DBObject dbObject = userOperations.createDBObject(updatedUser);
            databaseOperations.writeToDatabase(collection, dbObject);

        }

        System.out.println(accountData.count());

    }
}

package com.db;

import com.mongodb.*;

public class CO2EmissionStarter {

    public static void main(String[] args) {

        //creating user
        CreateUser createUser=new CreateUser();
        User user=createUser.createUser();
        DBObject dbObject=createUser.createDBObject(user);

        //creating mongo client connection
        MongoClient mongoClient=new MongoClient("localhost",27017);

        //creating database if not present
        DB database = mongoClient.getDB("C02_Emission_DB");

        //creating collection if not present
        DBCollection collection=database.getCollection("user_collection");

        DatabaseOperations databaseOperations=new DatabaseOperations();

        //inserting data of the user
        databaseOperations.writeToDatabase(collection,dbObject);

        //fetching the user data
        databaseOperations.readFromDatabase(collection);

    }
}

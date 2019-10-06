package com.db;

import com.mongodb.*;

public class DatabaseOperations {


    public void writeToDatabase(DBCollection collection, DBObject dbObject){
        collection.insert(dbObject);
    }

    public void readFromDatabase(DBCollection collection){
        DBObject query= BasicDBObjectBuilder.start().add("accountNumber","123").get();
        DBCursor cursor=collection.find(query);
        System.out.println(cursor.count());
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
}

package com.db;

import com.mongodb.*;

public class DatabaseOperations {


    public void writeToDatabase(DBCollection collection, DBObject dbObject) {
        collection.insert(dbObject);
    }

    public DBCursor readFromDatabase(DBCollection collection, String key, String value) {
        DBObject query = BasicDBObjectBuilder.start().add(key, value).get();
        DBCursor cursor = collection.find(query);
        return cursor;
    }


}

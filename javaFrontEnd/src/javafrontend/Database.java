/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafrontend;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.net.UnknownHostException;
 
/**
 *
 * @author brandonfoss
 */
public class Database {
    private static MongoClient mongoClient;

    public Database() throws UnknownHostException {
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);
    }
     public static void GetName(String Name) throws IOException {

        DB BEYOU_DB;
        BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth;
         System.out.println("password "+FXMLDocumentController.Password().toCharArray());
        auth = BEYOU_DB.authenticate("beyoutiful", FXMLDocumentController.Password().toCharArray());
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", Name);
        DBCursor cursor = table.find(searchQuery);

        String ClientName = searchQuery.getString(Name);
        //System.out.println("Looking for: "+Name);// these are to see the name your searching for is being entered
        //if (ClientName == null){
        FXMLDocumentController.errorMessage.setText(ClientName);
        //System.out.println("Cant locate that name.");
        // }
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
            // System.out.println("name "+ClientName);

        }
    }

   
}

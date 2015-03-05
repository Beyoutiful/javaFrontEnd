package javafrontend;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBDecoder;
import com.mongodb.DBEncoder;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import javafx.scene.control.ChoiceBox;


/**
 * @author sean
 * Implements Items collection from database
 * See end of Item.java for original methods from DocumentController I've
 * replaced --Sean, 26 Feb 2015
 */

public class Item 
{
   /* private DBCollection table;
    private BasicDBObject searchQuery;
    
    public Item()
    {
<<<<<<< HEAD
        this.table = BEYOU_DB.getCollection("items");
=======
        this.table = FXMLDocumentController.BEYOU_DB.getCollection("items");
>>>>>>> 27f2ba2fa4262804c28e1576a75b2d4213a9140f
        this.searchQuery = new BasicDBObject();
        
    }
    
    public void getItems()
    {
        DBCursor cursor = table.find(searchQuery);
        
        while (cursor.hasNext())
        {
            DBObject item = cursor.next();
            
            System.out.println(item.get("_id"));
<<<<<<< HEAD
            choiceBox.getItems().add(item.get("name"));
=======
            FXMLDocumentController.choiceBox.getItems().add(item.get("name"));
>>>>>>> 27f2ba2fa4262804c28e1576a75b2d4213a9140f
        }
    }
    
    public void populateItem()
    {
        DBCursor cursor = table.find(searchQuery);
        
        while (cursor.hasNext()) 
        {
            DBObject item = cursor.next();
            
<<<<<<< HEAD
            itemID.setText((String) item.get("_id"));
            itemName.setText((String) item.get("name"));
            itemPrice.setText((String) item.get("price"));
            itemDescription.setText((String) item.get("description"));
=======
            FXMLDocumentController.itemID.setText((String) item.get("_id"));
            FXMLDocumentController.itemName.setText((String) item.get("name"));
            FXMLDocumentController.itemPrice.setText((String) item.get("price"));
            FXMLDocumentController.itemDescription.setText((String) item.get("description"));
        }
>>>>>>> 27f2ba2fa4262804c28e1576a75b2d4213a9140f
    }
}

    //***Below are the methods I've attempted to replace with the class Item -SEAN***
    /*public void getItems() 
    {
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) 
        {
            DBObject item = cursor.next();
            System.out.println(item.get("_id"));
            choiceBox.getItems().add(item.get("name"));
        }
    }

    public void populateItem() 
    {
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) {
            DBObject item = cursor.next();
            itemID.setText((String) item.get("_id"));
            itemName.setText((String) item.get("name"));
            itemPrice.setText((String) item.get("price"));
            itemDescription.setText((String) item.get("description"));
        }*/
    }
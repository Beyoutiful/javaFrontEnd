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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.net.UnknownHostException;
import static javafrontend.FXMLDocumentController.Password;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import org.bson.types.ObjectId;

/**
 *
 * @author brandonfoss
 */
public class Clients {
    
    FXMLDocumentController controller;


   private final MongoClient mongoClient;
    
    public Clients(FXMLDocumentController _controller) throws UnknownHostException {
        
        controller=_controller;
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);
    }
    /*
    *   Clients
    */
    protected Label clientIDLabel() { return controller.clientIDLabel; }
    
    protected TextField clientNameField() { return controller.clientNameField; }
    
    protected TextField clientNumberField() { return controller.clientNumberField; }
    
    protected TextField clientAddressField() { return controller.clientAddressField; }
    
    protected TextField clientEmailField() { return controller.clientEmailField; }
    
    protected TextField streetNumberField() { return controller.streetNumberField; }

    protected TextField cityField() { return controller.cityField; }
  
    protected TextField stateField() { return controller.stateField; }
  
    protected TextField zipcodeField() { return controller.zipcodeField; }
  
    protected TextField searchName() { return controller.searchName; }
    
    protected Button createNewClient() { return controller.createNewClient; }
     

    
     
    public void searchClient(String Name) throws IOException 
    {        
        String password = Password();
            DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
             boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", Name);
        DBCursor cursor = table.find(searchQuery);

        String clientName = searchQuery.getString(Name);
        
        if (cursor.hasNext()==true){
            while (cursor.hasNext()) {
            DBObject client = cursor.next();
            
            Object ID = client.get("_id");
            clientIDLabel().setText(ID.toString());
            clientNameField().setText(client.get("name").toString());
            clientEmailField().setText(client.get("email").toString());
            clientNumberField().setText(client.get("phone").toString());
            streetNumberField().setText(client.get("address").toString());
            cityField().setText(client.get("city").toString());
            stateField().setText(client.get("state").toString());
            zipcodeField().setText(client.get("zip").toString());
            createNewClient().setVisible(false);
            }
        }else{
         clientNameField().setText(searchName().getText());
         //clientNameField().setText("");
         clientEmailField().setText("");
         clientNumberField().setText("");
         streetNumberField().setText("");
         cityField().setText("");
         stateField().setText("");
         zipcodeField().setText("");
         createNewClient().setVisible(true);
         
    }
    }
  // This function is working as it should
    public void updateClient() throws IOException {
        String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("clients");
        
        BasicDBObject query = new BasicDBObject();
        System.out.println(clientIDLabel().getText());
        query.put("_id", new ObjectId(clientIDLabel().getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", clientNameField().getText());
        newDocument.put("phone", clientNumberField().getText());
        newDocument.put("email", clientEmailField().getText());
        newDocument.put("address", streetNumberField().getText());
        newDocument.put("city", cityField().getText());
        newDocument.put("state", stateField().getText());
        newDocument.put("zip", zipcodeField().getText());
        
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        table.update(query, updateObj);
        clientNameField().setText("");
        clientEmailField().setText("");
        clientNumberField().setText("");
        streetNumberField().setText("");
        cityField().setText("");
        stateField().setText("");
        zipcodeField().setText("");
    }

    //Adds new client (does not update)
    public void newClient(String Name){
        
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject document = new BasicDBObject();
        document.put("name", clientNameField().getText());
        document.put("phone", clientNumberField().getText());
        document.put("email", clientEmailField().getText());
        document.put("address", streetNumberField().getText());
        document.put("city", cityField().getText());
        document.put("state", stateField().getText());
        document.put("zip", zipcodeField().getText());
        document.put("__v", 0);
        table.insert(document);
        clientNameField().setText("");
        clientEmailField().setText("");
        clientNumberField().setText("");
        streetNumberField().setText("");
        cityField().setText("");
        stateField().setText("");
        zipcodeField().setText("");
    }


}

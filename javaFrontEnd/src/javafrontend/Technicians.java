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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.bson.types.ObjectId;

/**
 *
 * @author brandonfoss
 */

public class Technicians {
   

     private final MongoClient mongoClient;
    
    public Technicians() throws UnknownHostException {
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);
    }
        public void deleteProfile(Label techID) throws IOException{
          String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("profiles");
        String ID = techID.getText();
        BasicDBObject searchQuery = new BasicDBObject();
       Object proID = null;
        searchQuery.put("technician", new ObjectId(ID)); 
        DBCursor cursor = table.find(searchQuery);
        System.out.println(cursor.hasNext());
        if (cursor.hasNext()==true){
            while (cursor.hasNext()) {
            DBObject profiles = cursor.next();
          
            proID = profiles.get("_id");
               
       // String profileID= cursor.toString();
        
            }
            
        }
       BasicDBObject deleteQuery = new BasicDBObject();
       deleteQuery.put("_id", new ObjectId(proID.toString()));
       table.remove(deleteQuery);
    }
    public void createProfile(Label techID) throws IOException{
         String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("profiles");
        String ID = techID.getText();
        BasicDBObject document = new BasicDBObject();
        
        document.put("technician", new ObjectId(ID));
       
        document.put("__v", 0);
        table.insert(document);
    }

    /**
     *
     * @param techID
     * @param techName
     * @param techPhone
     * @param techEmail
     * @param techStreet
     * @param techCity
     * @param techState
     * @param techZipcode
     * @param techTitle
     * @param techImageURLTextArea
     */
    public void searchTech(Label techID,TextField techName,TextField techPhone,TextField techEmail,TextField techStreet,TextField techCity,TextField techState,TextField techZipcode, TextField techTitle,TextField techImageURL,TextArea techDescription,ImageView techImageBox,Button createProfileButton,Button deleteProfileButton) throws IOException{
       {   
          String name = new String();
          name = techName.getText();
        String password = Password();
            DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
             boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("technicians");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);
        DBCursor cursor = table.find(searchQuery);

        String technicianName = searchQuery.getString(name);
        
        if (cursor.hasNext()==true){
            while (cursor.hasNext()) {
            DBObject technicians = cursor.next();
          
            Object ID = technicians.get("_id");
            techID.setText(ID.toString());
            techName.setText(technicians.get("name").toString());
            techEmail.setText(technicians.get("email").toString());
            techPhone.setText(technicians.get("phone").toString());
            techStreet.setText(technicians.get("address").toString());
            techCity.setText(technicians.get("city").toString());
            techState.setText(technicians.get("state").toString());
            techZipcode.setText(technicians.get("zip").toString());
            techTitle.setText(technicians.get("title").toString());
            techImageURL.setText(technicians.get("image").toString());
            techDescription.setText(technicians.get("description").toString());
            
                
               Image image = new Image("http://beyoutifulstudio.herokuapp.com/"+technicians.get("image"));
            techImageBox.setImage(image);
                
            }
        
         
    }
    }
    }
    public void createTech() throws IOException{
        
    }
    public void deleteTech(){
       
    }
}


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
   
    FXMLDocumentController controller;
     private final MongoClient mongoClient;
     
    protected Label techID(){ return controller.techID; }
    
    protected Tab techTab() { return controller.techTab; }
    
    protected TextField techName(){ return controller.techName; }
   
    protected TextField techPhone() { return controller.techPhone; }
   
    protected TextField techEmail() { return controller.techEmail; }
   
    protected TextField techStreet() { return controller.techStreet; }
    
    protected TextField techCity() { return controller.techCity; }
   
    protected TextField techState() { return controller.techState; }
    
    protected TextField techZipcode() { return controller.techZipcode; }
    
    protected TextField techTitle() { return controller.techTitle; }
    
    protected TextField techImageURL() { return controller.techImageURL; }
    
    protected TextArea techDescription() { return controller.techDescription; }
    
    protected ImageView techImageBox() { return controller.techImageBox; }
    
    protected Button createProfileButton() { return controller.createProfileButton; }
   
    protected Button deleteProfileButton() { return controller.deleteProfileButton; }

    
    public Technicians(FXMLDocumentController _controller) throws UnknownHostException {
        
        controller= _controller;
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
    public void searchTech() throws IOException{
       {   
          String name = new String();
          name = techName().getText();
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
            techID().setText(ID.toString());
            techName().setText(technicians.get("name").toString());
            techEmail().setText(technicians.get("email").toString());
            techPhone().setText(technicians.get("phone").toString());
            techStreet().setText(technicians.get("address").toString());
            techCity().setText(technicians.get("city").toString());
            techState().setText(technicians.get("state").toString());
            techZipcode().setText(technicians.get("zip").toString());
            techTitle().setText(technicians.get("title").toString());
            techImageURL().setText(technicians.get("image").toString());
            techDescription().setText(technicians.get("description").toString());
            
                
               Image image = new Image("http://beyoutifulstudio.herokuapp.com/"+technicians.get("image"));
            techImageBox().setImage(image);
                
            }        
    }
    }
    }  
     public void updateTech() throws IOException {
        String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("technicians");
        
        BasicDBObject query = new BasicDBObject();
        
        query.put("_id", new ObjectId(techID().getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", techName().getText());
        newDocument.put("phone", techPhone().getText());
        newDocument.put("email", techEmail().getText());
        newDocument.put("address", techStreet().getText());
        newDocument.put("city", techCity().getText());
        newDocument.put("state", techState().getText());
        newDocument.put("zip", techZipcode().getText());
        newDocument.put("title", techTitle().getText());
        newDocument.put("image", techImageURL().getText());
        newDocument.put("description", techDescription().getText());
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        table.update(query, updateObj);
        techName().setText("");
        techPhone().setText("");
        techEmail().setText("");
        techStreet().setText("");
        techCity().setText("");
        techState().setText("");
        techZipcode().setText("");
        techTitle().setText("");
        techImageURL().setText("");
        techDescription().setText("");
        Image image = null;
            techImageBox().setImage(image);
    }
     
     

    //Adds new tech (does not update)
    public void newTech() 
    {
        
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject document = new BasicDBObject();
        document.put("name", techName().getText());
        document.put("phone", techPhone().getText());
        document.put("email", techEmail().getText());
        document.put("address", techStreet().getText());
        document.put("city", techCity().getText());
        document.put("state", techState().getText());
        document.put("zip", techZipcode().getText());
        document.put("title", techTitle().getText());
        document.put("image", techImageURL().getText());
        document.put("description", techDescription().getText());
        document.put("__v", 0);
        table.insert(document);
         techName().setText("");
        techPhone().setText("");
        techEmail().setText("");
        techStreet().setText("");
        techCity().setText("");
        techState().setText("");
        techZipcode().setText("");
        techTitle().setText("");
        techImageURL().setText("");
        techDescription().setText("");
    }
}

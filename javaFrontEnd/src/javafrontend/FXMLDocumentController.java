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
import com.mongodb.DBDecoder;
import com.mongodb.DBEncoder;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.Cursor.cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bson.BSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author FossRobotics
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label passLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label clientName;
    @FXML
    private Label clientNumber;
    @FXML
    private Label clientAddress;
    @FXML
    private Label clientEmail;
    @FXML
    private Button submit;
    @FXML
    private Button button;
    @FXML
    private Button searchButton;
    @FXML
    private Button newClientButton;
    @FXML
    private Tab admin;
    @FXML
    private Tab login;
    @FXML
    private Tab schedule;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField clientNameField;
    @FXML
    private TextField clientNumberField;
    @FXML
    private TextArea clientAddressField;
    @FXML
    private TextField clientEmailField;
    @FXML
    private Label errorMessage;
    @FXML
    private TableView mainTable;
    @FXML
    private MenuButton menu;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Label itemLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextField itemID;
    @FXML
    private TextField itemName;
     @FXML
    private TextField itemPrice;
    @FXML
    private TextArea itemDescription;
   
    private final MongoClient mongoClient;

    public FXMLDocumentController() throws UnknownHostException {
        this.mongoClient = new MongoClient("ds035750.mongolab.com",35750);
       
    }

    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, MalformedURLException, ParseException  {
     login();
    }
     @FXML
    private void handleSearchButtonAction(ActionEvent event)  {
    
        getName(clientNameField.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    public void database(){
       
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
        //save example
             System.out.println("auth: "+ auth);
        DBCollection table = db.getCollection("services");
        BasicDBObject document = new BasicDBObject();
        document.put("name","Brandon");
        document.put("price",65);
        document.put("description", "new toes");
        document.put("__v",0);
        table.insert(document);

      

    
    } 
    public void getServices(){
   
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
        //save example
             System.out.println("auth: "+ auth);
        DBCollection table = db.getCollection("services");


             BasicDBObject searchQuery = new BasicDBObject();
             searchQuery.put("name", "Manicure");

             DBCursor cursor = table.find(searchQuery);

             while (cursor.hasNext()) {
                     System.out.println(cursor.next());
             }
         }
    public void getItems(){
   
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
        //save example
             System.out.println("auth: "+ auth);
        DBCollection table = db.getCollection("items");


             BasicDBObject searchQuery = new BasicDBObject();
             

             DBCursor cursor = table.find(searchQuery);
             
             while (cursor.hasNext()) {
                 DBObject item = cursor.next();
                 System.out.println(item.get("_id"));
                 choiceBox.getItems().add(item.get("name"));
             }
         }
    public void populateItem(){
         DB db = mongoClient.getDB("heroku_app33977271");
         boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
             DBCollection table = db.getCollection("items");
             BasicDBObject searchQuery = new BasicDBObject();     
             DBCursor cursor = table.find(searchQuery);
             while (cursor.hasNext()) {
                 DBObject item = cursor.next();
                itemID.setText((String)item.get("_id"));
                itemName.setText((String)item.get("name"));
                itemPrice.setText((String)item.get("price"));
                itemDescription.setText((String)item.get("description"));
             }
    }
    public void updateCollection(){
       
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
   
        System.out.println("auth: "+ auth);
        DBCollection table = db.getCollection("services");
 
	BasicDBObject query = new BasicDBObject();
	query.put("name", "Manicure-updated");
 
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "Manicure");
 
	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);
 
	table.update(query, updateObj);
    }
    public void getName(String Name){
        
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
        DBCollection table = db.getCollection("clients");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name",Name);
        DBCursor cursor = table.find(searchQuery);
       
        String ClientName=searchQuery.getString(Name);
        //System.out.println("Looking for: "+Name);// these are to see the name your searching for is being entered
         //if (ClientName == null){
             errorMessage.setText(ClientName);
            //System.out.println("Cant locate that name.");
       // }
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
               // System.out.println("name "+ClientName);
               
        }
    }
    public void enterClient()
    {
      
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());

        DBCollection table = db.getCollection("clients");
        BasicDBObject document = new BasicDBObject();
        document.put("name",clientNameField.getText());
        document.put("phone",clientNumberField.getText());
        document.put("email", clientEmailField.getText());
        document.put("address", clientAddressField.getText());
        document.put("__v",0);
        table.insert(document);
        clientNameField.setText("");
        clientNumberField.setText("");
        clientEmailField.setText("");
        clientAddressField.setText("");
    }
    private void login() throws MalformedURLException, IOException, ParseException{
         String url = "http://beyoutifulstudio.herokuapp.com/api/technicians?email="
                 +userField.getText()+"&password="+passField.getText();
       URL obj = new URL(url);
       HttpURLConnection con = (HttpURLConnection)obj.openConnection();
       con.setRequestProperty("Authorization", "Basic UDAwayFvb0ZmZjo=");
       con.setRequestProperty("Content-Type", "application/json");
       con.setRequestProperty("Accept", "application/json");
       int responceCode= con.getResponseCode();
        System.out.println("Responce code "+responceCode);
        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream())); 
        String inputLine; StringBuffer response = new StringBuffer();   
        while ((inputLine = in.readLine()) != null) { 
            response.append(inputLine); 
        } 
        in.close();   
    //print result 
        System.out.println(response.toString());
        JSONParser parser=new JSONParser();
        Object object = parser.parse(response.toString());
        System.out.println("object "+ object);
    }
}

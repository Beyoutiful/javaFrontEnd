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
 * SEAN TODO: Move the mongoclient stuff into a Class global.
 * Doesn't need to reconnect in each method.DONE 26 FEB
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    protected static Label passLabel;
    @FXML
    protected static Label userLabel;
    @FXML
    protected static Label clientName;
    @FXML
    protected static Label clientNumber;
    @FXML
    protected static Label clientAddress;
    @FXML
    protected static Label clientEmail;
    @FXML
    protected static Button submitButton;
    @FXML
    protected static Button saveButton;
    @FXML
    protected static Button searchButton;
    @FXML
    protected static Button button;
    @FXML
    protected static Tab admin;
    @FXML
    protected static Tab login;
    @FXML
    protected static Tab schedule;
    @FXML
    protected static Label loginLabel;
    @FXML
    protected static DatePicker datePicker;
    @FXML
    protected static TextField userField;
    @FXML
    protected static PasswordField passField;
    @FXML
    protected static TextField clientNameField;
    @FXML
    protected static TextField clientNumberField;
    @FXML
    protected static TextArea clientAddressField;
    @FXML
    protected static TextField clientEmailField;
    @FXML
    protected static Label errorMessage;
    @FXML
    protected static TableView mainTable;
    @FXML
    protected static MenuButton menu;
   
    @FXML
    protected static ChoiceBox choiceBox;
    @FXML
    protected static Label itemLabel;
    @FXML
    protected static Label priceLabel;
    @FXML
    protected static Label descriptionLabel;
    @FXML
    protected static TextField itemID;
    @FXML
    protected static TextField itemName;
    @FXML
    protected static TextField itemPrice;
    @FXML
    protected static TextArea itemDescription;
    @FXML
    protected static MongoClient mongoClient;

    public FXMLDocumentController() throws UnknownHostException
    {
        AUTH = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());
        BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);
    }

    protected static  DB BEYOU_DB;
    private final boolean AUTH;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, MalformedURLException, ParseException {
        login();
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {

        getName(clientNameField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void database() 
    {
        //Allowing insert to database...
        DBCollection table = BEYOU_DB.getCollection("services");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Brandon");
        document.put("price", 65);
        document.put("description", "new toes");
        document.put("__v", 0);
        table.insert(document);
    }

    public void getServices() 
    {
        DBCollection table = BEYOU_DB.getCollection("services");


        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Manicure");

        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    
    
    //Item testItem = new Item();
    //testItem.getItems();
    //testItem.populateItem();
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
        }
    }*/

    //
    public void updateCollection() 
    {
        DBCollection table = BEYOU_DB.getCollection("services");

        BasicDBObject query = new BasicDBObject();
        query.put("name", "Manicure-updated");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "Manicure");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);



        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Manicure");

        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    
    
   
  
    public void getName(String Name) 
    {        
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", Name);
        DBCursor cursor = table.find(searchQuery);

        String ClientName = searchQuery.getString(Name);
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

    //Adds new client (does not update)
    public void enterClient() 
    {
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject document = new BasicDBObject();
        document.put("name", clientNameField.getText());
        document.put("phone", clientNumberField.getText());
        document.put("email", clientEmailField.getText());
        document.put("address", clientAddressField.getText());
        document.put("__v", 0);
        table.insert(document);
        clientNameField.setText("");
        clientNumberField.setText("");
        clientEmailField.setText("");
        clientAddressField.setText("");
    }

    private void login() throws MalformedURLException, IOException, ParseException {
        String url = "http://beyoutifulstudio.herokuapp.com/api/technicians?email="
                + userField.getText() + "&password=" + passField.getText();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", "Basic UDAwayFvb0ZmZjo=");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("Responce code " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result 
        System.out.println(response.toString());
        JSONParser parser = new JSONParser();
        Object object = parser.parse(response.toString());
        System.out.println("object " + object);
        
        if(responseCode == 200){
           // admin.setDisable(false);
            schedule.setDisable(false);
            loginLabel.setText("Welcome "+ userField.getText());
            System.out.println("got here");
        }
        if (responseCode == 204){
            loginLabel.setText("Wrong USER/PASSWORD.");
        }   
    }
}

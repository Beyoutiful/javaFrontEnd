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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label loginLabel;
    @FXML
    private Button submit;
    @FXML
    protected static Button saveButton;
    @FXML
    protected static Button searchButton;
    @FXML
    protected static Button button;
    @FXML
    private Tab client;
    protected static Tab admin;
    @FXML
    protected static Tab login;
    @FXML
    protected static Tab schedule;
    @FXML
    private Tab Items;
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
    private TextField searchName;
    @FXML
    private TextArea searchResults;
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
    private TextField itemName;
    @FXML
    private TextField itemPrice;
    @FXML
    private TextArea itemDescription;

    private final MongoClient mongoClient;

    public FXMLDocumentController() throws UnknownHostException {
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, MalformedURLException, ParseException {
        
        login();
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {
  //  updateCollection();
     getName(searchName.getText());
        //getServices();


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }


    public void database() throws IOException {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", Password().toCharArray());
        DBCollection table = BEYOU_DB.getCollection("services");
        BasicDBObject document = new BasicDBObject();
        
        document.put("name", "Brandon");
    }

    public void getServices() throws IOException {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", Password().toCharArray());
        //save example
    
        DBCollection table = BEYOU_DB.getCollection("services");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Manicures");

        DBCursor cursor = table.find(searchQuery);
        //System.out.println(cursor);
        
        while (cursor.hasNext()) {
            System.out.println(cursor.next().get("items"));
        }
      
    }

    public void getItems() throws IOException {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", Password().toCharArray());
        //save example
        DBCollection table = BEYOU_DB.getCollection("items");


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
    public void populateItem() throws IOException {
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", Password().toCharArray());
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
    }
    protected static String Password() throws FileNotFoundException, IOException{
        String password="";
        try {
			File file = new File("/Users/brandonfoss/NetBeansProjects/javaFrontEnd/javaFrontEnd/src/javafrontend/BEYOU_Config.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
                        password=stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return password;
      
    }
    protected static String apiPassword() throws FileNotFoundException, IOException{
        String password="";
        try {
			File file = new File("/Users/brandonfoss/NetBeansProjects/javaFrontEnd/javaFrontEnd/src/javafrontend/API_Config.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
                        password=stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return password;
      
    }
    public void updateCollection() throws IOException {
        String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        System.out.println("auth: " + auth);
        DBCollection table = BEYOU_DB.getCollection("services");

        BasicDBObject query = new BasicDBObject();
        query.put("name", "Manicure-updated");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "Manicure");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        table.update(query, updateObj);
    }

    public void getName(String Name) throws IOException 
    {        
        String password = Password();
            DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
             boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", Name);
        DBCursor cursor = table.find(searchQuery);

        String clientName = searchQuery.getString(Name);
        //System.out.println("Looking for: "+Name);// these are to see the name your searching for is being entered
        //errorMessage.setText(ClientName);
        if (cursor.hasNext()==false){
        System.out.println("Cant locate that name.");
        }else{
        while (cursor.hasNext()) {
            DBObject client = cursor.next();
            //System.out.println(cursor.next());
            System.out.println(client.get("name").toString());
            String name=client.get("name").toString();
            String email=client.get("email").toString();
            searchResults.setText(name+"\n"+email+"\n");
//cursor.next().get("name").toString()
        }
    }
    }
    /*public void EnterClient() throws IOException {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", Password().toCharArray());


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
    }*/

    //Adds new client (does not update)
    public void enterClient() 
    {
        
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
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
        con.setRequestProperty("Authorization", apiPassword());
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("Response code " + responseCode);

        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        if (responseCode == 200) {
            loginLabel.setText("Welcome "+ userField.getText());
            Items.setDisable(false);
            client.setDisable(false);
        }
        if (responseCode == 204) {
            loginLabel.setText("User/Password not found.");
            userField.setText("");
            passField.setText("");
        }
        in.close();


        in.close();
        //print result 
        System.out.println(response.toString());
        JSONParser parser = new JSONParser();
        Object object = parser.parse(response.toString());
        System.out.println("object " + object);
        
       
    }
}

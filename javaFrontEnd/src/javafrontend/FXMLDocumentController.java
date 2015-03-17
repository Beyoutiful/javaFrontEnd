/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafrontend;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    protected static Label clientName;
    @FXML
    protected Label clientIDLabel;
    @FXML
    protected static Label clientNumber;
    @FXML
    protected static Label clientAddress;
    @FXML
    protected static Label clientEmail;
    @FXML
    protected static Button submitButton;
    
    @FXML
    private Button submit;
    @FXML
    protected static Button saveButton;
    @FXML
    protected static Button searchButton;
    @FXML
    protected static Button button;
    @FXML
    protected static Tab admin;
   
    @FXML
    protected static Tab schedule;
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
   /*
    *   Login
    */
    @FXML
    protected static Tab login;
    @FXML
    protected static TextField userField;
    @FXML
    protected static PasswordField passField;
    @FXML
    private Label loginLabel;
    @FXML
    protected static Label passLabel;
    @FXML
    protected static Label userLabel;
   /*
    *   Clients
    */
    @FXML
    private Tab client;
    @FXML
    protected TextField clientNameField;
    @FXML
    protected TextField clientNumberField;
    @FXML
    protected TextField clientAddressField;
    @FXML
    protected TextField clientEmailField;
    @FXML
    protected TextField streetNumberField;
    @FXML
    protected TextField cityField;
    @FXML
    protected TextField stateField;
    @FXML
    protected TextField zipcodeField;
    @FXML
    private TextField searchName;
    @FXML
    protected Button createNewClient;
    /*
    *   Services
    */
    @FXML
    protected Button serviceManicure;
    @FXML
    protected Button servicePedicure;
    @FXML
    protected Button serviceNails;
    @FXML 
    protected ListView maniView;
    @FXML 
    protected ListView pediView;
    @FXML 
    protected ListView nailView;
    
    /*
     *   Items
     */
    @FXML
    private Tab Items;
    @FXML
    protected static TextField itemID;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemPrice;
    @FXML
    private TextArea itemDescription; 
    @FXML
    protected static Label itemLabel;
    @FXML
    protected static Label priceLabel;
    @FXML
    protected static Label descriptionLabel;
    @FXML
    protected ObservableList data;
  
    /*
     *   Tech
     */
    @FXML
    protected Label techID;
    @FXML
    protected Tab techTab;
    @FXML
    protected TextField techName;
    @FXML
    protected TextField techPhone;
    @FXML
    protected TextField techEmail;
    @FXML
    protected TextField techStreet;
    @FXML
    protected TextField techCity;
    @FXML
    protected TextField techState;
    @FXML
    protected TextField techZipcode;
    @FXML
    protected TextField techTitle;
    @FXML
    protected TextField techImageURL;
    @FXML
    protected TextArea techDescription;
    @FXML
    protected ImageView techImageBox;
    @FXML
    protected Button createProfileButton;
    @FXML
    protected Button deleteProfileButton;

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
     searchUpdateClient(searchName.getText());
     
     
    }
    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws IOException {
        updateClient();
    }
    @FXML
    private void handleNewClientButton(ActionEvent event){
        newClient();
        createNewClient.setVisible(false);
    }
     @FXML
    private void handleTechSearchButtonAction(ActionEvent event) throws IOException {
     searchTech(techName.getText());
     
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

    public void getMani() throws IOException, ParseException, JSONException {

        String url = "http://beyoutifulstudio.herokuapp.com/api/services/54ebc49353a2c2d504ac5909";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", apiPassword());
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        

        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        JSONObject manicure;
        manicure =  new JSONObject(response.toString());
        //System.out.println(manicure);
        JSONArray items;
        data = FXCollections.observableArrayList();
        items = manicure.getJSONArray("items");
        for(int i = 0; i < items.length();i++){
            JSONObject item = items.getJSONObject(i);
            data.add(i,item.get("name"));
            maniView.setItems(data);
        }
        
        in.close();
    
    }
    public void getPedi() throws IOException, ParseException, JSONException {

        String url = "http://beyoutifulstudio.herokuapp.com/api/services/54ebc49653a2c2d504ac590a";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", apiPassword());
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        

        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        JSONObject pedicure;
        pedicure =  new JSONObject(response.toString());
        //System.out.println(manicure);
        JSONArray items;
        data = FXCollections.observableArrayList();
        items = pedicure.getJSONArray("items");
        for(int i = 0; i < items.length();i++){
            JSONObject item = items.getJSONObject(i);
            data.add(i,item.get("name"));
            pediView.setItems(data);
        }
        
        in.close();
    
    }
    public void getNails() throws IOException, ParseException, JSONException {

        String url = "http://beyoutifulstudio.herokuapp.com/api/services/54ebc48f53a2c2d504ac5908";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", apiPassword());
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        

        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        JSONObject nails;
        nails =  new JSONObject(response.toString());
        //System.out.println(manicure);
        JSONArray items;
        data = FXCollections.observableArrayList();
        items = nails.getJSONArray("items");
        for(int i = 0; i < items.length();i++){
            JSONObject item = items.getJSONObject(i);
            data.add(i,item.get("name"));
            nailView.setItems(data);
        }
        
        in.close();
    
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
    public void deleteProfile() throws IOException{
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
    public void createProfile() throws IOException{
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
    public void searchTech(String Name) throws IOException{
       {        
        String password = Password();
            DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
             boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("technicians");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", Name);
        DBCursor cursor = table.find(searchQuery);

        String technicianName = searchQuery.getString(Name);
        
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
        }else{
         System.out.println("Cant locate that name.");
         clientNameField.setText("");
         clientEmailField.setText("");
         clientNumberField.setText("");
         streetNumberField.setText("");
         cityField.setText("");
         stateField.setText("");
         zipcodeField.setText("");
         createNewClient.setVisible(true);
         
    }
    }
    }
    public void createTech() throws IOException{
        
    }
    public void deleteTech(){
       
    }
    public void searchUpdateClient(String Name) throws IOException 
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
            System.out.println(client);
            Object ID = client.get("_id");
            clientIDLabel.setText(ID.toString());
            clientNameField.setText(client.get("name").toString());
            clientEmailField.setText(client.get("email").toString());
            clientNumberField.setText(client.get("phone").toString());
            streetNumberField.setText(client.get("address").toString());
            cityField.setText(client.get("city").toString());
            stateField.setText(client.get("state").toString());
            zipcodeField.setText(client.get("zip").toString());
            createNewClient.setVisible(false);
            }
        }else{
         System.out.println("Cant locate that name.");
         clientNameField.setText("");
         clientEmailField.setText("");
         clientNumberField.setText("");
         streetNumberField.setText("");
         cityField.setText("");
         stateField.setText("");
         zipcodeField.setText("");
         createNewClient.setVisible(true);
         
    }
    }
  // This function is working as it should
    public void updateClient() throws IOException {
        String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("clients");
        
        BasicDBObject query = new BasicDBObject();
        System.out.println(clientIDLabel.getText());
        query.put("_id", new ObjectId(clientIDLabel.getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", clientNameField.getText());
        newDocument.put("phone", clientNumberField.getText());
        newDocument.put("email", clientEmailField.getText());
        newDocument.put("address", streetNumberField.getText());
        newDocument.put("city", cityField.getText());
        newDocument.put("state", stateField.getText());
        newDocument.put("zip", zipcodeField.getText());
        
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        table.update(query, updateObj);
        clientNameField.setText("");
        clientEmailField.setText("");
        clientNumberField.setText("");
        streetNumberField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipcodeField.setText("");
    }

    //Adds new client (does not update)
    public void newClient() 
    {
        
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject document = new BasicDBObject();
        document.put("name", clientNameField.getText());
        document.put("phone", clientNumberField.getText());
        document.put("email", clientEmailField.getText());
        document.put("address", streetNumberField.getText());
        document.put("city", cityField.getText());
        document.put("state", stateField.getText());
        document.put("zip", zipcodeField.getText());
        document.put("__v", 0);
        table.insert(document);
        clientNameField.setText("");
        clientEmailField.setText("");
        clientNumberField.setText("");
        streetNumberField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipcodeField.setText("");
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

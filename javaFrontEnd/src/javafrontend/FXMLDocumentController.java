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
    private Label loginLabel;
    @FXML
    private Button submit;
    @FXML
    private Button searchButton;
    @FXML
    private Button saveButton;
    @FXML
    private Tab client;
    @FXML
    private Tab login;
    @FXML
    private Tab Items;
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
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, MalformedURLException, ParseException {
        Password();
        Login();
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {

        GetName(clientNameField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void database() {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());
        //save example
        System.out.println("auth: " + auth);
        DBCollection table = BEYOU_DB.getCollection("services");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Brandon");
        document.put("price", 65);
        document.put("description", "new toes");
        document.put("__v", 0);
        table.insert(document);

    }

    public void getServices() {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());
        //save example
        System.out.println("auth: " + auth);
        DBCollection table = BEYOU_DB.getCollection("services");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Manicure");

        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    public void getItems() {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());
        //save example
        System.out.println("auth: " + auth);
        DBCollection table = BEYOU_DB.getCollection("items");

        BasicDBObject searchQuery = new BasicDBObject();

        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) {
            DBObject item = cursor.next();
            System.out.println(item.get("_id"));
            choiceBox.getItems().add(item.get("name"));
        }
    }

    public void populateItem() {
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());
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
    private String Password() throws FileNotFoundException, IOException{
        String password="";
        try {
			File file = new File("/Users/brandonfoss/NetBeansProjects/javaFrontEnd/javaFrontEnd/src/javafrontend/BEYOU_Config.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
                        password=stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return password;
       // FileInputStream inf = new FileInputStream("/Users/brandonfoss/NetBeansProjects/javaFrontEnd/javaFrontEnd/src/javafrontend/BEYOU_Config.txt");
        //inf.read(password);
    }
    public void updateCollection() {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());

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

    public void GetName(String Name) {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());
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

    public void EnterClient() {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", "P00k!ooFff".toCharArray());

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

    private void Login() throws MalformedURLException, IOException, ParseException {
        String url = "http://beyoutifulstudio.herokuapp.com/api/technicians?email="
                + userField.getText() + "&password=" + passField.getText();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", "Basic UDAwayFvb0ZmZjo=");
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
        //print result 
        /*System.out.println(response.toString());
         JSONParser parser = new JSONParser();
         Object object = parser.parse(response.toString());
         System.out.println("object " + object);*/

    }
}

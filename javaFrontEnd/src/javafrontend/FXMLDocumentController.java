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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bson.BSONObject;
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
    private TableView mainTable;
    @FXML
    private MenuButton menu;
   // private final MongoClient client;
    private final MongoClient mongoClient;

    public FXMLDocumentController() throws UnknownHostException {
        this.mongoClient = new MongoClient("ds035750.mongolab.com",35750);
       // this.client = new MongoClient();
    }

    
    @FXML
    private void handleButtonAction(ActionEvent event)  {
     // database();
        //getServices();
        //updateCollection();
        //getName();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    public void database(){
   MongoClient mongo;
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
        MongoClient mongo;
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
    
    public void updateCollection(){
        MongoClient mongo;
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
        MongoClient mongo;
        DB db = mongoClient.getDB("heroku_app33977271");
        boolean auth = db.authenticate("beyoutiful","P00k!ooFff".toCharArray());
        DBCollection table = db.getCollection("Clients");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name",Name);
        DBCursor cursor = table.find(searchQuery);
        String ClientName=searchQuery.getString(Name);
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
                System.out.println("name "+ClientName);
                clientNameField.setText(ClientName);
        }
    }
}
/*String url = "http://beyoutifulstudio.herokuapp.com/api/services/54e12e5bb84210161c2c1b45";
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
        System.out.println("object "+ object);*/

//String url = "http://192.168.7.154:3000/api/services/";
//       URL obj = new URL(url);
//       HttpURLConnection con = (HttpURLConnection)obj.openConnection();
//       con.setRequestProperty("Authorization", "Basic UDAwayFvb0ZmZjo=");
//       con.setRequestProperty("Content-Type", "application/json; charset=utf-8" );
//       
//       con.setRequestProperty("Accept", "*/*");
//       con.setRequestMethod("POST");
//       con.setDoOutput(true);
//       
//       JSONObject Service = new JSONObject();
//       JSONObject Parent = new JSONObject();
//       
//       Service.put("price", 65);
//       Service.put("name", "Gels");
//       Service.put("description","toes");
//       Parent.put("service", Service);
//       
//       OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
//       System.out.println("string "+ Parent.toString());
//       wr.write(Parent.toString());
//       wr.close();
//       int responceCode= con.getResponseCode();
//        System.out.println("Responce code "+responceCode);
//        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream())); 
//        String inputLine; StringBuffer response = new StringBuffer();   
//        while ((inputLine = in.readLine()) != null) { 
//            response.append(inputLine); 
//        } 
//        in.close();   
//    //print result 
//        System.out.println(response.toString());
//        JSONParser parser=new JSONParser();
//        Object object = parser.parse(response.toString());
//        System.out.println("object "+ object);

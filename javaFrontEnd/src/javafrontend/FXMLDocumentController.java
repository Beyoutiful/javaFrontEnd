/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafrontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


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
    private void handleButtonAction(ActionEvent event) throws MalformedURLException, IOException, ParseException {
      String url = "http://192.168.7.154:3000/api/services/";
       URL obj = new URL(url);
       HttpURLConnection con = (HttpURLConnection)obj.openConnection();
       con.setRequestProperty("Authorization", "auth key");
       con.setRequestProperty("Content-Type", "application/json; charset=utf-8" );
       
       con.setRequestProperty("Accept", "*/*");
       con.setRequestMethod("POST");
       con.setDoOutput(true);
       
       JSONObject Service = new JSONObject();
       JSONObject Parent = new JSONObject();
       
       Service.put("price", 65);
       Service.put("name", "Gels");
       Service.put("description","toes");
       Parent.put("service", Service);
       
       OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
       System.out.println("string "+ Parent.toString());
       wr.write(Parent.toString());
       wr.close();
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
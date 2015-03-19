/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafrontend;

import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Brandon Foss
 * 
 */
public class FXMLDocumentController implements Initializable {
    Items items = new Items();
    Technicians technicians = new Technicians(this);
    Clients clients = new Clients(this);
    
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
    protected TextField userField;
    @FXML
    protected PasswordField passField;
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
    private Tab clientTab;
    @FXML
    protected Label clientIDLabel;
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
     TextField searchName;
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
    public ListView maniView;
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
    protected TextField itemID;
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
        System.out.println(searchName.getText());
    clients.searchClient(searchName.getText());
     
     
    }
    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws IOException {
     clients.updateClient();
    }
    @FXML
    private void handleNewClientButton(ActionEvent event){
        clients.newClient(clientNameField.getText());
        createNewClient.setVisible(false);
    }
     @FXML
    private void handleManiButton(ActionEvent event) throws IOException, ParseException, JSONException{
        items.getMani(maniView);
    }
      @FXML
    private void handlePediButton(ActionEvent event) throws IOException, ParseException, JSONException{
        items.getPedi(pediView);
    }
      @FXML
    private void handleNailsButton(ActionEvent event) throws IOException, ParseException, JSONException{
        items.getNails(nailView);
    }
     @FXML
    private void handleTechSearchButtonAction(ActionEvent event) throws IOException {
    String name = new String();
     technicians.searchTech();
    }
      @FXML
    private void handleCreateProfileButton(ActionEvent event) throws IOException, ParseException, JSONException{
        technicians.createProfile(techID);
    }
     @FXML
    private void handleDeleteProfileButton(ActionEvent event) throws IOException, ParseException, JSONException{
        technicians.deleteProfile(techID);
    }
     @FXML
    private void handleTechSaveButton(ActionEvent event) throws IOException, ParseException, JSONException{
        technicians.updateTech();
    }
     @FXML
    private void handleNewTechButton(ActionEvent event) throws IOException, ParseException, JSONException{
        technicians.newTech();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            clientTab.setDisable(false);
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

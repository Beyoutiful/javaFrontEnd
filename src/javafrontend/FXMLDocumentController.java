package javafrontend;

import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Brandon Foss, Calvin Brewer
 *
 */
public class FXMLDocumentController implements Initializable {

    Items items = new Items(this);
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
    @FXML
    protected Tab appointmentsTab;
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
    protected Tab servicesTab;
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
    @FXML
    protected Button getItem;
    @FXML
    protected TextField maniTitle;
    @FXML
    protected TextField maniDesc;
    @FXML
    protected TextField maniPrice;
    @FXML
    protected Button maniUpdate;
    @FXML
    protected Button maniCreate;
    @FXML
    protected TextField pediTitle;
    @FXML
    protected TextField pediDesc;
    @FXML
    protected TextField pediPrice;
    @FXML
    protected Button pediUpdate;
    @FXML
    protected Button pediCreate;
    @FXML
    protected TextField nailTitle;
    @FXML
    protected TextField nailDesc;
    @FXML
    protected TextField nailPrice;
    @FXML
    protected Button nailUpdate;
    @FXML
    protected Button nailCreate;
    @FXML
    protected Label pediIDlabel;
    @FXML
    protected Label maniIDlabel;
    @FXML
    protected Label nailsIDlabel;
    @FXML
    protected Button pediUpdateButton;
    @FXML
    protected Button maniUpdateButton;
    @FXML
    protected Button nailUpdateButton;
    @FXML
    protected Button createNewMani;
    @FXML
    protected Button createNewPedi;
    @FXML
    protected Button createNewNail;
    @FXML
    protected TextField itemID;
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
    @FXML
    protected Button uploadButton;
    /*
     * Appointments
     */
    @FXML
    protected WebView appointmentsWebView;
    @FXML
    protected Button loadCalButton;
    
    private final MongoClient mongoClient;

    public FXMLDocumentController() throws UnknownHostException {
        mongoClient = new MongoClient(mongo, port);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws
            MalformedURLException, ParseException {

        try {
            login();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws
            IOException {

        clients.searchClient(searchName.getText());
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws IOException {
        clients.updateClient();
    }

    @FXML
    private void handleNewClientButton(ActionEvent event) {
        clients.newClient(clientNameField.getText());
        createNewClient.setVisible(false);
    }

    @FXML
    private void handleManiButton(ActionEvent event) throws IOException, ParseException, JSONException {
        items.getMani(maniView);
    }

    @FXML
    private void handlePediButton(ActionEvent event) throws IOException, ParseException, JSONException {
        items.getPedi(pediView);
    }

    @FXML
    private void handleNailsButton(ActionEvent event) throws IOException, ParseException, JSONException {
        items.getNails(nailView);
    }

    @FXML
    private void handleTechSearchButtonAction(ActionEvent event) throws IOException {
        String name = new String();
        technicians.searchTech();
    }

    @FXML
    private void handleCreateProfileButton(ActionEvent event) throws IOException, ParseException, JSONException {
        technicians.createProfile(techID);
    }

    @FXML
    private void handleDeleteProfileButton(ActionEvent event) throws IOException, ParseException, JSONException {
        technicians.deleteProfile(techID);
    }

    @FXML
    private void handleTechSaveButton(ActionEvent event) throws IOException, ParseException, JSONException {
        technicians.updateTech();
    }

    @FXML
    private void handleNewTechButton(ActionEvent event) throws IOException, ParseException, JSONException {
        technicians.newTech();
    }

    @FXML
    private void handleNewImageButton(ActionEvent event) throws IOException, ParseException, JSONException {
        technicians.newImage();
    }

    @FXML
    private void handleNailItem(ActionEvent event) throws IOException {
        items.queryNails(nailView);
    }

    @FXML
    private void handleManiItem(ActionEvent event) throws IOException {
        items.queryMani(maniView);
    }

    @FXML
    private void handlePediItem(ActionEvent event) throws IOException {
        items.queryPedi(pediView);
    }

    @FXML
    private void handleNailUpdate(ActionEvent event) throws IOException {
        items.setNails();
    }

    @FXML
    private void handleManiUpdate(ActionEvent event) throws IOException {
        items.setMani();
    }

    @FXML
    private void handlePediUpdate(ActionEvent event) throws IOException {
        items.setPedi();
    }

    @FXML
    private void handleNailNew(ActionEvent event) throws IOException {
        items.newNail();
    }

    @FXML
    private void handleManiNew(ActionEvent event) throws IOException {
        items.newMani();
    }

    @FXML
    private void handlePediNew(ActionEvent event) throws IOException {
        items.newPedi();
    }

    @FXML
    private void handleWebview(ActionEvent event) throws IOException {
        web();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void web() {
        WebEngine webEngine = appointmentsWebView.getEngine();
        webEngine.load("https://www.google.com/calendar/embed?mode=WEEK&amp;height=600&amp;wkst=2&amp;bgcolor=%23FFFFFF&amp;src=2a1gjrgtbvtprfirhclrvl79rg@group.calendar.google.com&amp;color=%23691426&amp;src=beyoutiful4dmin@gmail.com&amp;color=%231B887A&amp;src=3qs9gbh412e87j58nfmge2tpgk@group.calendar.google.com&amp;color=%2342104A&amp;src=hb7qppbc7gbjmh84qrp98gg3c0@group.calendar.google.com&amp;color=%2329527A&amp;ctz=America/Denver&pli=1");
    }
//
//    protected static String Password() throws FileNotFoundException, IOException {
//        String password = "";
//        try {
//            File file = new File("./javafrontend/dist/config/BEYOU_Config.txt");
//            StringBuilder stringBuffer;
//            try (FileReader fileReader = new FileReader(file)) {
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                stringBuffer = new StringBuilder();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuffer.append(line);
//                }
//            }
//            password = stringBuffer.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return password;
//    }
//
//    protected static String apiPassword() throws FileNotFoundException, IOException {
//        String password = "";
//        try {
//            File file = new File("./javafrontend/dist/config/API_Config.txt");
//            StringBuilder stringBuffer;
//            try (FileReader fileReader = new FileReader(file)) {
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                stringBuffer = new StringBuilder();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuffer.append(line);
//                }
//            }
//            password = stringBuffer.toString();
//        } catch (IOException e) {
//
//        }
//        return password;
//    }

    private void login() throws MalformedURLException, IOException, ParseException {
        String url = "http://beyoutifulstudio.herokuapp.com/api/technicians?email="
                + userField.getText() + "&password=" + passField.getText();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", apiPass);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("Response code " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            if (responseCode == 200) {
                loginLabel.setText("Welcome " + userField.getText());
                appointmentsTab.setDisable(false);
                clientTab.setDisable(false);
                techTab.setDisable(false);
                servicesTab.setDisable(false);
            }
            if (responseCode == 204) {
                loginLabel.setText("User/Password not found.");
                userField.setText("");
                passField.setText("");
            }
        }

    }
}

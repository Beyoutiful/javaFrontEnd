/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafrontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static javafrontend.FXMLDocumentController.apiPassword;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author brandonfoss
 */
public class items {
  
    @FXML
    protected ObservableList data;
    @FXML
    protected TextField itemID;   
    @FXML
    protected static Label itemLabel;
    @FXML
    protected static Label priceLabel;
    @FXML
    protected static Label descriptionLabel;
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
    protected ListView pediView;
    @FXML
    protected ListView nailView;

    @FXML
    public void getMani(ListView maniView) throws IOException, ParseException, JSONException {
        //This is hard coded
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
        manicure = new JSONObject(response.toString());
        //System.out.println(manicure);
        JSONArray items;
        data = FXCollections.observableArrayList();
        items = manicure.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            data.add(i, item.get("name"));
            maniView.setItems(data);
        }

        in.close();

    }

    public void getPedi(ListView pediView) throws IOException, ParseException, JSONException {
        //This is hard coded
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
        pedicure = new JSONObject(response.toString());
        //System.out.println(manicure);
        JSONArray items;
        data = FXCollections.observableArrayList();
        items = pedicure.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            data.add(i, item.get("name"));
            pediView.setItems(data);
        }

        in.close();

    }

    public void getNails(ListView nailView) throws IOException, ParseException, JSONException {
        // this is hard coded
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
        nails = new JSONObject(response.toString());
        //System.out.println(manicure);
        JSONArray items;
        data = FXCollections.observableArrayList();
        items = nails.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            data.add(i, item.get("name"));
            nailView.setItems(data);
        }

        in.close();

    }
}

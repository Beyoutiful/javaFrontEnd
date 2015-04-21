/**
 *
 * @author Brandon Foss, Calvin Brewer
 * 
 */
package javafrontend;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import static javafrontend.FXMLDocumentController.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;


public class Items {

    FXMLDocumentController controller;
    @FXML
    protected ObservableList data;
    /*
     *   Services
     */
    private final MongoClient mongoClient;

    public Items(FXMLDocumentController _controller) throws UnknownHostException {
        controller = _controller;
        mongoClient = new MongoClient(mongo, port);
    }

    protected TextField maniTitle() {
        return controller.maniTitle;
    }

    protected TextField maniDesc() {
        return controller.maniDesc;
    }

    protected TextField maniPrice() {
        return controller.maniPrice;
    }

    protected Button maniUpload() {
        return controller.maniUpdate;
    }

    protected Button maniCreate() {
        return controller.maniCreate;
    }

    protected TextField pediTitle() {
        return controller.pediTitle;
    }

    protected TextField pediDesc() {
        return controller.pediDesc;
    }

    protected TextField pediPrice() {
        return controller.pediPrice;
    }

    protected Button pediUpload() {
        return controller.pediUpdate;
    }

    protected Button pediCreate() {
        return controller.pediCreate;
    }

    protected TextField nailTitle() {
        return controller.nailTitle;
    }

    protected TextField nailDesc() {
        return controller.nailDesc;
    }

    protected TextField nailPrice() {
        return controller.nailPrice;
    }

    protected Button nailUpdate() {
        return controller.nailUpdate;
    }

    protected Button nailCreate() {
        return controller.nailCreate;
    }

    protected Label pediIDlabel() {
        return controller.pediIDlabel;
    }

    protected Label maniIDlabel() {
        return controller.maniIDlabel;
    }

    protected Label nailsIDlabel() {
        return controller.nailsIDlabel;
    }

    @FXML
    public void getMani(ListView maniView) throws IOException, ParseException, JSONException {
        String url = "http://beyoutifulstudio.herokuapp.com/api/services/54ebc49353a2c2d504ac5909";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", FXMLDocumentController.apiPass);
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
        String url = "http://beyoutifulstudio.herokuapp.com/api/services/54ebc49653a2c2d504ac590a";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", FXMLDocumentController.apiPass);
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
        String url = "http://beyoutifulstudio.herokuapp.com/api/services/54ebc48f53a2c2d504ac5908";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", FXMLDocumentController.apiPass);
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

    public void queryNails(ListView nailView) throws IOException {
        String name = new String();
        nailTitle().setText(nailView.getSelectionModel().getSelectedItem().toString());
        name = nailView.getSelectionModel().getSelectedItem().toString();
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);
        DBCursor cursor = table.find(searchQuery);
        String itemName = searchQuery.getString(name);
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject items = cursor.next();
                System.out.println(items);
                Object ID = items.get("_id");
                nailsIDlabel().setText(ID.toString());
                nailTitle().setText(items.get("name").toString());
                nailDesc().setText(items.get("description").toString());
                nailPrice().setText(items.get("price").toString());
            }
        }
    }

    public void queryMani(ListView maniView) throws IOException {
        maniTitle().setText(maniView.getSelectionModel().getSelectedItem().toString());
        String name = new String();
        name = maniView.getSelectionModel().getSelectedItem().toString();
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);
        DBCursor cursor = table.find(searchQuery);
        String itemName = searchQuery.getString(name);
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject items = cursor.next();
                System.out.println(items);
                Object ID = items.get("_id");
                maniIDlabel().setText(ID.toString());
                maniTitle().setText(items.get("name").toString());
                maniDesc().setText(items.get("description").toString());
                maniPrice().setText(items.get("price").toString());
            }
        }
    }

    public void queryPedi(ListView pediView) throws IOException {
        pediTitle().setText(pediView.getSelectionModel().getSelectedItem().toString());
        String name = new String();
        name = pediView.getSelectionModel().getSelectedItem().toString();
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);
        DBCursor cursor = table.find(searchQuery);
        String itemName = searchQuery.getString(name);
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject items = cursor.next();
                System.out.println(items);
                Object ID = items.get("_id");
                pediIDlabel().setText(ID.toString());
                pediTitle().setText(items.get("name").toString());
                pediDesc().setText(items.get("description").toString());
                pediPrice().setText(items.get("price").toString());
            }
        }
    }

    public void setMani() throws IOException {
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject query = new BasicDBObject();
        System.out.println(maniIDlabel().getText());
        query.put("_id", new ObjectId(maniIDlabel().getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", maniTitle().getText());
        newDocument.put("description", maniDesc().getText());
        newDocument.put("price", maniPrice().getText());
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);
        table.update(query, updateObj);
        maniTitle().setText("");
        maniDesc().setText("");
        maniPrice().setText("");
    }

    public void setPedi() throws IOException {
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject query = new BasicDBObject();
        System.out.println(pediIDlabel().getText());
        query.put("_id", new ObjectId(pediIDlabel().getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", pediTitle().getText());
        newDocument.put("description", pediDesc().getText());
        newDocument.put("price", pediPrice().getText());
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);
        table.update(query, updateObj);
        maniTitle().setText("");
        maniDesc().setText("");
        maniPrice().setText("");
    }

    public void setNails() throws IOException {
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject query = new BasicDBObject();
        System.out.println(nailsIDlabel().getText());
        query.put("_id", new ObjectId(nailsIDlabel().getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", nailTitle().getText());
        newDocument.put("description", nailDesc().getText());
        newDocument.put("price", nailPrice().getText());
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);
        table.update(query, updateObj);
        nailTitle().setText("");
        nailDesc().setText("");
        nailPrice().setText("");
    }

    public void newMani() throws IOException {
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject document = new BasicDBObject();
        document.put("name", maniTitle().getText());
        document.put("description", maniDesc().getText());
        document.put("price", maniPrice().getText());
        document.put("__v", 0);
        table.insert(document);
        ObjectId ItemId = (ObjectId) document.get("_id");
        DBCollection table1;
        table1 = BEYOU_DB.getCollection("services");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Manicures");
        DBCursor cursor = table1.find(searchQuery);
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject service = cursor.next();
                BasicDBList theItems = (BasicDBList) service.get("items");
                theItems.add(ItemId);
                BasicDBObject query = new BasicDBObject();
                query.put("_id", new ObjectId("54ebc48f53a2c2d504ac5909"));
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("items", theItems);
                BasicDBObject updateObj = new BasicDBObject();
                updateObj.put("$set", newDocument);
                table1.update(query, updateObj);
            }
        }
        maniTitle().setText("");
        maniDesc().setText("");
        maniPrice().setText("");
    }

    public void newPedi() throws IOException {
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject document = new BasicDBObject();
        document.put("name", pediTitle().getText());
        document.put("description", pediDesc().getText());
        document.put("price", pediPrice().getText());
        document.put("__v", 0);
        table.insert(document);
        ObjectId ItemId = (ObjectId) document.get("_id");
        DBCollection table1;
        table1 = BEYOU_DB.getCollection("services");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Pedicures");
        DBCursor cursor = table1.find(searchQuery);
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject service = cursor.next();
                BasicDBList theItems = (BasicDBList) service.get("items");
                theItems.add(ItemId);
                BasicDBObject query = new BasicDBObject();
                query.put("_id", new ObjectId("54ebc49653a2c2d504ac590a"));
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("items", theItems);
                BasicDBObject updateObj = new BasicDBObject();
                updateObj.put("$set", newDocument);
                table1.update(query, updateObj);
            }
        }
        pediTitle().setText("");
        pediDesc().setText("");
        pediPrice().setText("");
    }

    public void newNail() throws IOException {
        String password = FXMLDocumentController.beyouPass;
        DB BEYOU_DB = mongoClient.getDB(FXMLDocumentController.DB);
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject document = new BasicDBObject();
        document.put("name", nailTitle().getText());
        document.put("description", nailDesc().getText());
        document.put("price", nailPrice().getText());
        document.put("__v", 0);
        table.insert(document);
        ObjectId ItemId = (ObjectId) document.get("_id");
        DBCollection table1;
        table1 = BEYOU_DB.getCollection("services");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Nails");
        DBCursor cursor = table1.find(searchQuery);
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject service = cursor.next();
                BasicDBList theItems = (BasicDBList) service.get("items");
                theItems.add(ItemId);
                BasicDBObject query = new BasicDBObject();
                query.put("_id", new ObjectId("54ebc48f53a2c2d504ac5908"));
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("items", theItems);
                BasicDBObject updateObj = new BasicDBObject();
                updateObj.put("$set", newDocument);
                table1.update(query, updateObj);
            }
        }
        nailTitle().setText("");
        nailDesc().setText("");
        nailPrice().setText("");
    }
}

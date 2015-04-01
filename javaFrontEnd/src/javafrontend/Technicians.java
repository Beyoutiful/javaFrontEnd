
package javafrontend;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.lang.Object;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafrontend.FXMLDocumentController.Password;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.bson.types.ObjectId;

/**
 *
 * @author brandonfoss
 */
public class Technicians {

    FXMLDocumentController controller;
    private final MongoClient mongoClient;

    protected Label techID() { return controller.techID; }
    protected Tab techTab() { return controller.techTab; }
    protected TextField techName() { return controller.techName; }
    protected TextField techPhone() { return controller.techPhone; }
    protected TextField techEmail() { return controller.techEmail; }
    protected TextField techStreet() { return controller.techStreet; }
    protected TextField techCity() { return controller.techCity; }
    protected TextField techState() { return controller.techState; }
    protected TextField techZipcode() { return controller.techZipcode; }
    protected TextField techTitle() { return controller.techTitle; }
    protected TextField techImageURL() { return controller.techImageURL; }
    protected TextArea techDescription() { return controller.techDescription; }
    protected ImageView techImageBox() { return controller.techImageBox; }
    protected Button createProfileButton() { return controller.createProfileButton; }
    protected Button deleteProfileButton() { return controller.deleteProfileButton; }
    protected Button uploadButton() { return controller.uploadButton; }
    
    public Technicians(FXMLDocumentController _controller) throws UnknownHostException {

        controller = _controller;
        mongoClient = new MongoClient("ds035750.mongolab.com", 35750);
    }

    public void deleteProfile(Label techID) throws IOException {
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
        if (cursor.hasNext() == true) {
            while (cursor.hasNext()) {
                DBObject profiles = cursor.next();

                proID = profiles.get("_id");

            }

        }
        BasicDBObject deleteQuery = new BasicDBObject();
        deleteQuery.put("_id", new ObjectId(proID.toString()));
        table.remove(deleteQuery);
    }

    public void createProfile(Label techID) throws IOException {
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

    public void searchTech() throws IOException {
        {
            String name = new String();
            name = techName().getText();
            String password = Password();
            DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
            boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

            DBCollection table = BEYOU_DB.getCollection("technicians");
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", name);
            DBCursor cursor = table.find(searchQuery);

            String technicianName = searchQuery.getString(name);

            if (cursor.hasNext() == true) {
                while (cursor.hasNext()) {
                    DBObject technicians = cursor.next();

                    Object ID = technicians.get("_id");
                    techID().setText(ID.toString());
                    techName().setText(technicians.get("name").toString());
                    techEmail().setText(technicians.get("email").toString());
                    techPhone().setText(technicians.get("phone").toString());
                    techStreet().setText(technicians.get("address").toString());
                    techCity().setText(technicians.get("city").toString());
                    techState().setText(technicians.get("state").toString());
                    techZipcode().setText(technicians.get("zip").toString());
                    techTitle().setText(technicians.get("title").toString());
                    techImageURL().setText(technicians.get("image").toString());
                    techDescription().setText(technicians.get("description").toString());

                    Image image = new Image("http://beyoutifulstudio.herokuapp.com/" + technicians.get("image"));
                    techImageBox().setImage(image);

                }
            }
        }
    }

    public void updateTech() throws IOException {
        String password = Password();
        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        boolean auth = BEYOU_DB.authenticate("beyoutiful", password.toCharArray());

        DBCollection table = BEYOU_DB.getCollection("technicians");

        BasicDBObject query = new BasicDBObject();

        query.put("_id", new ObjectId(techID().getText()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", techName().getText());
        newDocument.put("phone", techPhone().getText());
        newDocument.put("email", techEmail().getText());
        newDocument.put("address", techStreet().getText());
        newDocument.put("city", techCity().getText());
        newDocument.put("state", techState().getText());
        newDocument.put("zip", techZipcode().getText());
        newDocument.put("title", techTitle().getText());
        newDocument.put("image", techImageURL().getText());
        newDocument.put("description", techDescription().getText());
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        table.update(query, updateObj);
        techName().setText("");
        techPhone().setText("");
        techEmail().setText("");
        techStreet().setText("");
        techCity().setText("");
        techState().setText("");
        techZipcode().setText("");
        techTitle().setText("");
        techImageURL().setText("");
        techDescription().setText("");
        Image image = null;
        techImageBox().setImage(image);
    }

    public void newTech() {

        DB BEYOU_DB = mongoClient.getDB("heroku_app33977271");
        DBCollection table = BEYOU_DB.getCollection("clients");
        BasicDBObject document = new BasicDBObject();
        document.put("name", techName().getText());
        document.put("phone", techPhone().getText());
        document.put("email", techEmail().getText());
        document.put("address", techStreet().getText());
        document.put("city", techCity().getText());
        document.put("state", techState().getText());
        document.put("zip", techZipcode().getText());
        document.put("title", techTitle().getText());
        document.put("image", techImageURL().getText());
        document.put("description", techDescription().getText());
        document.put("__v", 0);
        table.insert(document);
        techName().setText("");
        techPhone().setText("");
        techEmail().setText("");
        techStreet().setText("");
        techCity().setText("");
        techState().setText("");
        techZipcode().setText("");
        techTitle().setText("");
        techImageURL().setText("");
        techDescription().setText("");
    }
    public void newImage() {
        final JFrame frame = new JFrame("Image Selector");
        //Cloudinary cloudinary = new Cloudinary("cloudinary://392683615658657:7fBLpLmr3bQwHrMVyn_k7YR8m_I@hslj0appl");
        Map config = new HashMap(); 
        config.put("cloud_name", "hslj0appl");
        config.put("api_key", "392683615658657");
        config.put("api_secret", "7fBLpLmr3bQwHrMVyn_k7YR8m_I"); 
        Cloudinary cloudinary = new Cloudinary(config);
        
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setCurrentDirectory(new File("C:\\tmp"));
 
        JButton btn1 = new JButton("Select Image");
        btn1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showDialog(frame, "Choose");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("Opening: " + file.getAbsolutePath());
                   File img = new File(file.getAbsolutePath());
                  /*  try {
                       // Map uploadResult = cloudinary.uploader().upload(img, ObjectUtils.emptyMap());
                    } catch (IOException ex) {
                        Logger.getLogger(Technicians.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    
                    
                }
            }
           
        });
        
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(1, 1, 10, 10));
        pane.add(btn1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    }


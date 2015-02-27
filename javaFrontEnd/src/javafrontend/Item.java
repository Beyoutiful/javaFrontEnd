package javafrontend;
/**
 * @author sean
 * Implements Items collection from database
 * See end of Item.java for original methods from DocumentController I've
 * replaced --Sean, 26 Feb 2015
 */

public class Item 
{
    private DBCollection table;
    private BasicDBObject searchQuery;
    
    public Item()
    {
        this.table = FXMLDocumentController.BEYOU_DB.getCollection("items");
        this.searchQuery = new BasicDBObject();
        
    }
    
    public void getItems()
    {
        DBCursor cursor = table.find(searchQuery);
        
        while (cursor.hasNext())
        {
            DBObject item = cursor.next();
            
            System.out.println(item.get("_id"));
            FXMLDocumentController.choiceBox.getItems().add(item.get("name"));
        }
    }
    
    public void populateItem()
    {
        DBCursor cursor = table.find(searchQuery);
        
        while (cursor.hasNext()) 
        {
            DBObject item = cursor.next();
            
            FXMLDocumentController.itemID.setText((String) item.get("_id"));
            FXMLDocumentController.itemName.setText((String) item.get("name"));
            FXMLDocumentController.itemPrice.setText((String) item.get("price"));
            FXMLDocumentController.itemDescription.setText((String) item.get("description"));
        }
    }
}

    //***Below are the methods I've attempted to replace with the class Item -SEAN***
    /*public void getItems() 
    {
        DBCollection table = BEYOU_DB.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) 
        {
            DBObject item = cursor.next();
            System.out.println(item.get("_id"));
            choiceBox.getItems().add(item.get("name"));
        }
    }

    public void populateItem() 
    {
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
    }*/
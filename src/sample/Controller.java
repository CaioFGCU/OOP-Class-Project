package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents the javaFX code for the GUI with all text fields, tables,
 * boxes, buttons, and tabs.
 * used to control GUI functions, actions, displays, and calls from Database tables and
 * the Database Manager class.
 * made to manage changes and functionality to GUI and operations within it.
 */
public class Controller {

    @FXML
    private Tab pr;

    @FXML
    private TextField productNameText;

    @FXML
    private TextField manufacText;

    @FXML
    private ChoiceBox<ItemType> itemtype_choice;

    @FXML
    private TableView<Product> Product_Table;

    @FXML
    private TableColumn<?, Product> column_ID;

    @FXML
    private TableColumn<?, Product> column_Name;

    @FXML
    private TableColumn<?, Product> column_Manuf;

    @FXML
    private TableColumn<?, Product> column_Type;

    @FXML
    private Button addButton;

    @FXML
    private ListView<Product> Product_List;

    @FXML
    private ComboBox<String> cboQuantity;

    @FXML
    private Button buttonRecProduction;

    @FXML
    private TextArea ProdLogTxtArea;

    @FXML
    private Tab emptab;

    @FXML
    private TextField employee_un;

    @FXML
    private PasswordField employee_pw;

    @FXML
    private TextField employee_Login_display;

    @FXML
    private Button login_button;

    private ObservableList<Product> productLine;

    private ObservableList<ProductionRecord> productionLog;

    Statement statement;

    /**
     * loads the product list in product tab
     * shows production record in productionRecord tab
     * method used to initialize database and comboBox
     * adds values to comboBox and allows users to select quantity.
     * initializes tableview and seperate columns within it
     */
    @FXML
    void initialize() {
        try {
            DatabaseManager dm = new DatabaseManager();
            productLine = FXCollections.observableArrayList((dm.GetProductsfromDB()));
            loadProductList();
            productionLog = FXCollections.observableArrayList((dm.getProductionRecords()));
            showProduction();
            Product_List.setItems(productLine);
            Product_Table.setItems(productLine);


            column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
            column_Manuf.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            column_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            column_Type.setCellValueFactory(new PropertyValueFactory<>("type"));


        } catch (Exception e) {
            e.printStackTrace();
        }

        cboQuantity.getItems().addAll("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10");
        cboQuantity.setEditable(true);
        cboQuantity.getSelectionModel().selectFirst();
        itemtype_choice.getItems().addAll(ItemType.values());

        /**
         * Issue4
         * creates production record and prints it out for testing.
         */
// test constructor used when creating production records from user interface
        Integer numProduced = 3; // this will come from the combobox in the UI

        for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
            ProductionRecord pr = new ProductionRecord(0);
            System.out.println(pr.toString());
        }

/* test constructor used when creating production records from reading database
        ProductionRecord pr = new ProductionRecord(0, 3, "1", new java.util.Date());
        ProdLogTxtArea.setText(pr.toString());

// testing accessors and mutators
        pr.setProductionNum(1);
        System.out.println(pr.getProductionNum());

        pr.setProductID(4);
        System.out.println(pr.getProductID());

        pr.setSerialNum("2");
        System.out.println(pr.getSerialNum());

        pr.setProdDate(new Date());
        System.out.println(pr.getProdDate());
*/
    }

    /**
     * used to get instance of product into database and will be used later on to dynamically add
     * products to Tableview and PRODUCT table while program is running.
     */
    public void loadProductList() {

        DatabaseManager dm = new DatabaseManager();
        List<Product> listOfItem = null;
        try {
            listOfItem = dm.GetProductsfromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        productLine.addAll(listOfItem);

    }


    /**
     * this method gets the input from the user in the GUI text field in the PRODUCT tab
     * and transfers to the database
     * displays product list with name, manufacturer, and type
     * also used for pop up if name, manufacturer, and item type is left blank to display message.
     *
     * @param event event parameter used for action of mouse click on ADD PRODUCT button
     */
    @FXML
    void handleAddProduct(MouseEvent event) {
        try {
            String productName = productNameText.getText();
            String manufacturer = manufacText.getText();
            ItemType itemChoice = itemtype_choice.getValue();
            if (productName.isEmpty()) throw new IllegalArgumentException("Product must have a name.");
            if (manufacturer.isEmpty()) throw new IllegalArgumentException("Manufacturer must have a name.");
            if (itemChoice == null) throw new IllegalArgumentException("Must choose an item.");
            DatabaseManager dm = new DatabaseManager();
            dm.addProductToDB(productName, manufacturer, itemChoice);
            loadProductList();
        } catch (IllegalArgumentException ex) {
            ErrorMessage.popUpCreater(ex.getMessage());
        }
    }


    /**
     * record button event handler that records product chosen from product list view
     * also gets value from combobox and adds the quantity of the product
     * calls showproduction to display all of the above
     *
     * @param event
     */
    @FXML
    void handle_RecProd(MouseEvent event) {
        try {
            Product getproduct = Product_List.getSelectionModel().getSelectedItem();
            String getcmbo_quantity = cboQuantity.getValue();
            Integer int_quntity;
            try {
                int_quntity = Integer.parseInt(getcmbo_quantity);
            } catch (NumberFormatException ex) {
                int_quntity = 0;
            }
            if (getproduct == null) throw new IllegalArgumentException("Must choose product.");
            if (int_quntity < 1) throw new IllegalArgumentException("Quantity must be a number greater than 1");
            ArrayList<ProductionRecord> productionRun = new ArrayList<>();
            int itemcount = 0;
            for (ProductionRecord r : productionLog) {
                if (r.getProductID() == getproduct.getId()) {
                    itemcount++;
                }
            }
            for (int i = 0; i < int_quntity; i++) {
                ProductionRecord r = new ProductionRecord(getproduct, itemcount);
                productionRun.add(r);
            }
            DatabaseManager db = new DatabaseManager();
            db.addToProductionDB(productionRun);
            showProduction();
        } catch (IllegalArgumentException ex) {
            ErrorMessage.popUpCreater(ex.getMessage());
        }
    }

    /**
     * used in production log tab to display id, serial num, and date produced thru database.
     */
    public void showProduction() {
        DatabaseManager dm = new DatabaseManager();
        List<ProductionRecord> call = dm.getProductionRecords();
        ProdLogTxtArea.setText("");
        for (ProductionRecord r : call) {
            ProdLogTxtArea.appendText(r.toString() + "\n");
        }
        productionLog.clear();
        productionLog.addAll(dm.getProductionRecords());
    }

    /**
     * handles login button for employees
     * issue 8
     *
     * @param event
     */
    @FXML
    void handle_login(MouseEvent event) {

        //not used as not a part of issue 9

    }


    //database was initialized using Database manager class. this used as reference.

//    /**
//     * method used to connect database to H2 and intelliJ to use for program
//     *
//     * @return stmt returned to be used to initialize database in another method.
//     */
//    public Statement database() {
//        final String JDBC_DRIVER = "org.h2.Driver";
//        final String DB_URL = "jdbc:h2:./res/HR";
//
//        //  Database credentials
//        final String USER = "";
//        final String PASS = "";
//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//            // STEP 1: Register JDBC driver
//            Class.forName(JDBC_DRIVER);
//
//            //STEP 2: Open a connection
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //STEP 3: Execute a query
//            stmt = conn.createStatement();
//
//            return stmt;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}

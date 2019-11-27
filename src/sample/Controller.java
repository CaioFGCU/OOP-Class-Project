package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * This class represents the javaFX code for the GUI with all text fields, tables,
 * boxes, buttons, and tabs.
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

    private ObservableList<Product> productLine;

    Statement statement;

    /**
     * method used to initialize database and comboBox
     */
    @FXML
    void initialize() {
        try{
            DatabaseManager dm = new DatabaseManager();
            productLine = FXCollections.observableArrayList((dm.GetProductsfromDB()));
            List<Product> listOfItem = dm.GetProductsfromDB();
            productLine.addAll(listOfItem);
            Product_List.setItems(productLine);
            Product_Table.setItems(productLine);

            column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
            column_Manuf.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            column_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            column_Type.setCellValueFactory(new PropertyValueFactory<>("type"));



        } catch (Exception e) {
            e.printStackTrace();
        }
        statement = database();
        cboQuantity.getItems().addAll("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10");
        cboQuantity.setEditable(true);
        cboQuantity.getSelectionModel().selectFirst();
        itemtype_choice.getItems().addAll(ItemType.values());

        /**
         * Issue4
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
     * this method gets the input from the user in the GUI text field in the PRODUCT tab
     * and transfers to the database
     *
     * @param event event parameter used for action of mouse click on ADD PRODUCT button
     */
    @FXML
    void handleAddProduct(MouseEvent event) {
        String productName = productNameText.getText();
        String manufacturer = manufacText.getText();
        String SQL = "INSERT INTO Product(type, manufacturer, name) " +
                "VALUES (" + "'"
                + itemtype_choice.getValue().getCode()
                + "'"
                + ","
                + "'"
                + manufacturer
                + "'"
                + ","
                + "'"
                + productName
                + "'"
                + ")";
        //System.out.println(SQL);
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void handle_RecProd(MouseEvent event) {

    }

    /**
     * method used to connect database to H2 and intelliJ to use for program
     *
     * @return stmt returned to be used to initialize database in another method.
     */
    public Statement database() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/HR";

        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();

            return stmt;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

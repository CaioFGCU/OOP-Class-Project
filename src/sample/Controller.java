package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

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
    private ChoiceBox<?> itemtype_choice;

    @FXML
    private Button addButton;

    @FXML
    private ListView<?> Product_List;

    @FXML
    private ComboBox<String> cboQuantity;

    @FXML
    private Button buttonRecProduction;

    Statement statement;

    /**
     * method used to initialize database and comboBox
     */
    @FXML
    void initialize() {
        statement = database();
        cboQuantity.getItems().addAll("1","2","3","4","5","6",
                "7","8","9" ,"10" );
        cboQuantity.setEditable(true);
        cboQuantity.getSelectionModel().selectFirst();


    }

    /**
     * this method gets the input from the user in the GUI text field in the PRODUCT tab
     * and transfers to the database
     * @param event event parameter used for action of mouse click on ADD PRODUCT button
     */
    @FXML
    void handleAddProduct(MouseEvent event) {
        String productName = productNameText.getText();
        String manufacturer = manufacText.getText();
        String SQL = "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO'," + "'" +  manufacturer + "'" + "," + "'" + productName + "'" + ")";
        //System.out.println(SQL);
        try {
            statement.executeUpdate(SQL);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    @FXML
    void handle_RecProd(MouseEvent event) {

    }

    /**
     * method used to connect database to H2 and intelliJ to use for program
     * @return stmt returned to be used to initialize database in another method.
     */
    public Statement database (){
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

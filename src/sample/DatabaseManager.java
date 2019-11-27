package sample;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends Main {
    private Connection con = null;
    public int idOftheProduct;
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //final String JDBC_DRIVER = "org.h2.Driver";
    public DatabaseManager() {
        try {

            /*Properties openFile = new Properties();
            openFile.load(new FileInputStream("res/pass"));
            String user = openFile.getProperty("user");
            String pass = openFile.getProperty("pass");*/
            this.con = DriverManager.getConnection(DB_URL);

        } catch (SQLException exception) {
            exception.printStackTrace();

        }
    }
    public List<Product> GetProductsfromDB() throws SQLException {
        try {
            int id;
            String name;
            String type;
            String manufacturer;
            ResultSet rss = null;
            String query;
            query = "Select * from Product";
            PreparedStatement stmt = con.prepareStatement(query);
            rss = stmt.executeQuery();
            //newListRecord = recordList
            ArrayList<Product> recordList = new ArrayList<>();
            while(rss.next()){
                id = rss.getInt("id");
                name = rss.getString("name");
                type = rss.getString("type");
                manufacturer = rss.getString("manufacturer");
                Product product = new Product(id, name,manufacturer,ItemType.valueOf(type)) {

                    @Override
                    public int getID() {
                        return id;
                    }
                };
                recordList.add(product);

            }
            return recordList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductionRecord> getProductionRecords() throws SQLException{
        try {
            int productionNumber;
            //(this will be unique for every item produced and get auto incremented by the database)
            int productID;
            // (to correspond to the productID from the Product table / class)
            String serialNumber;
            Date dateProduced;
            ResultSet rss = null;
            String query;
            query = "Select * from PRODUCTIONRECORD";
            PreparedStatement stmt = con.prepareStatement(query);
            rss = stmt.executeQuery();
            //newListRecord = recordList
            ArrayList<ProductionRecord> recordList = new ArrayList<>();
            while(rss.next()){
                productionNumber = rss.getInt("Production_Num:");
                productID = rss.getInt("Product_ID:");
                serialNumber = rss.getString("Serial_Num:");
                dateProduced = rss.getDate("Date_Produced");
                ProductionRecord productionRecord = new ProductionRecord(productionNumber,productID,serialNumber,dateProduced);

                /*Product product = new Product(name,manufacturer,ItemType.valueOf(type)) {

                    @Override
                    public int getID() {
                        return 0;
                    }
                };*/
                recordList.add(productionRecord);

            }
            return recordList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }




}

/*
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

 */

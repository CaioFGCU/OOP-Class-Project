package sample;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * class made to setup and initiate database
 * while in other classes any calls or changes to database will be made through this class
 * all classes using database call this class
 */
public class DatabaseManager extends Main {
    private Connection con = null;
    public int idOftheProduct;
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //final String JDBC_DRIVER = "org.h2.Driver";

    /**
     * constructor for DatabaseManager used to get connection "con"
     * also used to reverse string (issue 9)
     */
    public DatabaseManager() {
        try {
            Properties p = new Properties();
            p.load(Main.class.getClassLoader().getResourceAsStream("Properties"));
            p.setProperty("password", reverseString(p.getProperty("password")));

            /*Properties openFile = new Properties();
            openFile.load(new FileInputStream("res/pass"));
            String user = openFile.getProperty("user");
            String pass = openFile.getProperty("pass");*/
            this.con = DriverManager.getConnection(DB_URL, p);

        } catch (SQLException exception) {
            exception.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method used to reverse string for database password
     * "properties" in resource folder will have username and password for database
     *
     * @param pw used for password reversal
     * @return recursion function with password pw
     */
    public String reverseString(final String pw) {
        if (pw.length() == 1) {
            return pw;
        } else {
            return reverseString(pw.substring(1)) + pw.charAt(0);
        }
    }

    /**
     * method used to add into PRODUCTIONRECORD database other tables and then execute
     *
     * @param pr used for a List of type ProductionRecord to access PRODUCTIONRECORD database
     */
    public void addToProductionDB(List<ProductionRecord> pr) {
        try {
            PreparedStatement s = con.prepareStatement("INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) " +
                    "VALUES (?, ?, ?)");
            for (ProductionRecord r : pr) {
                s.setInt(1, r.getProductID());
                s.setString(2, r.getSerialNum());
                s.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                s.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * method adds designated tables into PRODUCT database table
     *
     * @param productName  sets name into PRODUCT table
     * @param manufacturer sets manufacturer into PRODUCT table
     * @param item         of type Itemtype (enum) which gets code from there and sets item into PRODUCT table
     */
    public void addProductToDB(String productName, String manufacturer, ItemType item) {
        String SQL = "INSERT INTO Product(type, manufacturer, name) " +
                "VALUES (?, ?, ?)";
        //System.out.println(SQL);
        try {
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, item.getCode());
            statement.setString(2, manufacturer);
            statement.setString(3, productName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * goes into PRODUCT database and gets name, manufacturer, and type to be used in other parts of the program
     * creates ResultSet to be used to get id, name, and type to be used in record list
     *
     * @return returns record list from database
     * @throws SQLException catches exception for SQL prepare statement.
     */
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
            while (rss.next()) {
                id = rss.getInt("id");
                name = rss.getString("name");
                type = rss.getString("type");
                manufacturer = rss.getString("manufacturer");
                Product product = new Product(id, name, manufacturer, ItemType.getValueofCode(type)) {

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

    /**
     * similar to method above except it goes into PRODUCTIONRECORD database
     *
     * @return same code = return recordlist from production record
     */
    public List<ProductionRecord> getProductionRecords() {
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
            while (rss.next()) {
                productionNumber = rss.getInt("Production_Num");
                productID = rss.getInt("Product_ID");
                serialNumber = rss.getString("Serial_Num");
                dateProduced = rss.getDate("Date_Produced");
                ProductionRecord productionRecord = new ProductionRecord(productionNumber, productID, serialNumber, dateProduced);

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

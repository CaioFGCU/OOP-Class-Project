package sample;

import java.util.Date;

public class ProductionRecord {

    private int productionNumber;
    //(this will be unique for every item produced and get auto incremented by the database)
    private int productID;
    // (to correspond to the productID from the Product table / class)
    private String serialNumber;
    private Date dateProduced;

    ProductionRecord(int productID){
        productionNumber = 0;
        serialNumber = "0";
        dateProduced = new Date();
        this.productID = productID;
    }

    ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced){
        this.productionNumber = productionNumber;
        this.productID = productID;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }

    ProductionRecord(Product product, int itemCount){
        serialNumber = product.manufacturer.substring(0,3) + product.type.getCode() + String.format("%05D",itemCount);
    }

    @Override
    public String toString(){
        return "Production Number: " + productionNumber + " Product ID: " + productID
                 + " Serial Number: " + serialNumber + " Date: " + dateProduced;
    }

    public int getProductionNum() {
        return productionNumber;
    }

    public void setProductionNum(int productionNumber) {
        this.productionNumber = productionNumber;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSerialNum() {
        return serialNumber;
    }

    public void setSerialNum(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getProdDate() {
        return dateProduced;
    }

    public void setProdDate(Date dateProduced) {
        this.dateProduced = dateProduced;
    }
}

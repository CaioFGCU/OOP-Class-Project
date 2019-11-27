package sample;

/**
 * Product class is an abstract class and implements Item interface to get contents within Item.
 */
public abstract class Product implements Item {
    int id;
    ItemType type;
    String manufacturer;
    String name;

    /**
     * Constructor used for declared variables above
     *
     * @param name         parameter used to get name from within class to be used
     * @param manufacturer parameter used to get manufacturer from within class
     * @param type         parameter used to get type from within class
     */
    Product(String name, String manufacturer, ItemType type) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    Product(int id, String name, String manufacturer, ItemType type) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }



    public int getId() {
        return id;
    }


    public ItemType getType() {
        return type;
    }


    public String getManufacturer() {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ("Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type);
    }

}

package sample;

/**
 * item interface to get name, id, and manufacturer
 * sets name and manufacturer
 */
public interface Item {

    int getID();

    void setName(String name);

    String getName();

    void setManufacturer(String manufact);

    String getManufacturer();


}


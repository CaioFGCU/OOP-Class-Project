package sample;

/**
 * Widget class extends Product abstract to get whatever is within Product.
 * Widget is purely used to test Product class.
 */
public class Widget extends Product {
    Widget(String name, String manufacturer, ItemType type) {
        super(name, manufacturer, type);
    }

    @Override
    public int getID() {
        return 0;
    }
}

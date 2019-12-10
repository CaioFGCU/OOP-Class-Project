package sample;

/**
 * enum to get type of item
 * used in itembox in first tab of GUI
 */
public enum ItemType {
    AUDIO("AU"),
    VISUAL("VI"),
    AUDIO_MOBILE("AM"),
    VISUAL_MOBILE("VM");

    private String code;

    ItemType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * gets type of Itemtype (used in GetProductsfromDB in DatabaseManager)
     * @param type used for type of Item
     * @return typeenum looped through to get type of item
     */
    public static ItemType getValueofCode(String type) {

        ItemType typeenum = null;
        for (ItemType t : ItemType.values()) {
            if (t.getCode().equalsIgnoreCase(type)) {
                typeenum = t;
            }
        }
        if (typeenum == null) {
            throw new IllegalArgumentException();
        }
        return typeenum;
    }
}

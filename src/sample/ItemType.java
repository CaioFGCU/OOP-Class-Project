package sample;

public enum ItemType {
    AUDIO("AU"),
    VISUAL("VI"),
    AUDIO_MOBILE("AM"),
    VISUAL_MOBILE("VM");

    private String code;

    ItemType(String code) {
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public static ItemType getValueofCode(String type){

        ItemType typeenum = null;
        for (ItemType t: ItemType.values()){
            if (t.getCode().equalsIgnoreCase(type)){
                typeenum = t;
            }
        }
        if (typeenum == null){
            throw new IllegalArgumentException();
        }
        return typeenum;
    }
}

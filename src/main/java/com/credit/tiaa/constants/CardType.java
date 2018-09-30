package com.credit.tiaa.constants;

public enum CardType {

    VISA("4","13"),
    MASTER("5","16"),
    AMERICAN_EXPRESS("37","15"),
    DISCOVER("6","16");

    private String strtDigit;
    private String length;

    CardType(String strtDigit, String length) {
        this.strtDigit = strtDigit;
        this.length = length;
    }

    public String getStrtDigit() {
        return strtDigit;
    }

    public String getLength() {
        return length;
    }

    public CardType getValue(String ccType){
        if("VISA".equalsIgnoreCase(ccType)){
            return VISA;
        }else if("MASTER".equalsIgnoreCase(ccType)){
            return MASTER;
        }else if("AMERICAN_EXPRESS".equalsIgnoreCase(ccType)){
            return AMERICAN_EXPRESS;
        }else if ("DISCOVER".equalsIgnoreCase(ccType)){
            return DISCOVER;
        }else{
            throw new IllegalArgumentException("Invalid card type");
        }
    }
}

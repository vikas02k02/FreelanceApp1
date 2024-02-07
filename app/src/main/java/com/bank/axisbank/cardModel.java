package com.bank.axisbank;

public class cardModel {
    private String card_Holder_Name ;
    private String Card_Number ;
    private String Expiry_Month ;
    private String Expiry_Year ;
    private String CVV;

    public cardModel(String cardHolderName, String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        card_Holder_Name = cardHolderName;
        Card_Number = cardNumber;
        Expiry_Month = expiryMonth;
        Expiry_Year = expiryYear;
        CVV = cvv;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getExpiry_Year() {
        return Expiry_Year;
    }

    public void setExpiry_Year(String expiry_Year) {
        Expiry_Year = expiry_Year;
    }

    public String getExpiry_Month() {
        return Expiry_Month;
    }

    public void setExpiry_Month(String expiry_Month) {
        Expiry_Month = expiry_Month;
    }

    public String getCard_Number() {
        return Card_Number;
    }

    public void setCard_Number(String card_Number) {
        Card_Number = card_Number;
    }

    public String getCard_Holder_Name() {
        return card_Holder_Name;
    }

    public void setCard_Holder_Name(String card_Holder_Name) {
        this.card_Holder_Name = card_Holder_Name;
    }
}

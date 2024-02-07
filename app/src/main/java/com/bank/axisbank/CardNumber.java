package com.bank.axisbank;

public class CardNumber {
    private   static String Card_Number;
    public CardNumber(String card){
        Card_Number=card;
    }

    public static String getCard_Number() {
        return Card_Number;
    }

    public static void setCard_Number(String card_Number) {
        Card_Number = card_Number;
    }
}

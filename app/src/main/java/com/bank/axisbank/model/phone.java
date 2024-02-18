package com.bank.axisbank.model;

public class phone{
    private static String Phone;
    public  phone(String phoneNum){
        Phone=phoneNum;
    }

    public static String getPhone() {
        return Phone;
    }

    public static void setPhone(String phone) {
        Phone = phone;
    }
}

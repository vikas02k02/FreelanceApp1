package com.bank.axisbank;

public class UserData {
    private String PhoneNumber;
    private String Name;
    private String Email;
    private String Date_of_Birth;
    private String PAN;
    private String Aadhar;
    private String PermanentAddress;
    private String CurrentAddress;
    private String Country;
    private String State;
    private String Pincode;
    private String AnnualIncome;
    private String Bank;


    public UserData(String phoneNumber, String name, String email, String Dob ,String pan, String aadhar, String permanentAddress, String currentAddress, String country, String state, String pincode, String annualIncome, String bank) {
        PhoneNumber = phoneNumber;
        Name = name;
        Email = email;
        Date_of_Birth=Dob;
        PAN = pan;
        Aadhar = aadhar;
        PermanentAddress = permanentAddress;
        CurrentAddress = currentAddress;
        Country = country;
        State = state;
        Pincode = pincode;
        AnnualIncome = annualIncome;
        Bank = bank;
    }

    public String getAnnualIncome() {
        return AnnualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        AnnualIncome = annualIncome;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCurrentAddress() {
        return CurrentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        CurrentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return PermanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        PermanentAddress = permanentAddress;
    }

    public String getAadhar() {
        return Aadhar;
    }

    public void setAadhar(String aadhar) {
        Aadhar = aadhar;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        Date_of_Birth = date_of_Birth;
    }
}

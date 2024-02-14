package com.example.civicgrievance;

public class Complaints {
    String Problems;
    String Address;
    String MobileNumber;
    String Description;
    public Complaints(){

    }
    public Complaints(String Problems, String Address, String MobileNumber, String Description){
        this.Address=Address;
        this.MobileNumber=MobileNumber;
        this.Description=Description;
    }
    public String getProblems(){
        return Problems;}

    public String getAddress(){
        return Address;
    }
    public String getDescription(){
        return Description;
    }
    public void setProblems(){
        this.Problems=Problems;
    }
    public void setDescription(){
        this.Description=Description;
    }
    public String getMobileNumber(){
        return MobileNumber;
    }
    public void setAddress(){
        this.Address=Address;

    }
    public void setMobileNumber(){
        this.MobileNumber=MobileNumber;

    }
}

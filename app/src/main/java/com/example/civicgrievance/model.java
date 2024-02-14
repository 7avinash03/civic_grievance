package com.example.civicgrievance;

public class model {
    String Address,Description,Mobile_Number,Problem,Image;

    public model() {
    }

    public model(String address, String description, String mobile_Number, String problem, String image) {
        this.Address = address;
        this.Description = description;
        this.Mobile_Number = mobile_Number;
        this.Problem = problem;
        this.Image = image;
    }

    public String getAddress() {
        return Address;
    }

    public String getDescription() {
        return Description;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public String getProblem() {
        return Problem;
    }

    public String getImage() {
        return Image;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    public void setImage(String image) {
        Image = image;
    }
}

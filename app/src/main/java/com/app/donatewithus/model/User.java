package com.app.donatewithus.model;

public class User {
    private String userName;
    private String userEmail;
    private String userContactNumber;
    private String userAddress;

    public User() {
    }

    public User(String userName, String userEmail, String userContactNumber) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userContactNumber = userContactNumber;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContactNumber() {
        return this.userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
    }
}

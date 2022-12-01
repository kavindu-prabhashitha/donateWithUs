package com.app.donatewithus.model;

public class Club {

    private String clubName;
    private String clubCode;
    private String clubEmail;
    private String clubLocation;
    private String clubContactNumber;

    public Club(){

    }

    public Club(String clubName, String clubCode, String clubEmail, String clubLocation) {
        this.clubName = clubName;
        this.clubCode = clubCode;
        this.clubEmail = clubEmail;
        this.clubLocation = clubLocation;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubCode() {
        return this.clubCode;
    }

    public void setClubCode(String clubCode) {
        this.clubCode = clubCode;
    }

    public String getClubEmail() {
        return this.clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getClubLocation() {
        return this.clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getClubContactNumber() {
        return this.clubContactNumber;
    }

    public void setClubContactNumber(String clubContactNumber) {
        this.clubContactNumber = clubContactNumber;
    }
}

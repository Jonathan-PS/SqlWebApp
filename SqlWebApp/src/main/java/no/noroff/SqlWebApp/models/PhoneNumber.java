package no.noroff.SqlWebApp.models;

public class PhoneNumber {
    private final int pID;
    private final int pnID;
    private String phoneCategory;
    private int phoneNumber;

    //Constructor
    public PhoneNumber (int pID, int pnID, String phoneCategory, int phoneNumber) {
        this.pID = pID;
        this.pnID= pnID;
        this.phoneCategory = phoneCategory;
        this.phoneNumber = phoneNumber;
    }

    /* GETTERS */
    public int getpID() {
        return pID;
    }
    public int getPnID () {
        return(pnID);
    }
    public int getPhoneNumber() {
        return (phoneNumber);
    }
    public String getPhoneCategory() {
        return phoneCategory;
    }

    /* SETTERS */
    public void setPhoneCategory(String phoneCategory) {
        this.phoneCategory = phoneCategory;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

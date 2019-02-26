package no.noroff.SqlWebApp.models;

public class PhoneNumber {
    private final int pID;
    private final int pnID;
    private String phoneCategory;
    private String phoneNumber;

    //Constructor
    public PhoneNumber (int pID, int pnID, String phoneCategory, String phoneNumber) {
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
    public String getPhoneNumber() {
        return (phoneNumber);
    }
    public String getPhoneCategory() {
        return phoneCategory;
    }

    /* SETTERS */
    public void setPhoneCategory(String phoneCategory) {
        this.phoneCategory = phoneCategory;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

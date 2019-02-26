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

    public String getPhoneNumber() {
        return (phoneNumber);
    }

    public int getPnID () {
        return(pnID);
    }
}

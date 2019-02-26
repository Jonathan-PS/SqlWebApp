package no.noroff.SqlWebApp;

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

    public int getPhoneNumber() {
        return (phoneNumber);
    }

    public int getPnID () {
        return(pnID);
    }
}

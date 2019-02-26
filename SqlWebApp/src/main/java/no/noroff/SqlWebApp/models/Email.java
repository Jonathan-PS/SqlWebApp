package no.noroff.SqlWebApp.models;

public class Email {
    private final int pID;
    private final int eID;
    private String emailCategory;
    private String email;

    //Constructor
    public Email (int pID, int eID, String emailCategory, String email) {
        this.pID = pID;
        this.eID = eID;
        this.emailCategory = emailCategory;
        this.email = email;
    }

    public String getEmail() {
        return (email);
    }

    public int getEID() {
        return (eID);
    }


}

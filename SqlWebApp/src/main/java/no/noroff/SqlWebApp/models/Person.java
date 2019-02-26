package no.noroff.SqlWebApp.models;


import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private final int pID; // Never to be changed, thus final.
    private String firstName;
    private String lastName;
    private String homeAddress;
    private Date dateOfBirth;

    // TODO: Populate the following maps
    private Map<String, String> phoneNumberMap = new HashMap<>();
    private Map<String, String> emailMap = new HashMap<>();

    public Person(int pID, String firstName, String lastName, String homeAddress, Date dateOfBirth){
        this.pID = pID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;
    }


    /* GETTERS */
    public int getpID() {
        return pID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getName() {
        return firstName + " " + lastName;
    }
    public Date getBirthDate() {return dateOfBirth;}
    public String getHomeAddress() {return homeAddress;}

    /* SETTERS */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

package no.noroff.SqlWebApp.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static no.noroff.SqlWebApp.SqlWebApplication.sqlConn;

public class Person {
    private final int pID; // Never to be changed, thus final.
    private String firstName;
    private String lastName;
    private String homeAddress;
    private Date dateOfBirth;

    private Map<String, String> phoneNumbers = new HashMap<>();
    private Map<String, String> emails = new HashMap<>();
    private Map<String, Integer> relations = new HashMap<>();


    public Person(int pID, String firstName, String lastName, String homeAddress, Date dateOfBirth){
        this.pID = pID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;

        setEmails();
        setPhoneNumbers();
        theRelations();
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
    public String getHomeAddress() {return homeAddress;}
    public Map<String, String> getEmails() {
        return emails;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public Map<String, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Map<String, Integer> getRelations() {
        return relations;
    }

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

    private void setEmails() {
        ArrayList<Email> emailList = sqlConn.selectEmailList(pID);
        for(Email email: emailList){
            emails.put(email.getEmailCategory(), email.getEmail());
        }
    }

    private void setPhoneNumbers() {
        ArrayList<PhoneNumber> phoneNumberList = sqlConn.selectPhoneNumberList(pID);
        for (PhoneNumber number: phoneNumberList) {
            phoneNumbers.put(number.getPhoneCategory(), number.getPhoneNumber());
        }
    }


    private void theRelations() {
        ArrayList<Relationship> allRelationships = sqlConn.selectPersonalRelationship(pID);
        for (Relationship relation : allRelationships) {
            if (pID == relation.getP1()) {
                relations.put(relation.getP1p2(),
                        relation.getP2());
            } else if (pID == relation.getP2()) {
                relations.put(relation.getP2p1(),
                        relation.getP1());

            }
        }

    }

}

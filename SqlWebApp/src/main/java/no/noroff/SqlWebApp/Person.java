package no.noroff.SqlWebApp;


/*CREATE TABLE Persons (
    pID int NOT NULL AUTO_INCREMENT,
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    HomeAddress varchar(255),
    DateOfBirth Date,
    PRIMARY KEY(pID)
    );*/


import java.sql.Date;

public class Person {
    private final int pID; // Never to be changed, thus final.
    private String firstName;
    private String lastName;
    private String homeAddress;
    private Date dateOfBirth;

    // TODO:
    private int[] phoneNumbers;
    private String[] emails;

    public Person(int pID, String firstName, String lastName, String homeAddress, Date dateOfBirth){
        this.pID = pID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}

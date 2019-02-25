package no.noroff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class TheSqlConnection {

    public Connection connect() {
        Connection conn = null;

        try {
            // db parameters
            String url = "jdbc:sqlite::resource:HappyFamily.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /* TABLE INITIALIZERS */
    public void initPersons(){
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table
    }

    public void initPhoneNumbers(){
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table
    }

    public void initEmails(){
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table
    }

    public void initRelationships(){
        // Checks whether table  exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table
    }

    /* INSERT METHODS */
    public int insertPerson(String firstName, String lastName, String homeAdreses, LocalDate dateOfBirth) {
        // Inserts given person into table Person
        // returns pID
        return -1;
    }

    public int insertPhoneNumber(int pID, PhoneCategories pCategory, int phoneNumber) {
        // Inserts given phone number into table PhoneNumbers
        // returns pnID
        return -1;
    }

    // INSERT INTO EMAILS

    // INSERT INTO RELATIONSHIPS



    public void getPIDByName(String firstName, String lastName) {
        // prints out ids of all persons with given name
    }



}

package no.noroff;


import java.sql.*;

import java.time.LocalDate;

public class TheSqlConnection {
    Connection conn = null;

    public void connect() {
        String url = "jdbc:sqlite:src/main/resources/HappyFamily.sqlite";
        //String url = "jdbc:sqlite::resource:HappyFamily.sqlite";

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /* TABLE INITIALIZERS */

    public void initAllTables() {
        initPersons();
        initPhoneNumbers();
        initEmails();
        initRelationships();
    }

    public void initPersons() {
        //Connection conn = connect();
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table

        String createStatement = "CREATE TABLE Persons (\n" +
                "    pID int NOT NULL,\n" +
                "    FirstName varchar(255) NOT NULL,\n" +
                "    LastName varchar(255) NOT NULL,\n" +
                "    HomeAddress varchar(255),\n" +
                "    DateOfBirth Date,\n" +
                "    PRIMARY KEY(pID)\n" +
                "    );\n" +
                "\n";

        try ( PreparedStatement pstmt = conn.prepareStatement(createStatement)){
            pstmt.execute();
            System.out.println("Table Persons created");
            // Insert all initial elements

        } catch (SQLException E) {
            System.out.println("Persons table creation statement failed. Maybe it already exists");
        }

    }

    public void initPhoneNumbers(){
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table


        String createStatement = "CREATE TABLE PhoneNumbers (\n" +
                "        pnID int NOT NULL,\n" +
                "        pID int NOT NULL,\n" +
                "        PhoneCategory varchar(255),\n" +
                "        Number int,\n" +
                "        PRIMARY KEY(pnID),\n" +
                "        FOREIGN KEY(pID) REFERENCES Persons(pID)\n" +
                "    )";

        try ( PreparedStatement pstmt = conn.prepareStatement(createStatement)){
            pstmt.execute();
            System.out.println("Table PhoneNumbers created");
        } catch (SQLException E) {
            System.out.println("PhoneNumbers table creation statement failed");
        }
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

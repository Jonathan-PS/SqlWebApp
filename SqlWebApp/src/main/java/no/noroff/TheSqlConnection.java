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
                "    pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    FirstName varchar(255) NOT NULL,\n" +
                "    LastName varchar(255) NOT NULL,\n" +
                "    HomeAddress varchar(255),\n" +
                "    DateOfBirth Date\n" +
                "    );\n" +
                "\n";

        try ( PreparedStatement pstmt = conn.prepareStatement(createStatement)){
            pstmt.execute();
            System.out.println("Table Persons created");
            // Insert all initial elements

        } catch (SQLException E) {
            System.out.println("Persons table creation statement failed. Maybe it already exists.");
        }

    }

    public void initPhoneNumbers(){
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table
        //TODO: constraints on number


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
            System.out.println("PhoneNumbers table creation statement failed. Maybe it already exists");
        }
    }

    public void initEmails(){
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table


        String createStatement = "CREATE TABLE Emails (\n" +
                "    eID int NOT NULL,\n" +
                "    pID int NOT NULL,\n" +
                "    EmailCategory varchar(255),\n" +
                "    Email varchar(255) NOT NULL,\n" +
                "    PRIMARY KEY(eID),\n" +
                "    FOREIGN KEY(pID) REFERENCES Persons(pID)\n" +
                "    )";

        try ( PreparedStatement pstmt = conn.prepareStatement(createStatement)){
            pstmt.execute();
            System.out.println("Table Emails created");
        } catch (SQLException E) {
            System.out.println("Emails table creation statement failed. Maybe it already exists.");
        }

    }

    public void initRelationships(){
        // Checks whether table  exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table


        String createStatement = "CREATE TABLE Relationships (\n" +
                "    rID int NOT NULL,\n" +
                "    p1 int NOT NULL,\n" +
                "    p2 int NOT NULL,\n" +
                "    p1p2 varchar(255) NOT NULL,\n" +
                "    p2p1 varchar(255) NOT NULL,\n" +
                "    PRIMARY KEY(rID),\n" +
                "    FOREIGN KEY(p1) REFERENCES Persons(pID),\n" +
                "    FOREIGN KEY(p2) REFERENCES Persons(pID)\n" +
                "    )";

        try ( PreparedStatement pstmt = conn.prepareStatement(createStatement)){
            pstmt.execute();
            System.out.println("Table Relationship created");
        } catch (SQLException E) {
            System.out.println("Relationships table creation statement failed");
        }
    }

    /* INSERT METHODS */
    public int insertPerson(String firstName, String lastName, String homeAddress, LocalDate dateOfBirth) {
        // Inserts given person into table Person
        // returns pID
        //String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
        String sql = "INSERT INTO Persons(FirstName, LastName, HomeAddress, DateOfBirth) VALUES(?,?,?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, homeAddress);
                pstmt.setDate(4, java.sql.Date.valueOf(dateOfBirth));
                pstmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return -1;
    }

    public int insertPhoneNumber(PhoneCategories pCategory, int phoneNumber) {
        // Inserts given phone number into table PhoneNumbers
        // returns pnID
        String sql = "INSERT INTO PhoneNumbers(pCategory, phoneNumber) VALUES(?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, pCategory.toString().toLowerCase());
                pstmt.setInt(2, phoneNumber);
                pstmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return -1;
    }

    // INSERT INTO EMAILS
    public int insertEmails(EmailCategories emailCategorie, String email) {
        // Inserts given phone number into table PhoneNumbers
        // returns pnID
        String sql = "INSERT INTO Emails(EmailCategory, Email) VALUES(?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, emailCategorie.toString().toLowerCase());
                pstmt.setString(2, email);
                pstmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return -1;
    }
    // INSERT INTO RELATIONSHIPS
    public int insertRelationship(String rel1, String rel2){
        String sql = "INSERT INTO Relationship(p1p2, p2p1) VALUES(?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, rel1);
                pstmt.setString(2, rel2);
                pstmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return -1;
    }


    public void getPIDByName(String firstName, String lastName) {
        // prints out ids of all persons with given name
    }



}

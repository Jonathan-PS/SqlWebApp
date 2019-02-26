package no.noroff.SqlWebApp;

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

    //Close the connection
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
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
                "        pnID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "        pID int NOT NULL,\n" +
                "        PhoneCategory varchar(255),\n" +
                "        Number int,\n" +
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
                "    eID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    pID int NOT NULL,\n" +
                "    EmailCategory varchar(255),\n" +
                "    Email varchar(255) NOT NULL,\n" +
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
                "    rID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    p1 int NOT NULL,\n" +
                "    p2 int NOT NULL,\n" +
                "    p1p2 varchar(255) NOT NULL,\n" +
                "    p2p1 varchar(255) NOT NULL,\n" +
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
        //TODO:DATEOFBIRTH
        String sql = "INSERT INTO Persons(FirstName, LastName, HomeAddress, DateOfBirth) VALUES(?,?,?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, homeAddress);
                pstmt.setDate(4, Date.valueOf(dateOfBirth));
                pstmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return -1;
    }

    public int insertPhoneNumber(int pID, PhoneCategories pCategory, int phoneNumber) {
        // Inserts given phone number into table PhoneNumbers
        // returns pnID
        String sql = "INSERT INTO PhoneNumbers(pID, PhoneCategory, Number) VALUES(?,?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, pID);
                pstmt.setString(2, pCategory.toString().toLowerCase());
                pstmt.setInt(3, phoneNumber);
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
    public int insertEmails(int pID, EmailCategories emailCategory, String email) {
        // Inserts given phone number into table PhoneNumbers
        // returns pnID
        String sql = "INSERT INTO Emails(pID, EmailCategory, Email) VALUES(?,?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, pID);
                pstmt.setString(2, emailCategory.toString().toLowerCase());
                pstmt.setString(3, email);
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
    public int insertRelationship(int p1, int p2, String rel1, String rel2){
        String sql = "INSERT INTO Relationships(p1, p2, p1p2, p2p1) VALUES(?,?,?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,p1);
                pstmt.setInt(2, p2);
                pstmt.setString(3, rel1);
                pstmt.setString(4, rel2);
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

    public Person selectPerson(int pID){
        String sql = "SELECT * FROM Persons WHERE pID = (?)";

        Person person = null;

        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, pID);
                ResultSet rs = pstmt.executeQuery();

                person = new Person(rs.getInt("pID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("HomeAddress"),
                            rs.getDate("DateOfBirth"));
            }
        } catch (SQLException e) {
            System.out.println("Person (pID=" + pID + ") SELECT not working.");
            System.out.println(e.getMessage());
        }

        return person;
    }

    public Email selectEmail (int pID) {
        String sql = "SELECT * FROM Emails WHERE pID = (?)";
        Email email = null;

        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, pID);
                ResultSet rs = pstmt.executeQuery();

                email = new Email(rs.getInt("pID"),
                        rs.getInt("eID"),
                        rs.getString("EmailCategory"),
                        rs.getString("Email"));
            }
        } catch (SQLException exc) {
            System.out.println("Email (pID=" + pID + ") SELECT not working.");
            System.out.println(exc.getMessage());
        }

        return email;

    }

    public PhoneNumber selectPhoneNumber (int pID) {
        String sql = "SELECT * FROM PhoneNumbers WHERE pID = (?)";
        PhoneNumber phoneNumber = null;

        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, pID);
                ResultSet rs = pstmt.executeQuery();

                phoneNumber = new PhoneNumber(rs.getInt("pID"),
                        rs.getInt("pnID"),
                        rs.getString("PhoneCategory"),
                        rs.getInt("Number"));
            }
        } catch (SQLException exc) {
            System.out.println("Phone number (pID=" + pID + ") SELECT not working.");
            System.out.println(exc.getMessage());
        }

        return phoneNumber;

        /* */

    }

    public Relationship selectRelationship (int p1) {
        String sql = "SELECT * FROM Relationships WHERE pID = (?)";
        Relationship relationship = null;

        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, p1);
                ResultSet rs = pstmt.executeQuery();

                relationship = new Relationship(rs.getInt("rID"),
                        rs.getInt("p1"),
                        rs.getInt("p2"),
                        rs.getString("p1p2"),
                        rs.getString("p2p1"));
            }
        } catch (SQLException exc) {
            System.out.println("Relationship (pID=" + p1 + ") SELECT not working.");
            System.out.println(exc.getMessage());
        }

        return relationship;

        /* "CREATE TABLE Relationships (\n" +
                "    rID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    p1 int NOT NULL,\n" +
                "    p2 int NOT NULL,\n" +
                "    p1p2 varchar(255) NOT NULL,\n" +
                "    p2p1 varchar(255) NOT NULL,\n" +
                "    FOREIGN KEY(p1) REFERENCES Persons(pID),\n" +
                "    FOREIGN KEY(p2) REFERENCES Persons(pID)\n" +
                "    )";*/

    }

}

package no.noroff;

import java.sql.*;
import java.time.LocalDate;

public class TheSqlConnection {
    Connection conn = null;
    public void connect() {
        String url = "jdbc:sqlite:./src/main/resources/HappyFamily.sqlite";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
        //String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
        String sql = "INSERT INTO Persons(FirstName, LastName, HomeAdress, DateOfBirth) VALUES(?,?,?,?)";
        try{
            if(conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, homeAdreses);
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

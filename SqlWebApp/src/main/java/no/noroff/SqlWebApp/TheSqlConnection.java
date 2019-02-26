package no.noroff.SqlWebApp;

import no.noroff.SqlWebApp.enumerators.EmailCategories;
import no.noroff.SqlWebApp.enumerators.PhoneCategories;
import no.noroff.SqlWebApp.models.Email;
import no.noroff.SqlWebApp.models.Person;
import no.noroff.SqlWebApp.models.PhoneNumber;
import no.noroff.SqlWebApp.models.Relationship;

import java.sql.*;
import java.time.LocalDate;

public class TheSqlConnection {
    Connection conn = null;

    // Data for constructing our initial sqlite database
    String[] firstName = {"Leon", "Eliot", "Malak", "Katya", "Tobias", "Natalya", "Kelise", "Cieran", "Duke", "Lilly"};
    String[] lastName = {"Greig", "Villarreal", "Payne", "Whitfield", "Oliver", "Pace", "Sheldon", "Young", "Gray", "Hawkins"};
    String[] homeAddress = {"27 Shelby Ave", "8705 Kevin Ln", "24 South St", "5435 Louise Ave", "5414 County 150 Rd",
            "407 Carolina St", "26 9th Ave", "405 Sunrise Ave", "6 Buttonball Dr", "911 Clare Ave"};
    LocalDate[] dateOfBirth = { LocalDate.of(1980,01,1), LocalDate.of(1960,03,6),
            LocalDate.of(1980,06,7), LocalDate.of(2000,12,3),
            LocalDate.of(2010,07,21), LocalDate.of(1991,11,14),
            LocalDate.of(2001,03,30), LocalDate.of(1920,10,2),
            LocalDate.of(1999,04,12), LocalDate.of(1954,06,13)};
    String[] phoneNumbers = {"12345678", "23456789", "34567890", "45678901", "56789012", "67890123", "78901234", "89012345", "90123456", "12345670"};
    String[] emails = new String[10];
    {
        for (int i = 0; i < firstName.length; i++) {
            emails[i] = firstName[i] + "." + lastName[i] + "@craigmail.com";
        }
    }



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


        try (PreparedStatement pstmt = conn.prepareStatement(createStatement)) {
            pstmt.execute();
            System.out.println("Table Persons created");

            // FILL TABLE
            for (int i = 0; i < firstName.length; i++) {
                insertPerson(firstName[i], lastName[i], homeAddress[i], dateOfBirth[i]);
            }

        } catch (SQLException e) {
            System.out.println("Persons table creation statement failed. Maybe it already exists.");
        }

    }

    public void initPhoneNumbers() {
        // Checks whether table exists
        // if it doesn't exist to the following
        // 1. Create the table
        // 2. Fill the table
        //TODO: constraints on number


        String createStatement = "CREATE TABLE PhoneNumbers (\n" +
                "        pnID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "        pID int NOT NULL,\n" +
                "        PhoneCategory varchar(255),\n" +
                "        Number varchar(8),\n" +
                "        FOREIGN KEY(pID) REFERENCES Persons(pID)\n" +
                "    )";

        try ( PreparedStatement pstmt = conn.prepareStatement(createStatement)){
            // CREATE TABLE
            pstmt.execute();
            System.out.println("Table PhoneNumbers created");

            // FILL TABLE
            for (int i = 0; i < firstName.length; i++) {
                insertPhoneNumber(i+1, PhoneCategories.MOBILE, phoneNumbers[i]);
            }


        } catch (SQLException E) {
            System.out.println("PhoneNumbers table creation statement failed. Maybe it already exists");
        }
    }

    public void initEmails() {
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
            // CREATE TABLE
            pstmt.execute();
            System.out.println("Table Emails created");

            // FILL TABLE
            for (int i = 0; i < firstName.length; i++) {
                insertEmails(i+1, EmailCategories.PERSONAL, emails[i]);
            }

        } catch (SQLException E) {
            System.out.println("Emails table creation statement failed. Maybe it already exists.");
        }

    }

    public void initRelationships() {
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
            // CREATE TABLE
            pstmt.execute();
            System.out.println("Table Relationship created");

            // FILL TABLE
            insertRelationship(1,2,"Brother","Brother");
            insertRelationship(2, 1, "Brother", "Brother");
            insertRelationship(4,5,"Sister","Brother");
            insertRelationship(5, 4, "Brother", "Sister");
            insertRelationship(8,10,"Father","Daughter");
            insertRelationship(10, 8, "Daughter", "Father");
            insertRelationship(10, 9, "Mother", "Son" );
            insertRelationship(9, 10, "Son", "Mother");
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
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, homeAddress);
                pstmt.setObject(4, java.sql.Date.valueOf(dateOfBirth));
                pstmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return -1;
    }

    public int insertPhoneNumber(int pID, PhoneCategories pCategory, String phoneNumber) {
        // Inserts given phone number into table PhoneNumbers
        // returns pnID
        String sql = "INSERT INTO PhoneNumbers(pID, PhoneCategory, Number) VALUES(?,?,?)";
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, pID);
                pstmt.setString(2, pCategory.toString().toLowerCase());
                pstmt.setString(3, phoneNumber);
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
        try {
            if (conn != null) {
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
    public int insertRelationship(int p1, int p2, String rel1, String rel2) {
        String sql = "INSERT INTO Relationships(p1, p2, p1p2, p2p1) VALUES(?,?,?,?)";
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, p1);
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

    public Person selectPerson(int pID) {
        String sql = "SELECT * FROM Persons WHERE pID = (?)";

        Person person = null;

        try {
            if (conn != null) {
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
                        rs.getString("Number"));
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

  public void updatePerson(int pId, String attributeName, String value) {
        String updateSql = String.format("UPDATE Persons SET %s =? WHERE pID=?", attributeName);
        PreparedStatement uStmt = null;
        try {
            uStmt = conn.prepareStatement(updateSql);
            uStmt.setString(1, attributeName);
            uStmt.setString(1, value);
            uStmt.setInt(2, pId);


        boolean autoCommit = conn.getAutoCommit();
        try {
            conn.setAutoCommit(false);
            uStmt.executeUpdate();
        } catch (SQLException exc) {
            conn.rollback();
        } finally {
            conn.setAutoCommit((autoCommit));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}

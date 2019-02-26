package no.noroff.SqlWebApp;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        
        String[] firstName = {"Leon", "Eliot", "Malak", "Katya", "Tobias", "Natalya", "Kelise", "Cieran", "Duke", "Lilly"};
        String[] lastName = {"Greig", "Villarreal", "Payne", "Whitfield", "Oliver", "Pace", "Sheldon", "Young", "Gray", "Hawkins"};
        String[] homeAddress = {"27 Shelby Ave", "8705 Kevin Ln", "24 South St", "5435 Louise Ave", "5414 County 150 Rd",
                                "407 Carolina St", "26 9th Ave", "405 Sunrise Ave", "6 Buttonball Dr", "911 Clare Ave"};
        LocalDate[] dateOfBirth = { LocalDate.of(1980,01,1), LocalDate.of(1960,03,6),
                                    LocalDate.of(1980,06,7), LocalDate.of(2000,12,3),
                                    LocalDate.of(2010,07,21), LocalDate.of(1991,11,14),
                                    LocalDate.of(2001,03,30), LocalDate.of(1920,10,2),
                                    LocalDate.of(1999,04,12), LocalDate.of(1954,06,13)};


        //System.out.println("Datevalue: " + dateOfBirth[1]);
        String[] emails = new String[10];
        for (int i = 0; i < firstName.length; i++) {
            emails[i] = firstName[i] + "." + lastName[i] + "@craigmail.com";
        }

        int[] phoneNumbers = {12345678, 23456789, 34567890, 45678901, 56789012, 67890123, 78901234, 89012345, 90123456, 12345670};


        TheSqlConnection conn = new TheSqlConnection();

        conn.connect();
        conn.initAllTables();
        for (int i = 0; i < firstName.length; i++) {
            //insertPerson(String firstName, String lastName, String homeAddress, LocalDate dateOfBirth)
            int outputP = conn.insertPerson(firstName[i], lastName[i], homeAddress[i], dateOfBirth[i]);
            int outputE = conn.insertEmails(i+1, EmailCategories.PERSONAL, emails[i]);
            int outputPN = conn.insertPhoneNumber(i+1, PhoneCategories.MOBILE, phoneNumbers[i]);
        }

        conn.insertRelationship(1,2,"Brother","Brother");
        conn.insertRelationship(4,5,"Sister","Brother");
        conn.insertRelationship(8,10,"Father","Daughter");


        // SELECT PERSON TESTING
        Person person1 = conn.selectPerson(1);
        Person person2 = conn.selectPerson(2);

        System.out.println(person1.getName());
        System.out.println(person2.getName());

    }
}

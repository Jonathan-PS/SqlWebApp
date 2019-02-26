package no.noroff.SqlWebApp;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

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
        conn.insertRelationship(2, 1, "Brother", "Brother");
        conn.insertRelationship(4,5,"Sister","Brother");
        conn.insertRelationship(5, 4, "Brother", "Sister");
        conn.insertRelationship(8,10,"Father","Daughter");
        conn.insertRelationship(10, 8, "Daughter", "Father");
        conn.insertRelationship(10, 9, "Mother", "Son" );
        conn.insertRelationship(9, 19, "Son", "Mother");


        // SELECT PERSON TESTING
        Person person1 = conn.selectPerson(1);
        Person person2 = conn.selectPerson(2);

        System.out.println(person1.getName());
        System.out.println(person2.getName());

    }
}

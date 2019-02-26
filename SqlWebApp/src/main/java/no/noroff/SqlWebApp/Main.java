package no.noroff.SqlWebApp;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        TheSqlConnection conn = new TheSqlConnection();
        conn.connect();
        conn.initAllTables();


        // SELECT PERSON TESTING
        Person person1 = conn.selectPerson(1);
        Person person2 = conn.selectPerson(2);

        System.out.println(person1.getName());
        System.out.println(person2.getName());

    }
}

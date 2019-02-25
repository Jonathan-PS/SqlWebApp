package no.noroff;

public class Main {
    public static void main(String[] args) {
        System.out.println("Jonathan was here!");

        TheSqlConnection conn = new TheSqlConnection();

        conn.connect();

        conn.initPersons();
        conn.initPhoneNumbers();
    }
}

package no.noroff;

public class Main {
    public static void main(String[] args) {

        TheSqlConnection conn = new TheSqlConnection();

        conn.connect();
        conn.initPersons();
        conn.initPhoneNumbers();
    }
}

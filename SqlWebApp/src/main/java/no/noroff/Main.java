package no.noroff;

public class Main {
    public static void main(String[] args) {
        System.out.println("Jonathan was here!");
        System.out.println("Thomas was not here");

        TheSqlConnection db = new TheSqlConnection();
        db.connect();
    }
}

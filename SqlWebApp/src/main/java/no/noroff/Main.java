package no.noroff;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        
        String[] firstName = {"Leon", "Eliot", "Malak", "Katya", "Tobias", "Natalya", "Kelise", "Cieran", "Duke", "Lilly"};
        String[] lastName = {"Greig", "Villarreal", "Payne", "Whitfield", "Oliver", "Pace", "Sheldon", "Young", "Gray", "Hawkins"};
        String[] homeAdress = {"27 Shelby Ave", "8705 Kevin Ln", "24 South St", "5435 Louise Ave", "5414 County 150 Rd",
                                "407 Carolina St", "26 9th Ave", "405 Sunrise Ave", "6 Buttonball Dr", "911 Clare Ave"};
        LocalDate[] dateOfBirth = { LocalDate.of(1980,1,1), LocalDate.of(1960,3,6),
                                    LocalDate.of(1980,6,7), LocalDate.of(2000,12,3),
                                    LocalDate.of(2010,7,21), LocalDate.of(1991,11,14),
                                    LocalDate.of(2001,8,30), LocalDate.of(1920,10,2),
                                    LocalDate.of(1999,4,12), LocalDate.of(1954,6,13)};

        TheSqlConnection conn = new TheSqlConnection();

        conn.connect();
        conn.initPersons();
        conn.initPhoneNumbers();
    }
}

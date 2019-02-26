package no.noroff.SqlWebApp;

import no.noroff.SqlWebApp.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlWebApplication {

	public static void main(String[] args) {
		TheSqlConnection conn = new TheSqlConnection();
		conn.connect();
		conn.initAllTables();



        // SELECT PERSON TESTING
        Person person1 = conn.selectPerson(1);
        Person person2 = conn.selectPerson(2);

        System.out.println(person1.getName());
        System.out.println(person2.getName());


        //SpringApplication.run(SqlWebApplication.class, args);

	}



}

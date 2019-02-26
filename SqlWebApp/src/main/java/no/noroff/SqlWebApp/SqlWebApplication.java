package no.noroff.SqlWebApp;

import no.noroff.SqlWebApp.models.Person;
import no.noroff.SqlWebApp.models.PhoneNumber;
import no.noroff.SqlWebApp.models.Relationship;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlWebApplication {
	public static TheSqlConnection conn;

	public static void main(String[] args) {
		SpringApplication.run(SqlWebApplication.class, args);

		System.out.println("-------BEFORE");
		conn = new TheSqlConnection();
		conn.connect();
		conn.initAllTables();
		System.out.println("++++++++AFTER");


        // SELECT PERSON TESTING
        Person person1 = conn.selectPerson(1);
        Person person2 = conn.selectPerson(2);
        System.out.println(person1.getName());
        System.out.println(person2.getName());

		//SELECT PHONENUMBER TESTING
		PhoneNumber phoneNumber1 = conn.selectPhoneNumber(1);
		System.out.println(phoneNumber1.getPhoneNumber());

		Relationship rel = conn.selectRelationship(1);
		System.out.printf("Relationship %d:	%s	%s	%s	%s\n",
				rel.getrID(), rel.getP1Name(), rel.getP2Name(), rel.getP1p2(), rel.getP2p1());

		Relationship rel2 = conn.selectRelationship(2);
		System.out.printf("Relationship %d:	%s	%s	%s	%s\n",
				rel2.getrID(), rel2.getP1Name(), rel2.getP2Name(), rel2.getP1p2(), rel2.getP2p1());

	}
}

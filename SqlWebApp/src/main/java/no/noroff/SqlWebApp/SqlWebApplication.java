package no.noroff.SqlWebApp;

import no.noroff.SqlWebApp.sqlQueries.TheSqlConnection;
import no.noroff.SqlWebApp.models.Person;
import no.noroff.SqlWebApp.models.PhoneNumber;
import no.noroff.SqlWebApp.models.Relationship;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SqlWebApplication {
	public static TheSqlConnection sqlConn;

	public static void main(String[] args) {

		System.out.println("-------BEFORE");
		sqlConn = new TheSqlConnection();
		sqlConn.connect();
		sqlConn.initAllTables();
		System.out.println("++++++++AFTER");

		SpringApplication.run(SqlWebApplication.class, args);


        // SELECT PERSON TESTING
        Person person1 = sqlConn.selectPerson(1);
        //Person person2 = |.selectPerson(2);
        System.out.println(person1.getFirstName() + person1.getLastName());
        //System.out.println(person2.getFirstName() + person2.getLastName());

		/*Test update email*/
		//sqlConn.updateTable(9, "Emails", "Email", "trude@craigmail.com");
		//System.out.println(sqlConn.selectEmailList(9).get(0).getEmail());

		/*Test delete*/
		//sqlConn.delete(10);

        /*
		//SELECT PHONENUMBER TESTING
		PhoneNumber phoneNumber1 = sqlConn.selectPhoneNumber(1);

		System.out.println(phoneNumber1.getPhoneNumber());

		Relationship rel = sqlConn.selectRelationship(1);
		System.out.printf("Relationship %d:	%s	%s	%s	%s\n",
				rel.getrID(), rel.getP1Name(), rel.getP2Name(), rel.getP1p2(), rel.getP2p1());

		Relationship rel2 = sqlConn.selectRelationship(2);
		System.out.printf("Relationship %d:	%s	%s	%s	%s\n",
				rel2.getrID(), rel2.getP1Name(), rel2.getP2Name(), rel2.getP1p2(), rel2.getP2p1());
		//System.out.println(phoneNumber1.getPhoneNumber());

		ArrayList<Person> personList = new ArrayList<Person>();
		personList = sqlConn.selectPersonByName("FirstName","Lilly");

		ArrayList<PhoneNumber> phoneBook = new ArrayList<PhoneNumber>();
		phoneBook = sqlConn.selectAllEqualPhoneNumbers("12345678");
		System.out.println(phoneBook.get(0).getPhoneNumber());

		Person p = sqlConn.selectPerson(phoneBook.get(0).getpID());
		System.out.println(p.getFirstName() + " " + p.getLastName() + " " + phoneBook.get(0).getPhoneNumber());

		boolean check = sqlConn.checkPID(11);
		System.out.println(check);


		ArrayList<Relationship> allRelationships = SqlWebApplication.sqlConn.selectAllRelations();

		for (Relationship relation: allRelationships) {
			System.out.println(relation.getP1() + relation.getP1Name() + ", " + relation.getP1p2() + " of " +relation.getP2() + relation.getP2Name());

		}
*/
	}
}

package no.noroff.SqlWebApp;

import no.noroff.SqlWebApp.sqlQueries.TheSqlConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlWebApplication {
	public static TheSqlConnection sqlConn;

	public static void main(String[] args) {
		SpringApplication.run(SqlWebApplication.class, args);
		System.out.println("-------BEFORE");
		sqlConn = new TheSqlConnection();
		sqlConn.connect();
		sqlConn.initAllTables();
		System.out.println("++++++++AFTER");


	}
}

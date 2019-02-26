package no.noroff.SqlWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlWebApplication {

	public static void main(String[] args) {
		TheSqlConnection conn = new TheSqlConnection();
		conn.connect();
		conn.initAllTables();


		SpringApplication.run(SqlWebApplication.class, args);

	}



}

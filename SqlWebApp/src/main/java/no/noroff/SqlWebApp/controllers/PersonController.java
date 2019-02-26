package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.TheSqlConnection;
import no.noroff.SqlWebApp.models.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/person/{pID}")
    public Person personGet(@PathVariable int pID) {

        System.out.println("Trying to find person: " + pID);

        Person person = SqlWebApplication.conn.selectPerson(pID);

        return person;

    }
}

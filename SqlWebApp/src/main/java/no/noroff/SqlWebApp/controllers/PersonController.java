package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.models.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class PersonController {

    @GetMapping("/person")
    public ArrayList<Person> personsGet() {

        System.out.println("Trying to find person: ");

        ArrayList<Person> personList = SqlWebApplication.sqlConn.selectAllPersons();

        return personList;

    }

    @GetMapping("/person/{pID}")
    public Person personGet(@PathVariable int pID) {

        System.out.println("Trying to find person: " + pID);

        Person person = SqlWebApplication.sqlConn.selectPerson(pID);

        return person;
    }
}

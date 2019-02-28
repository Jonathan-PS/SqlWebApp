package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.UserInput;
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

    @GetMapping("/menu/list")
    public static ArrayList<Person> personSearch(UserInput userInput) {
        ArrayList<Person> pl = new ArrayList<Person>();
        if(userInput.getValue() != null && userInput.getAttribute() != null){
            pl = SqlWebApplication.sqlConn.selectPersonByName(userInput.getAttribute(),userInput.getValue());
            for (Person p : pl){
                System.out.println(p.getpID() + " " + p.getFirstName() + " " + p.getLastName());
            }
            System.out.println(pl.get(0).getFirstName());
        }
        //ArrayList<Person> personList = SqlWebApplication.sqlConn.selectPersonByName(userInput.getAttribute(),userInput.getValue());

        System.out.println("was called");
        return pl;

    }

    @GetMapping("/person/{pID}")
    public Person personGet(@PathVariable int pID) {

        System.out.println("Trying to find person: " + pID);

        Person person = SqlWebApplication.sqlConn.selectPerson(pID);

        return person;
    }
}

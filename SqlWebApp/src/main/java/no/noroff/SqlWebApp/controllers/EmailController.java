package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.models.Email;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @GetMapping("/email/{eID}")
    public Email emailGet(@PathVariable int eID) {

        System.out.println("Trying to find email: " + eID);

        Email email = SqlWebApplication.sqlConn.selectEmail(eID);

        return email;

    }
}
package no.noroff.SqlWebApp.controllers;


import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.models.PhoneNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneNumberController {

    @GetMapping("/phonenumber/{pnID}")
    public PhoneNumber phoneNumberGet(@PathVariable int pnID) {

        System.out.println("Trying to find phone number: " + pnID);

        PhoneNumber phoneNumber = SqlWebApplication.sqlConn.selectPhoneNumber(pnID);

        return phoneNumber;

    }
}

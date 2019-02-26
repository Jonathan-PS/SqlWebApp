package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.models.PhoneNumber;
import no.noroff.SqlWebApp.models.Relationship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationshipsController {

    @GetMapping("/relationship/{rID}")
    public Relationship relationshipGet(@PathVariable int rID) {

        System.out.println("Trying to find relationship: " + rID);

        Relationship relationship = SqlWebApplication.conn.selectRelationship(rID);

        return relationship;

    }
}

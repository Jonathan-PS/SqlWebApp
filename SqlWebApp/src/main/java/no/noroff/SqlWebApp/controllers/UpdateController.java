package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.UserInput;
import no.noroff.SqlWebApp.UserUpdateInput;
import no.noroff.SqlWebApp.models.Person;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateController {

    @PostMapping("/update/")
    public UserUpdateInput editOnePerson(
            @RequestBody UserUpdateInput userUpdateInput) {

        System.out.println("UpdateController: "+ userUpdateInput.getTableName() +" "+
                        userUpdateInput.getId() +" "+
                userUpdateInput.getAttributeName() +" "+
                userUpdateInput.getValue());

        SqlWebApplication.sqlConn.updateTable(
                userUpdateInput.getTableName(),
                userUpdateInput.getId(),
                userUpdateInput.getAttributeName(),
                userUpdateInput.getValue());

        return userUpdateInput;
    }
}

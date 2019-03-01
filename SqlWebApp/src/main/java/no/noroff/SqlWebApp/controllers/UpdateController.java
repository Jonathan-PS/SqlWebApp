package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.DeleteInput;
import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.UserInput;
import no.noroff.SqlWebApp.UserUpdateInput;
import no.noroff.SqlWebApp.models.Person;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UpdateController {

    UserUpdateInput userUpdateInput;
    private String templateName = "updateTemplate";

    @GetMapping("/update")
    public String update(Model model) {
        model.addAttribute("userUpdateInput", new UserUpdateInput());
        return templateName;
    }

    @PostMapping("/update") //use the shorthand
    public String deleteResult(@ModelAttribute("userUpdateInput") UserUpdateInput userUpdateInput,
                               RedirectAttributes re) {
        this.userUpdateInput = userUpdateInput;
        System.out.println("You are trying to update id: " + userUpdateInput.getId());

        System.out.println(userUpdateInput.getAttributeName());
        SqlWebApplication.sqlConn.updateTable("Persons", userUpdateInput.getId(), userUpdateInput.getAttributeName(), userUpdateInput.getValue());

        re.addAttribute("pid", userUpdateInput.getId());

        return "redirect:/persons/{pid}";
    }

    /*
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
    } */
}
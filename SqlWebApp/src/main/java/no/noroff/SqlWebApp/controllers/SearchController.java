package no.noroff.SqlWebApp.controllers;

import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.UserInput;
import no.noroff.SqlWebApp.models.Relationship;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {
    private String templateName = "menuTemplate";
    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("userInput", new UserInput());
        return templateName;
    }

    //@PostMapping("/menu") //use the shorthand
    public String menuResult(@ModelAttribute("userInput") UserInput userInput) {
           System.out.println("value: " + userInput.getValue());
        System.out.println("dropdown: " + userInput.getAttribute());
           return "persons/1";
    }
}

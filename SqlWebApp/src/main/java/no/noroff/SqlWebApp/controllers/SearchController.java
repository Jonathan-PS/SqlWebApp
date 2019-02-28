package no.noroff.SqlWebApp.controllers;


import no.noroff.SqlWebApp.SqlWebApplication;
import no.noroff.SqlWebApp.UserInput;
import no.noroff.SqlWebApp.models.Person;
import no.noroff.SqlWebApp.models.Relationship;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collections;
import java.util.List;

import java.util.ArrayList;

@Controller
public class SearchController {
    UserInput userInput;

    private String templateName = "menuTemplate";
    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("userInput", new UserInput());
        return templateName;
    }












}
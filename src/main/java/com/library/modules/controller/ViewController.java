package com.library.modules.controller;

import com.library.modules.Service.BookService;
import com.library.modules.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController
{
    @Autowired
    BookService bookService;

    @GetMapping("/login")
    public String login(Model model, String logout, String errorMsg)
    {
        System.out.println("Test Mapping forward");
        return "login";
    }

    @GetMapping("/home")
    public String homeForward()
    {
        return "home";
    }

    @GetMapping("/listBooks")
    public String listBooks()
    {
        return "book";
    }

    @GetMapping("/lendHistory")
    public String lendHistoryForward()
    {
        return "lendHistory";
    }

    @GetMapping("/AvailablityCheck")
    public String AvailablityCheck()
    {
        return "AvailablityCheck";
    }

    @GetMapping("/addBook")
    public String UpdateStock()
    {
        return "addBook";
    }

    @GetMapping("/AddMembers")
    public String AddMembers(Model model)
    {
        //model.addAttribute("AddMembers");
        return "AddMembers";
    }

}
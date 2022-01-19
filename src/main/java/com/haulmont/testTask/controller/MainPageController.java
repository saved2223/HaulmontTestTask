package com.haulmont.testTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping(value = "")
    public String showMainPage(Model model) {
        return "index";
    }


}

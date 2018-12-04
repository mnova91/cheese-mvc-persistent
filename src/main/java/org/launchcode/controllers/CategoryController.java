package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model templateVariables) {
        templateVariables.addAttribute("title", "All Categories");
        templateVariables.addAttribute("categories", categoryDao.findAll());
        return "category/index"; }

//    @RequestMapping(value = "add")
//    public String add() {
//        //render an add form
//        return ""; }
//
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String add() {
//        //accepts POST form request
//        return ""; }

}

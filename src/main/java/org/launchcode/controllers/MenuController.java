package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller @RequestMapping(value = "menu") public class MenuController {

    //Data Access Objects

    @Autowired private CheeseDao cheeseDao;

    @Autowired private MenuDao menuDao;

    //Internal Handlers

    @RequestMapping(value = "") public String index(Model templateVariables) {
        templateVariables.addAttribute("menus", menuDao.findAll());
        templateVariables.addAttribute("title", "All Menus");
        return "menu/index"; }

    @RequestMapping(value = "add", method = RequestMethod.GET) public String displayAddMenuForm(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        model.addAttribute("categories", menuDao.findAll());
        return "menu/add"; }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuForm(Model model, @ModelAttribute @Valid Menu menu, Errors errors) {
        if (errors.hasErrors()){
        model.addAttribute("title","Add Menu");
        return "menu/add"; }

        menuDao.save(menu);
        return "redirect:";
    }

    @RequestMapping(value = "view/{menuId}")
    public String displayView(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);
        String title = "Menu: " + menu.getName();
        model.addAttribute("menu", menu);
        model.addAttribute("title", title);
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{menuId}")
    public String addMenuItem(Model model, @PathVariable int menuId) {

        Menu menu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        AddMenuItem addMenuItem = new AddMenuItem(menu, cheeses);

        model.addAttribute("form", addMenuItem);

        String title = "Add item to menu: " + menu.getName();
        model.addAttribute("menu", menu);
        model.addAttribute("title", title);
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItem addMenuItem, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", addMenuItem);
            return "menu/add-item";
        }
        int cheeseId = addMenuItem.getCheeseId();
        int menuId = addMenuItem.getMenuId();
        Menu menu = menuDao.findOne(menuId);
        Cheese cheese = cheeseDao.findOne(cheeseId);
        menu.addItem(cheese);
        menuDao.save(menu);
        return "redirect:/menu/view/" + menu.getId();
    }
}

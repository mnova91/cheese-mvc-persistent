package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItem {
    //variables

    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull private int MenuId;

    @NotNull private int CheeseId;

    //getters and setters

    public Menu getMenu() { return menu; }

    public Iterable<Cheese> getCheeses() { return cheeses; }

    public int getMenuId() { return MenuId; }

    public int getCheeseId() { return CheeseId; }

    public void setMenu(Menu menu) { this.menu = menu; }

    public void setCheeses(Iterable<Cheese> cheeses) { this.cheeses = cheeses; }

    //constructors

    public AddMenuItem() { }

    public AddMenuItem(Menu menu, Iterable<Cheese> cheeses) { this.menu = menu; this.cheeses = cheeses; }
}




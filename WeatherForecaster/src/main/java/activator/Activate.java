package activator;

import menu.Menu;
import page.Page;

public class Activate {

    private Menu menu;
    private Page page;

    /*
    Constructor to initialize objects of Page, Menu classes.
    And then activate menu by - menu.activateMenu() method.
     */
    public Activate() {
        page = new Page();
        menu = new Menu(page);
        menu.activateMenu();    }

}

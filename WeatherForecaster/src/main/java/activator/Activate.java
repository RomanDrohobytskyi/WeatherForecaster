package activator;

import menu.Menu;
import page.Page;

public class Activate {

    private Menu menu;
    private Page page;

    public Activate() {
        page = new Page();
        menu = new Menu(page);
        menu.activateMenu();    }

}

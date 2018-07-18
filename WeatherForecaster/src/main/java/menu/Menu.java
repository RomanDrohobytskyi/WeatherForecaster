package menu;

import access.Password;
import input.Input;
import page.Page;
import page.Constants;

import java.util.HashMap;
import java.util.Map;

public class Menu{

    private Input input;
    private Password password;
    private Page page;
    private Map<Integer, Runnable> subMenu;

    public Menu(Page page){
        this.page = page;
        input = new  Input();
        password = new Password();
    }

    {
        subMenu = new HashMap<>();
        subMenu.put(0, () -> System.out.println( Constants.STARS + "\n" + "SubMenu logged out."));
        subMenu.put(1, () -> page.printAllTemps());
        subMenu.put(2, () -> page.printAllMaxTemps());
        subMenu.put(3, () -> page.printAllMinTemps());
        subMenu.put(4, () -> page.updateMinTemp(input));
        subMenu.put(5, () -> page.updateMaxTemp(input));
        subMenu.put(6, () -> page.updateBothTemps(input));

        subMenu.put(10, () -> page.printDaysTemps());
        subMenu.put(11, () -> page.printAllDays());
        subMenu.put(12, () -> page.printSelectedDay(input));
        subMenu.put(13, () -> page.updateDay(input));
        subMenu.put(14, () -> page.updateMonth(input));
        subMenu.put(15, () -> page.updateYear(input));
        subMenu.put(16, () -> page.updateAllDates(input));
        subMenu.put(17, () -> page.updatePassword(input, password));
        subMenu.put(20, () -> globalExit());
    }

    public void activateMenu() {
        boolean menu = true;
        while (menu){
            System.out.println(Constants.STARS);
            printEnumMenu();
            System.out.println(Constants.STARS);
            menu = choiceMenu(choiceEnum(),page);
        }
    }

    public void runMapMenu(int choice){
        subMenu.get(choice).run();
    }

    private void printEnumMenu(){
    for (EMenu element : EMenu.values()){
            System.out.println(element.getElement());
        }
    }

    private boolean choiceMenu(EMenu menu, Page page){
        try {
            switch (menu) {
                case WEEK:
                    page.printWeekWeather();
                    break;
                case TITLE:
                    page.printTitle();
                    break;
                case TODAY:
                    page.printTodayWeather();
                    break;
                case CHECK_DAY:
                    page.checkDay();
                    break;
                case RANGE_DAYS:
                    page.getSelectedRange(input);
                    page.printRangeDay();
                    break;
                case INSERT:
                    page.insertDayDateIntoDB();
                    break;
                case DAY:
                    page.printSelectedDay(input);
                    break;
                case UPDATE:
                    if (password.verifyPassword(page, password.enterPassword(input))) {
                        System.out.println("SubMenu.");
                        subMenu();
                        break;
                    }
                    else
                        break;
                case EXIT:
                    if (page.getConnection() != null) {
                        globalExit();
                    }
                    return false;
            }

            }catch (Exception e) {
            System.out.println("Exception : " + e);
            e.printStackTrace();
        }
            return true;
    }

    private EMenu choiceEnum(){
        switch (getChoice()){
            case 1:
                return EMenu.WEEK;
            case 2:
                return EMenu.TODAY;
            case 3:
                return EMenu.DAY;
            case 4:
                return EMenu.RANGE_DAYS;
            case 5:
                return EMenu.CHECK_DAY;
            case 6:
                return EMenu.INSERT;
            case 7:
                return EMenu.TITLE;
            case 8:
                return EMenu.UPDATE;
            case 0:
                return EMenu.EXIT;
        }
        return null;
    }

    private void subMenu() {
        int choice;
        try {
            do {
                System.out.println(Constants.STARS);
                System.out.println("10 - print all temperatures with days.");
                System.out.println("1  - print all temperatures.");
                System.out.println("2  - print only max Temps.");
                System.out.println("3  - print only min Temps.");
                System.out.println("4  - update min temp.");
                System.out.println("5  - update max temp.");
                System.out.println("6  - update both temps.");
                System.out.println(Constants.STARS);
                System.out.println("11 - print all days.");
                System.out.println("12 - print selected day.");
                System.out.println("13 - update day.");
                System.out.println("14 - update month.");
                System.out.println("15 - update year.");
                System.out.println("16 - update all dates.");
                System.out.println("17 - update password.");
                System.out.println("20 - global exit.");
                System.out.println("0  - exit.");
                System.out.println(Constants.STARS);
                System.out.println("Please, enter your choice:");
                System.out.println(Constants.STARS);

                choice = input.inputNumber(0, 20);
                runMapMenu(choice);

            } while (choice != 0) ;
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
    }

    private int getChoice(){
        System.out.println("Please, enter your choice:");
        System.out.println(Constants.STARS);
        int choice = input.inputNumber( 0, EMenu.values().length);
        return choice;
    }

    public void globalExit(){
        try {
            System.out.println(Constants.STARS);
            System.out.println("Global exit...");
            if (page.getConnection() != null) {
                page.closeAll();
            }
            System.out.println("JDBC connector closed.");
            System.out.println("Logged out...");
            System.exit(0);
        }catch (Exception e){
            System.out.println("Exception : " + e);
        }
    }
}

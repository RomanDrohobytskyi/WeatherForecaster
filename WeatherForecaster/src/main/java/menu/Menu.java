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

    /*
    Constructor - to initialize page, input and password objects.
     */
    public Menu(Page page){
        this.page = page;
        this.input = new  Input();
        this.password = new Password();
    }

    /*
    SubMenu map initialize. Where key is integer, value - runnable, methods.
    They contains to numbers in subMenu
     */
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

    /*
    Method printing and menu and get your choice. While before you`ll choose EXIT - 0.
     */
    public void activateMenu() {
        boolean menu = true;
        while (menu){
            System.out.println(Constants.STARS);
            printEnumMenu();
            System.out.println(Constants.STARS);
            menu = choiceMenu(choiceEnum(),page);
        }
    }

    /*
    This method run chosen subMenu method. Where parameter 'choice'
    it`s a number of method in subMenu.
     */
    public void runMapMenu(int choice){
        subMenu.get(choice).run();
    }

    /*
    Printing all ENUM value from class EMenu.
     */
    private void printEnumMenu(){
    for (EMenu element : EMenu.values()){
            System.out.println(element.getElement());
        }
    }

    /*
    Run method based on EMenu value.
     */
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

    /*
    Return EMenu value based on your choice - getChoice().
     */
    private EMenu choiceEnum(){
        switch (getChoice(0, EMenu.values().length - 1)){
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

    /*
     This method print subMenu and getting your choice, then run map method based
     on your choice. While before you`ll chose EXIT - 0.
     */
    private void subMenu() {
        int choice;
        try {
            do {
                System.out.println("SubMenu.");
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

                choice = getChoice(0, 20);
                runMapMenu(choice);

            } while (choice != 0) ;
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
    }

    /*
    This method getting your choice, parameters - range of menus.
     */
    private int getChoice(int start, int end){
        System.out.println("Please, enter your choice:");
        System.out.println(Constants.STARS);
        int choice = input.inputNumber( start, end);
        return choice;
    }

    /*
    This method close activate page.closeAll() method if connection ISN`T null.
    And then exit.
     */
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

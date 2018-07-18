package menu;

public enum EMenu {
    WEEK ("1 - to see weather for the week."),
    TODAY ("2 - to see weather for today. "),
    DAY ("3 - to see weather only for a one day."),
    RANGE_DAYS("4 - to see weather for range days."),
    CHECK_DAY("5 - check last added day."),
    INSERT ("6 - insert day & temperature to DB."),
    TITLE ("7 - see page title."),
    UPDATE ("8 - to update DataBase."),
    EXIT ("0 - exit.");

    private String element;

    EMenu(String message) {
        this.element = message;
    }

    public String getElement() {
        return element;
    }
}

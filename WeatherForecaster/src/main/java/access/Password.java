package access;

import input.Input;
import page.Page;
import page.Constants;

import java.util.Arrays;

public class Password {

    /*
    This method verify entered password from argument with password from DB,
    returned by page.getPassword() method. Return true if equals, false if NOT.
     */
    public boolean verifyPassword(Page page, char[]password){
        if (Arrays.equals(password, page.getPassword())){
            System.out.println(Constants.STARS);
            return true;
        }
        else
            System.out.println(Constants.STARS);
            System.out.println("Sorry, wrong password!");
            return false;
    }

    /*
    This method is to input a password and then return password as char array.
     */
    public char[] enterPassword(Input input){
        System.out.println(Constants.STARS);
        System.out.println("Enter password, please.");
        return input.inputChar();
    }

}

package input;

import java.util.Scanner;

public class Input {

    private Scanner scanner = new Scanner(System.in);

    /*
    inputNumber(int start, int end) - method to input a Integer number,
    where parameter 'start & end' - range.
     */
    public int inputNumber(int start, int end){
        while (true) {
            try {
                String numberStr = scanner.nextLine();
                int number = Integer.parseInt(numberStr);
                if (number < start || number > end) {
                    System.out.println("Wrong range, try again");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input...");
                System.out.println("Exception : " + e);
                System.out.println("Try again!");
            }
        }
    }

    public char[]inputChar(){
        try {
            String textStr = scanner.nextLine();
            return textStr.toCharArray();
            } catch (Exception e) {
                System.out.println("Wrong input...");
                System.out.println("Exception : " + e);
                System.out.println("Try again!");
            }
        return null;

    }
}

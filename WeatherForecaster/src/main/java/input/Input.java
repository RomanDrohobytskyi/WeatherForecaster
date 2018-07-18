package input;

import java.util.Scanner;

public class Input {

    private Scanner scanner = new Scanner(System.in);

    public int inputNumber(int start, int end){
        boolean flag = true;
        while (flag) {
            try {
                String numberStr = scanner.nextLine();
                int number = Integer.parseInt(numberStr);
                if (number < start || number > end) {
                    System.out.println("Wrong range, try again");
                    flag = true;
                } else {
                    flag = false;
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input...");
                System.out.println("Exception : " + e);
                System.out.println("Try again!");
            }
        }
        return 0;
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

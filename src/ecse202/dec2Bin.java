package ecse202;

import java.util.Scanner;
/**
 * @author Martin Nguyen, Professor Frank Ferrie F2021 midterm
 * program to convert decimal to binary string
 * acm library not used for portability reasons
 */
public class dec2Bin {
    public static void main(String[] args) {
        System.out.println("Enter number to be converted (negative value to terminate program)");
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            int myNum = sc.nextInt();
            if(myNum < 0) { // Sentinel
                System.out.println("Program terminated.");
                break;
            } else {
                String binString = cvtDec2Bin(myNum);
                System.out.println(myNum + " expressed in binary = " + binString);
            }
        }


    }

    /**
     * @param arg int to convert to a binary String
     * @return binary String representation of int arg
     */
    static String cvtDec2Bin(int arg) {
        String out = "";
        int currentBit;
        while (arg > 0) {
            currentBit = arg % 2;
            arg /= 2;
            out = currentBit + out;
        }
        return out;
    }

}

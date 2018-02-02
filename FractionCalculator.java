/*************************************************************
 * Microsoft: DEV277x - Object Oriented Programming in Java
 * Module 2 Project
 * ***********************
 * FRACTION CALCULATOR
 * ***********************
 * Author: Zaryab Muhammad Akram
 * 2/2/2018
 * NNNNNNNNNNNNN Fraction Calculator Class NNNNNNNNNNNNNNNN
 *************************************************************/

import java.util.Scanner;
public class FractionCalculator {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        //welcome message
        System.out.println("This program is a fraction calculator.");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");

        while(true){
            System.out.println("----------------------------------------------------------------------------------");

            //prompting user to enter in an operation
            String operation = getOperation();

            //getting two fractions from the user
            Fraction input1 = getFraction();
            Fraction input2 = getFraction();

            Fraction output = new Fraction(1,1); /*declaring a fraction variable for the output*/

            //commuting result according to user choice
            if (operation.equals("=")) {
                boolean result = input1.equals(input2);
                System.out.println(input1.toString() + " " + operation + " " + input2.toString() + " = " + result);
                continue;
            } else if (operation.equals("-")) {
                output = input1.subtract(input2);
            } else if (operation.equals("*")) {
                output = input1.multiply(input2);
            } else if (operation.equals("/")) {
                output = input1.divide(input2);
              } else {
               output = input1.add(input2);
               }

            output.toLowestTerms(); /*convert the output fraction to its lowest form*/
            if(output.getDenominator() == 1){
            int outInt = output.getNumerator();
                System.out.println(input1.toString() + " " + operation + " " + input2.toString() + " = " + outInt);
                continue;
            }

            System.out.println(input1.toString() + " " + operation + " " + input2.toString() + " = " + output);
        }
    }
    /*
    * This method prompts the user to enter a valid operation symbol.
    * On entering of 'q' or 'Q', the program is terminated.
     */
    public static String getOperation(){
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
        String operation = input.nextLine();

        while(!operation.equals("+") && !operation.equals("-") && !operation.equals("/") && !operation.equals("*") && !operation.equals("=") && !operation.equalsIgnoreCase("q")){
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            operation = input.nextLine();
        }

        if (operation.equalsIgnoreCase("q")) {
            System.exit(0); /*terminating program if the operation input is q or Q*/
        }

        return operation;
    }

    /*
    * This method checks if the entered fraction is valid or not.
     */
    public static boolean validFraction(String input) {
        if (input.contains("/")) { /*if input is of the form a/b*/
            String[] inputParts = input.split("/"); /* inputParts[0] = numerator, inputParts[1] = denominator */
            /*checking if the numerator and denominators are integers*/
            if (inputParts[0].matches("-?\\d+") && inputParts[1].matches("-?\\d+")) {
                if (Integer.parseInt(inputParts[1]) > 0) {
                    return true;
                } else {
                    return false; /*negative denominator*/
                }
            } else {
                return false; /*Non-integer numerator or denominator*/
            }
        } else {
            if (input.matches("-?\\d+")) { /*if input in an integer*/
                return true;
            } else { /*Non integer input*/
                return false;
            }
        }
    }

    /*
    * This method prompts the user to input a fraction(string) of the form a/b and converts it to integer.
     */
    public static Fraction getFraction(){
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String inputData = input.nextLine();

        while(!validFraction(inputData)){
            System.out.print("Invalid fraction. Please enter a fraction (a/b) or (a), where a and b are integers and b is not zero: ");
            inputData = input.nextLine();
        }

        if(inputData.contains("/")){
            String[] inputParts = inputData.split("/");
            int numInput = Integer.parseInt(inputParts[0]);
            int denInput = Integer.parseInt(inputParts[1]);
            return new Fraction(numInput, denInput);
        } else{
            return new Fraction(Integer.parseInt(inputData), 1);
        }
    }
}


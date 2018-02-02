/*************************************************************
 * Microsoft: DEV277x - Object Oriented Programming in Java
 * Module 2 Project
 * ***********************
 * FRACTION CALCULATOR
 * ***********************
 * Author: Zaryab Muhammad Akram
 * 2/2/2018
 * NNNNNNNNNNNNN Fraction Class NNNNNNNNNNNNNNNN
 *************************************************************/

import java.util.InputMismatchException;
public class Fraction {
    //fields
    private int numerator = 0;
    private int denominator = 0;

    //two parameter constructor
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        if (denominator == 0) { /*throw an IllegalArgumentException if the denominator is zero*/
            throw new IllegalArgumentException(Integer.toString(denominator));
        } else if(denominator < 0) {
            this.numerator *= -1;
            this.denominator = (-1) * denominator;
        } else {
            this.denominator = denominator;
        }
    }

    //one parameter constructor
    public Fraction(int numerator) {
        this(numerator, 1);
    }

    //zero parameter constructor
    public Fraction() {
        this(0, 1);
    }

    /*
    * This method exposes the value of the numerator field to the user
     */
    public int getNumerator(){
        return this.numerator;
    }

    /*
     * This method exposes the value of the denominator field to the user
     */
    public int getDenominator(){
        return this.denominator;
    }

    /*
    * This method returns  a String representation of the Fraction
     */
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    /*
    *This method gives the result of numerator / denominator
     */
    public double toDouble(){
        return (double)numerator /denominator; /*type-casting to double so that integer division does not occur*/
    }

    /*
     *This method takes in two ints and returns the Greatest Common Divisor.
     */
    private static int gcd(int num, int den){
        if (den == 0) {
            return num;
        }
        return gcd(den, num % den);
    }

    /*
     *This method takes in two ints and returns the Lowest Common Multiple of the denominators.
     */
    private static int lcm(int den1, int den2){
        int numGCD = gcd(den1, den2);
        return (den1 * den2)/ numGCD;
    }

    /*
     *This method returns a new Fraction that is the sum of other and this fractions.
     */
    public Fraction add(Fraction other){
        int fracDenominator = lcm(this.denominator, other.denominator);
        int fracNumerator = ((fracDenominator/ this.denominator) * this.numerator) + ((fracDenominator/ other.denominator) * other.numerator);
        Fraction result = new Fraction(fracNumerator, fracDenominator);
        return result;
    }

    /*
     *This method returns a new Fraction that is the difference between the other and this fraction.
     */
    public Fraction subtract(Fraction other){
        int fracDenominator = lcm(this.denominator, other.denominator);
        int fracNumerator = ((fracDenominator/ this.denominator) * this.numerator) - ((fracDenominator/ other.denominator) * other.numerator);
        Fraction result = new Fraction(fracNumerator, fracDenominator);
        return result;
    }

    /*
     *This method returns a new Fraction that is the product of the other and this fraction.
     */
    public Fraction multiply(Fraction other){
        int fracNumerator =  this.numerator * other.numerator;
        int fracDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(fracNumerator, fracDenominator);
        return result;
    }

    /*
     *This method returns a new Fraction that is the division of the other and this fraction.
     */
    public Fraction divide(Fraction other){
        if(other.denominator == 0){
            /*throw an IllegalArgumentException if the user asks to divide by zero*/
            throw new IllegalArgumentException(Integer.toString(denominator));
        }
        int fracNumerator =  this.numerator * other.denominator;
        int fracDenominator = this.denominator * other.numerator;
        Fraction result = new Fraction(fracNumerator, fracDenominator);
        return result;
    }

    /*
     *This method converts the current fraction to the lowest terms
     */
    public void toLowestTerms(){
        int numGCD = gcd(this.numerator, this.denominator);
        this.numerator /= numGCD;
        this.denominator /= numGCD;
        if(this.denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /*
     *This method checks whether the other and this equations are equal or not.
     */
    public boolean equals(Object other){
        if(other instanceof Fraction){
            Fraction otherFrac = (Fraction)other;
            otherFrac.toLowestTerms();

            Fraction thisFrac = new Fraction(this.numerator, this.denominator);
            thisFrac.toLowestTerms();

            if ((thisFrac.numerator == otherFrac.numerator) && (thisFrac.denominator == otherFrac.denominator)) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new InputMismatchException();
        }
    }
}


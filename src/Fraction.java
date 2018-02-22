/**
 * Defines object Fraction with default and two other constructors.
 * Defines methods for adding, subtracting, multiplying, dividing or comparing fractions and helper methods.
 *
 * @author Kamila Lastowska
 * @version 1.0
 * @since 1.0
 */
public class Fraction {
    private int numerator;
    private int denominator;

    Fraction(){
        numerator = 0;
        denominator = 1;
    }

    Fraction (int numerator){
        this.numerator = numerator;
        denominator = 1;
    }

    Fraction (int numerator, int denominator) throws IllegalArgumentException {
        this.numerator = numerator;
        if (denominator == 0) throw new IllegalArgumentException("Denominator cannot be zero!");
        this.denominator = denominator;
        if (denominator <0){
            this.denominator = -denominator;
            this.numerator = -numerator;
        }
    }

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    public String toString(){
        if (numerator%denominator == 0){
           return Integer.toString(numerator/denominator);
        }
        else
        return numerator+"/"+denominator;
    }

    /**
     * Converts Fraction to a Double value by dividing numerator by denominator.
     * Usage: calculate fraction to check if fractions are equal (see: equals(Object other))
     */
    private double toDouble(){
        return numerator/denominator;
    }

    /**
     * Finds the greatest common divisor (Euclidean Algorithm) of two integers.
     * Usage: to convert fraction to lowest terms.
     *
     * @param num integer, numerator of a fraction
     * @param den integer, denominator of a fraction
     * @return    greatest common divisor of 2 ints
     */
    private static int gcd(int num, int den){
        //finds greatest common divisor
        do {
            int remainder = num%den;
            num = den;
            den = remainder;
        }
        while (den!=0);
        return num;
    }

    /**
     * Converts the current fraction to the lowest terms.
     */
    private void toLowestTerms(){
        /*
        int num = Math.abs(numerator);
        int den = Math.abs(denominator);
         */
        int num = numerator;
        int den = denominator;
        int commonDiv = gcd(num, den);
        numerator = num/commonDiv;
        denominator = den/commonDiv;
    }

    /**
     * Adds the current and other Fraction
     *
     * @param other Fraction instance to add to current Fraction
     * @return      new Fraction as a result of adding
     */
    Fraction add(Fraction other){
        Fraction addFrac = new Fraction();
        addFrac.denominator = denominator*other.denominator;
        addFrac.numerator = numerator*other.denominator + other.numerator*denominator;
        addFrac.toLowestTerms();
        return addFrac;
    }

    /**
     * Subtracts the other Fraction from current Fraction
     *
     * @param other Fraction instance to subtract from current Fraction
     * @return      new Fraction as a result of subtracting
     */
    Fraction subtract(Fraction other){
        Fraction subtrFrac = new Fraction();
        subtrFrac.denominator = denominator*other.denominator;
        subtrFrac.numerator = numerator*other.denominator - other.numerator*denominator;
        subtrFrac.toLowestTerms();
        return subtrFrac;
    }

    /**
     * Multiplies the other Fraction and current Fraction
     *
     * @param other Fraction instance to multiply by current Fraction
     * @return      new Fraction as a result of multiplying
     */
    Fraction multiply(Fraction other){
        Fraction multFrac = new Fraction();
        multFrac.numerator = numerator*other.numerator;
        multFrac.denominator = denominator*other.denominator;
        multFrac.toLowestTerms();
        return multFrac;
    }

    /**
     * Divides the current Fraction by other Fraction
     * Throws new IllegalArgumentException if user tries to divide by zero.
     *
     * @param other Fraction instance, current Fraction is divided by
     * @return      new Fraction as a result of dividing
     */
    Fraction divide(Fraction other) {
        if (other.numerator == 0){
            throw new IllegalArgumentException();
        }
        Fraction divFrac = new Fraction();
        divFrac.numerator = numerator*other.denominator;
        divFrac.denominator = denominator*other.numerator;
        divFrac.toLowestTerms();
        return divFrac;
    }

    /**
     * Checks if Fractions are equal.
     * To properly override the Object class's equals method, takes in an Object.
     *
     * @param other to properly override the Object class's equals method, takes in an Object
     * @return      true only if Object is instance of Fraction and current fraction is equal to other Fraction.
     */
    public boolean equals(Object other){
        if (other instanceof Fraction) {
            Fraction equalFrac = (Fraction) other;
            return (toDouble() == equalFrac.toDouble());
        }
        return false;
    }
}

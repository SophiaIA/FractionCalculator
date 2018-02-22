import java.util.Scanner;
import java.lang.String;

/**
 * Fraction calculator is an assignment from Microsoft: DEV277x "Object Oriented Programming in Java" course.
 * Fraction Calculator asks user for type of fraction operation:
 * <ul>
 *     <li>adding,</li>
 *     <li>subtracting,</li>
 *     <li>multiplying,</li>
 *     <li>dividing,</li>
 *     <li>check if fractions are equal</li>
 * </ul>
 * and 2 fractions (a/b) or integers and returns the result of chosen operation.
 * <p>
 *     Methods in Class:
 *     <ol>
 *         <li>welcome(),</li>
 *         <li>getOperation(),</li>
 *         <li>validFraction(),</li>
 *         <li>getFraction()</li>
 *     </ol>
 * </p>
 * @author Kamila Lastowska
 * @version 1.0
 * @since 1.0
 */
public class FractionCalculator {

    /**
     * Asks for user input until quit.
     *
     * Prints the result of user-chosen operation on user-given fractions.
     *
     * @param args
     * @throws IllegalArgumentException if user asks to divide by zero (see: divide() in Fraction.java)
     */
    public static void main(String[] args) throws IllegalArgumentException {
    welcome();
    String action = "";
        while (!(action.equals("Q") || action.equals("q"))) {
            try {
                action = getOperation();
            Fraction first = getFraction();
            Fraction second = getFraction();
            switch (action) {
                case "+":
                    System.out.println(first.toString() + " + " + second.toString() + " = " + first.add(second).toString());
                    break;
                case "-":
                    System.out.println(first.toString() + " - " + second.toString() + " = " + first.subtract(second).toString());
                    break;
                case "/":
                    System.out.println(first.toString() + " / " + second.toString() + " = " + first.divide(second).toString());
                    break;
                case "*":
                    System.out.println(first.toString() + " * " + second.toString() + " = " + first.multiply(second).toString());
                    break;
                case "=":
                    System.out.println(first.toString() + " = " + second.toString() + " is " + first.equals(second));
                    break;
                }
             }
        catch (IllegalArgumentException e){
                System.out.println("Cannot divide by zero!");
        }
        }
    }

    private static void welcome(){
        System.out.println("This is a Fraction Calculator program.");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
    }

    /**
     * Gets operation-type from user
     *
     * Asks user to give operation type (+,-,/,*,=) until
     * users types 'Q' or 'q' to quit.
     *
     * @return  type of operation as a String
     * @since   1.0
     */
    private static String getOperation(){

        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------");
        String operat;
        do {
            System.out.print("Please enter an operation (+, -, /, *, = or Q to quit):");
            operat = input.nextLine();
            if (operat.equals("Q")||operat.equals("q")){
                System.exit(0);
            }
            }
        while (!operat.equals("+") && !operat.equals("-") && !operat.equals("=") && !operat.equals("/") && !operat.equals("*"));
        return operat;
    }

    /**
     * Validates a fraction
     *
     * Checks if user input matches wanted pattern.
     * Pattens: integer (with optional '-' sign) or integer/integer (with optional '-' sign),
     * where the number after '/' sign is different than 0 (but can be ex. 0099).
     *
     * @param frac   String to match the pattern.
     * @return       true if frac matches the pattern, false otherwise
     */
    private static boolean validFraction(String frac){

        return ((frac.matches("-?\\d+")) || (frac.matches("-?\\d+/-?[0-9]*[1-9][0-9]*$")));
    }

    /**
     * Asks user for a valid fraction (a/b) or integer
     *
     * @return  new instance of Fraction, with one or two parameters
     */
    private static Fraction getFraction(){
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        Scanner input = new Scanner(System.in);
        String fracString = input.nextLine();
        while (!validFraction(fracString)){
            System.out.print("Please enter a valid fraction (a/b) or (a): ");
            fracString = input.nextLine();
        }
        if (fracString.contains("/")){
            String[] parts = fracString.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            return new Fraction(num,den);
        }
        else{
            int num = Integer.parseInt(fracString);
            return new Fraction(num);
        }
    }
}

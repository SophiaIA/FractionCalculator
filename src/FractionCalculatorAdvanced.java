import java.util.Scanner;
import java.lang.String;

/**
 * Fraction calculator advanced is an assignment from Microsoft: DEV277x "Object Oriented Programming in Java" course.
 * Fraction Calculator Advanced asks user for 2 fractions (or integers) and operation-type in a single line.
 * <p>
 *     Methods in Class:
 *     <ol>
 *         <li>welcome(),</li>
 *         <li>getOperation(),</li>
 *         <li>validOperation(),</li>
 *         <li>splitOperation(),</li>
 *         <li>getFraction().</li>
 *     </ol>
 * </p>
 * @author Kamila Lastowska
 * @version 1.0
 * @since 1.0
 */
public class FractionCalculatorAdvanced {
    public static void main(String[] args) throws IllegalArgumentException {
        welcome();
        String action = "";
        while (!(action.equals("Q") || action.equals("q"))) {
            try {
                action = getOperation();
                String[] parts = splitOperation(action);
                Fraction first = getFraction(parts[0]);
                Fraction second = getFraction(parts[2]);

                switch (parts[1]) {
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
                    default:
                        System.out.println("Operations are: +, -, *, / or =");
                        break;
                }

            }
            catch (IllegalArgumentException e){
                System.out.println("Cannot divide by zero!");
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input");
            }
        }
    }

    private static void welcome(){
        System.out.println("This is a Fraction Calculator program.");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Valid operation are of the form \"[FRAC] [OPERATION] [FRAC]\".");
        System.out.println("[FRAC] can be either a single integer or two integers separated by \"/\"");
        System.out.println("[OPERATION] can be +, -, *, / or =.");
    }

    /**
     * Asks user to give operation in form of [FRAC] [OPERATION] [FRAC]
     * where FRAC can be type either a single integer or two integers separated by '/'
     * and OPERATION can be +, -, *, / or =,
     * until users types 'Q' or 'q' to quit.
     *
     * @return  two fractions and operation as one String
     * @since   1.0
     */
    private static String getOperation(){
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------");
        String operat;
        do {
            System.out.print("Please enter an operation (q to quit)[FRAC] [OPERATION] [FRAC]: ");
            operat = input.nextLine();
            if (operat.equals("Q")||operat.equals("q")){
                System.exit(0);
            }
        }
        while (!(validOperation(operat)));

        return operat;
    }

    /**
     * Validates user input
     *
     * Checks if user input matches wanted pattern.
     * Patten: [FRAC] [OPERATION] [FRAC]
     * pattern explanation: (-?\\d+) -digit or digits with optional'-' sign
     *              | ((-?\d+/-?[0-9]*[1-9][0-9]*) -OR a/b pattern, where a,b=digits, with optional'-' sign, where b!=0
     *              (\\s\\W\\s) -single whitespace followed by single non-word char followed by single whitespace
     *              $ -end of line
     *
     * @param frac   String to match the pattern.
     * @return       true if frac matches the pattern, false otherwise
     */
    private static boolean validOperation(String frac){
        return (frac.matches("(((-?\\d+)|(-?\\d+/-?[0-9]*[1-9][0-9]*))(\\s\\W\\s)((-?\\d+)|(-?\\d+/-?[0-9]*[1-9][0-9]*$)))|(\\w)"));
    }

    /**
     * Splits user input into 3 parts (whitespace is separator)
     *
     * usage: to get fractions and operation-type
     * parts[0]=>[FRAC], parts[1]=>[OPERATION], parts[2]=>[FRAC]
     *
     * @param operat    String to be divided, separator - whitespace
     * @return          returns array of Strings
     */
    private static String[] splitOperation(String operat){
        //split String to parts by whitespaces. parts[0]=>[FRAC], parts[1]=>[OPERATION], parts[2]=>[FRAC]
        return operat.split("\\s");
    }

    /**
     * Converts String to fraction.
     *
     * Checks if string is numeric-only or contains '/' (if it's a integer or fraction a/b)
     *
     * @param operat String to be converted to Fraction
     * @return  new instance of Fraction, with one or two parameters
     */
    private static Fraction getFraction(String operat){
        if (operat.contains("/")){
            String[] parts = operat.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            return new Fraction(num, den);
        }
        else {
            int num = Integer.parseInt(operat);
            return new Fraction(num);
        }
    }

}

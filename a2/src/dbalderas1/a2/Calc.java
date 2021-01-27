package dbalderas1.a2;

/**
 * Calc class used by Main
 * @author Diana Balderas
 * @version 1.0
 */

public class Calc {

    /**
     * Static function that is use to evaluate a math problem
     * @param line String input that is a math problem
     * @return String that is the answer to the math problem
     */
    public static String evaluate(String line) {
        double partA, partB;

        String[] parts = line.split(" ");

        // Make sure there are three parts to the expression
        if(parts.length != 3) {
            return "Invalid Input";
        }
        //
        // Make sure parts[0] is a double
        try {
            partA = Double.parseDouble(parts[0]);
        } catch (java.lang.NumberFormatException ex) {
            return "Invalid Input";
        }

        // Make sure parts[1] is a math operator
        if(!parts[1].equals("+") && !parts[1].equals("-") && !parts[1].equals("*") && !parts[1].equals("/")) {
            return "Invalid Input";
        }

        // Make sure parts[2] is a double
        try {
            partB = Double.parseDouble(parts[2]);
        } catch (java.lang.NumberFormatException ex) {
            return "Invalid Input";
        }

        // If we make it here the input is good
        switch(parts[1]) {
            case "+":
                return Double.toString(partA + partB);
            case "-":
                return Double.toString(partA - partB);
            case "*":
                return Double.toString(partA * partB);
            case "/":
                return Double.toString(partA / partB);
        }
        return "Invalid Input";
    }
}

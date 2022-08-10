import java.util.Scanner;
import java.lang.Math;

public class Calculator {

    public static void main(String[] args){

        // Variables
        double number1, number2, result;
        char symbol;
        char choice = 'y';

        // Scanner object
        Scanner sc = new Scanner(System.in);

        while(choice == 'y' || choice == 'Y'){
        // User prompts and inputs
            System.out.println("Input the first number: ");
            number1 = sc.nextDouble();
            System.out.println("Input the mathematical symbol you want to use, +, -, /, *, %, ^: ");
            symbol = sc.next().charAt(0);
            System.out.println("Input the second number: ");
            number2 = sc.nextDouble();
            result = mathematics(number1, symbol, number2);

            System.out.println("The result of equation: " + number1 + " " + symbol + " " + number2 + " is: " + result);
            
            // Ask user if they want to continue making equations.
            System.out.println("Do you want to enter a new formula? Press y or Y for yes, or anything else to exit program.");
            choice = sc.next().charAt(0);
            if(choice != 'y' && choice != 'Y'){
                System.exit(0);
            }
        }
        sc.close();
    }

    public static double mathematics(double number1, char symbol, double number2){
        // Switch statements based on the symbol utilized
        switch(symbol){
            case '+': return number1 + number2;
            case '-': return number1 - number2;
            case '/': return number1 / number2;
            case '*': return number1 * number2;
            case '%': return number1 % number2;
            case '^': return Math.pow(number1, number2);
            default: System.out.println("Incorrect inputs, try again.");
            break;
        }
        return 0;
    }
}

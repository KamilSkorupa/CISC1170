import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.lang.Math.*;

public class program1 {


    public static void main(String[] args) throws Exception{

        int number;

        // Creates Scanner and PrintStream objects to read in and out files
        Scanner sc = new Scanner(new File("inFile.txt"));
        PrintStream ps = new PrintStream("outFile.txt");

        // Call each method passing Scanner and PrintStream objects while integers are available
        while(sc.hasNextInt()){

            number = sc.nextInt();

            factorial(number, ps);
            sumOfSquares(number, ps);
            fibonacci(number, ps);
        }
     }

     public static void factorial(int x, PrintStream ps){
        
        int num = 1;
         // Iterates through for loop and calculates
        for (int i = 1; i <= x; i++){
            num = num * i;
        }
        // Print to console and to file
        System.out.println("The factorial of " + x + " is: " + num);
        ps.println("The factorial of " + x + " is: " + num);
     }

     public static void sumOfSquares(int y, PrintStream ps){

        int num = 0;
         // Iterates through for loop and calculates
        for (int i = 1; i <= y; i++){
            num += Math.pow(i, 2);
        }
        // Print to console and to file
        System.out.println("The sum of squares of " + y + " is: " + num);
        ps.println("The sum of squares of " + y + " is: " + num);
     }

    public static void fibonacci(int z, PrintStream ps){

        int first = 0;
        int second = 1;
        int sum;
        
        sum = first + second;
        for(int i = 2; i <= z; i++){
        // Initialize first fibonacci

        first = second;
        second = sum;
        sum = second + first;
        }
        System.out.println("The fibonacci of " + z + " is: " + sum);
        ps.println("The fibonacci of " + z + " is: " + sum);
    }



















}
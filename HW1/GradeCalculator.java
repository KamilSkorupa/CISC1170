import java.lang.Math;
import java.util.Scanner;
import java.io.*;

public class GradeCalculator {
    public static void main(String[] args) {
    //Scanner object to read-in user input
    Scanner sc = new Scanner(System.in);
    
    //System prompts to input values for each variable and assignment of values via scanner object
    System.out.print("Enter Homework grade: ");
    double homework = sc.nextDouble();
    System.out.print("Enter CodeLab grade: ");
    double codeLab = sc.nextDouble();
    System.out.print("Enter Midterm 1 grade: ");
    double midterm1 = sc.nextDouble();
    System.out.print("Enter Midterm 2 grade: ");
    double midterm2 = sc.nextDouble();
    System.out.print("Enter Final Test grade: ");
    double testFinal = sc.nextDouble();
    
    //Program calculates and prints sum of inputs
    double sum = homework + codeLab + midterm1 + midterm2 + testFinal;
    System.out.println("The total score is: " + sum);
    }
}
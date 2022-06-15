import java.lang.Math;
import java.util.Scanner;
import java.io.*;

public class RectangleStat {
    public static void main(String[] args) {
    //Scanner object to read-in user input
    Scanner sc = new Scanner(System.in);
    
    //System prompts to input values for each variable, and assignment of values via scanner object
    System.out.print("Enter width of rectangle: ");
    double width = sc.nextDouble();
    System.out.print("Enter length of rectangle: ");
    double length = sc.nextDouble();
    
    //Program calculates and prints width, length, area and perimeter of rectangle to 3 decimal places
    double perimeter = width*2 + length*2;
    double area = width * length;
    System.out.println("When one side is " + Math.round(width * 1000d) / 1000d + " and the other side is " + Math.round(length * 1000d) / 1000d + ", the area is " + Math.round(area * 1000d) / 1000d + " and the perimeter is " + Math.round(perimeter * 1000d) / 1000d);
    }
}
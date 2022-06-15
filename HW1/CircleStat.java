import java.lang.Math;
import java.util.Scanner;
import java.io.*;

public class CircleStat {
    public static void main(String[] args) {
    //Scanner object to read-in user input
    Scanner sc = new Scanner(System.in);
    
    //System prompts to input value for radius, and assignment of value via scanner object
    System.out.print("Enter radius of circle: ");
    double radius = sc.nextFloat();
    
    //Program calculates and prints radius, circumference, and area of circle to 5 decimal places
    double circumference = 2 * Math.PI * radius; 
    double area = Math.PI * Math.pow(radius, 2);
    System.out.println("When the radius is " + Math.round(radius * 100000d) / 100000d + " the circumference is " + Math.round(circumference * 100000d) / 100000d + ", and the area is " + Math.round(area * 100000d) / 100000d);
    }
}
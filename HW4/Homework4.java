import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Homework4 {
        public static void main (String[] args) {

            basicArray();
            // Initializes array with 10 strings
            String[] arrA = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eight", "ninth", "tenth"};
            // Calls method and passes array
            printElements(arrA);
            
            // Creates date object to hold dates, and time array of time LocalDateTime of size 3
            LocalDateTime date = LocalDateTime.now();
            LocalDateTime[] time = new LocalDateTime[3];
            // Initializes each index to 3 consecutive days and calls method passing array
            time[0] = date;
            time[1] = date.plusDays(1);
            time[2] = date.plusDays(2);
            printElements(time);

            // Creates two dimensional array, prints # of columns and rows, and its elements in matrix form
            final int[][] values = new int[10][20];
            System.out.println("\n" + "The number of rows in values array is: " + values.length);
            System.out.println("The number of columns in values array is: " + values[0].length + "\n");
            printElements(values);
        }

        public static void basicArray (){

            // Creates array and allocates it to memory
            int[] nums = new int[50];
            // Stores values 1 to 50 in nums
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i+1;
            }
            // Adds 10 to each entry in nums and prints
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums[i]+10;
                System.out.println(nums[i]);  
            }
            System.out.println("\n");
        }
        
        // Method which prints 10 strings
        public static void printElements (String[] a){

            // Iterates over passed array, checks if elements strings and prints them if so
            for (int i = 0; i < a.length; i++){
                if (a[i] instanceof String){
                System.out.println(a[i]);
                }
            }
            System.out.println("\n");
        }
        
        // Method which prints a 10 x 20 matrix
        public static void printElements (int[][] b) {
            
           int x = 0;
            // Rows matrix loop, outer
            for (int row = 0; row < b.length; row++){
                // Columns matrix loop, inner. Adds 1 to every element and prints matrix
                for (int col = 0; col < b[row].length; col++){
                    x +=1;
                    b[row][col] = x;
                    System.out.printf("%4d", b[row][col]);
                }
                System.out.println();
            }
        }

        // Method which prints 3 time objects
        public static void printElements (LocalDateTime[] c) {

            // Loop iterates through passed array, checks if elements are of time object type and prints them as string objects
            for (int i = 0; i < c.length; i++) {
                if (c[i] instanceof LocalDateTime){
                System.out.printf(c[i].toString() + "\n");
                }
            }
        }
}
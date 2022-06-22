import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math;
import java.util.Arrays;
import java.lang.Object;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;

public class Accounting {

    public static void main(String[] args) throws FileNotFoundException
    {
        // Create 2 variables in order to first store entire file in 1 line, and then to count each item delimited by a comma in the line
        String strLine = "";
        String str_data = "";
        // Create a BufferedReader object through which to read in the input file
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kamil\\CISC1170\\test_file.txt"));
            // Create the file's string variables. The int and double variables will be created below when the strings are  parsed accordingly
            String firstDate, lastDate;
            // While strLine is not null (file contains inputs) then str_data gets the file in 1 line as strLine converts the multiline file into one line
            while (strLine != null)
                {
                    if (strLine == null)
                        break;
                    str_data += strLine;
                    strLine = br.readLine();
                }
                // The items of the line are now split by the delimiter "," and consecutively stored into array tokens.
                String[] tokens = str_data.split(",");
                // The variable total is declared in order to later store the sum of the items from array totals.
                // The five arrays are created in order to hold the double values from the string in an array
                Double total = 0.0;
                double[] totals = new double[tokens.length];
                double[] itemPrice = new double[tokens.length];
                double[] itemDiscount = new double[tokens.length];
                double[] discountedValue = new double[tokens.length];
                double[] newItemPrice = new double[tokens.length];

                // The max, min, maxItem, and minItem arrays are created in order to store the corresponding items and prices
                // max and min are also parsed as double, and initialized to the 1st price in the tokens array to be compared later
                double[] max = new double[tokens.length];
                max[0] = Double.parseDouble(tokens[6]);
                double[] min = new double[tokens.length];
                min[0] = Double.parseDouble(tokens[6]);
                int[] maxItem = new int[tokens.length];
                int[] minItem = new int[tokens.length];

                // For loop which sums the item prices, which start at index 6, and stores sum in variable total
                for (int i = 6; i < totals.length-1; i+=4){
                    totals[i] = Double.parseDouble(tokens[i]);
                    total += totals[i];
                }

                // For loop which first parses item prices into doubles, which start at index 6 and appear every 4th index
                // Then the discount is parsed, which appears 1 index later
                // Then the discounted Value is calculated for each item
                // In order to then use it to calculate the newItemPrice
                // All values are stored in corresponding arrays in order to access them when needed
                for (int i = 6; i < itemPrice.length-1; i+=4){
                    itemPrice[i] = Double.parseDouble(tokens[i]);
                    itemDiscount[i+1] = Double.parseDouble(tokens[i+1]);
                    discountedValue[i+1] = ((itemDiscount[i+1] * itemPrice[i]));
                    newItemPrice[i] = (itemPrice[i] - discountedValue[i+1]);
                // Once the newItemPrices are calculated, 2 if statements do a simple comparison of max and min
                // As well as initializing which item # the prices belong to, which always appears 1 index prior
                        if(newItemPrice[i] > max[0]) {
                            max[0] = newItemPrice[i];
                            maxItem[0] = Integer.parseInt(tokens[i-1]);
                    }
                        if(newItemPrice[i] < min[0]){
                            min[0] = newItemPrice[i];
                            minItem[0] = Integer.parseInt(tokens[i-1]);
                    }
                }
                // The variables firstDate and lastDate are initialized to be the 4th index (the 1st appearance of a date) and the 4th to last item of line
                firstDate = tokens[4];
                lastDate = tokens[tokens.length-4];
                // The tax is pre-set and taxTotal calculated, as well as format established for displaying a double value
                double tax = 0.08875;
                double taxTotal = total * tax;
                DecimalFormat f = new DecimalFormat("#0.00");
                // Total is calculated by adding the sum of all items to the sum multiplied by the fractional tax
                total = total + (total * tax);

                // Instantiate PrintStream object in order to output println to either a file or a console
                PrintStream file = new PrintStream(new File("reports.txt"));
                PrintStream console = System.out;
                
                // println is set to print to file
                System.setOut(file);

                System.out.println("Report from " + firstDate + " to " + lastDate + "\n" + "The total is $" + String.format("%.2f", total) + "\n" + "The tax is $" + f.format(taxTotal) + "\n" + "The highest priced item is #" + maxItem[0] + " at $" + f.format(max[0]) + "\n" + "The lowest priced item is #" + minItem[0] + " at $" + f.format(min[0]));

                // println is set to print to console
                System.setOut(console);

                System.out.println("REPORT IS COMPLETED. File name generated is reports.txt");
                // BufferedReader is closed
                br.close();
        }
        // Exceptions if input file is not found or cannot be read
        catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
        
    }
}
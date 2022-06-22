import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math;
import java.util.Arrays;
import java.lang.Object;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;

public class TF {

    public static void main(String[] args) throws FileNotFoundException
    {
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        String str_data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kamil\\CISC1170\\test_file.txt"));
            String firstDate, lastDate, sum, highPrice, lowPrice, itemNum;
            while (strLine != null)
                {
                    if (strLine == null)
                        break;
                    str_data += strLine;
                    strLine = br.readLine();
                }
                String[] tokens = str_data.split(",");
                Double total = 0.0;
                double[] totals = new double[tokens.length];
                double[] itemPrice = new double[tokens.length];
                double[] itemDiscount = new double[tokens.length];
                double[] discountedValue = new double[tokens.length];
                double[] newItemPrice = new double[tokens.length];

                double[] max = new double[tokens.length];
                max[0] = Double.parseDouble(tokens[6]);
                double[] min = new double[tokens.length];
                min[0] = Double.parseDouble(tokens[6]);
                int[] maxItem = new int[tokens.length];
                int[] minItem = new int[tokens.length];

                for (int i = 6; i < totals.length-1; i+=4){
                    totals[i] = Double.parseDouble(tokens[i]);
                    total += totals[i];
                }

                for (int i = 6; i < itemPrice.length-1; i+=4){
                    itemPrice[i] = Double.parseDouble(tokens[i]);
                    itemDiscount[i+1] = Double.parseDouble(tokens[i+1]);
                    discountedValue[i+1] = ((itemDiscount[i+1] * itemPrice[i]));
                    newItemPrice[i] = (itemPrice[i] - discountedValue[i+1]);
                        if(newItemPrice[i] > max[0]) {
                            max[0] = newItemPrice[i];
                            maxItem[0] = Integer.parseInt(tokens[i-1]);
                    }
                        if(newItemPrice[i] < min[0]){
                            min[0] = newItemPrice[i];
                            minItem[0] = Integer.parseInt(tokens[i-1]);
                    }
                }
                firstDate = tokens[4];
                lastDate = tokens[tokens.length-4];
                double tax = 0.08875;
                double taxTotal = total * tax;
                DecimalFormat f = new DecimalFormat("#0.00");

                total = total + (total * tax);

                // Instantiate File class

                PrintStream file = new PrintStream(new File("reports.txt"));
                PrintStream console = System.out;
                System.setOut(file);

                System.out.println("Report from " + firstDate + " to " + lastDate + "\n" + "The total is $" + String.format("%.2f", total) + "\n" + "The tax is $" + f.format(taxTotal) + "\n" + "The highest priced item is #" + maxItem[0] + " at $" + f.format(max[0]) + "\n" + "The lowest priced items is #" + f.format(minItem[0]) + " at $" + min[0]);

                System.setOut(console);

                System.out.println("REPORT IS COMPLETED. File name generated is reports.txt");


                br.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
        
    }
}
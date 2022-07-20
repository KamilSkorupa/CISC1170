import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Homework5 {

    public static void main(String[] args) throws Exception {

        // Declare variables used
        double speed, time;
        String line, vehicle;

        // Read in vehicle file data
        Scanner sc = new Scanner(new File("vehicleFile.txt"));
        PrintStream ps_file = new PrintStream(new File("reportFile.txt"));
        line = sc.nextLine();

        sc.useDelimiter(",|\\n");

        // While file is not empty
        while(sc.hasNext()){

            // Read in vehicle name, its speed and time traveled 
            vehicle = sc.next();
            speed = Double.parseDouble(sc.next());
            time = Double.parseDouble(sc.next());

            // Call distance method to return distance traveled
            distance(vehicle, speed, time);
            // Call saveAsFile method to print to file
            saveAsFile(ps_file, vehicle, speed, time);
        }
        // CLose scanner and PrintStream
        sc.close();
        ps_file.close();
    }

    // Distance method reads in vehicle's speed and time
    public static double distance (String v, double s, double t){

    // Print error if speed is < 0 or time is < 1
        if (s < 0 || t < 1){
            System.out.println("Error: Speed cannot be less than 0 and time cannot be less than 1");
        }
        else {
            System.out.println("");
            System.out.println("Vehicle: " + v);
            System.out.println("Hour Distance Traveled");
            // Loop for distance traveled each hour
            for (int i = 1; i <= t; i++){
                System.out.println(i + "\t\t" + i*s);
            }
        }
        // Return distance traveled
        return s * t;
    }

    // Print to file method
    public static void saveAsFile (PrintStream f, String v, double s, double t){

        f.println("");
        f.println("Vehicle: " + v);
        f.println("Hour Distance Traveled");

        // Loop for distance traveled each hour for each vehicle
        for (int i = 1; i <= t; i++){
            f.println(i + "\t" + i*s);
        }
    }
}
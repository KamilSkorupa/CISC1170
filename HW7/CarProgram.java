import java.util.Scanner;
import java.io.File;
import java.lang.Math;

public class CarProgram {
    public static void main(String[] args)throws Exception{

        // Method variables
        int modelYear, modelY, secs, maxSpeed;
        int carSpeed = 0;
        double fuel = 10.0;
        double allowedFuel;
        double odometer = 999998.0;
        double distance = 0.0;
        double distance2 = 0.0;
        String carMake, makeOfCar;
        char decision = 'c';
        char choice = 'q';

        // Scanner object
        Scanner sc = new Scanner(System.in);
        
        // Get inputs from user
        System.out.println("Enter a model year: ");
        modelYear = sc.nextInt();
        System.out.println("Enter a make: ");
        carMake = sc.next();
        System.out.println("Enter car's max speed: ");
        maxSpeed = sc.nextInt();

        // Car objects
        Car c = new Car(modelYear, carMake, maxSpeed, fuel);
        Car c2 = new Car(decision);

        // Fuel Gauge object
        Car.FuelGauge f = new Car.FuelGauge(fuel, distance2, carSpeed);

        // Odometer object
        Car.Odometer o = new Car.Odometer(distance, odometer);

        // DynamicArray object
        Car.DynamicArray arr = new Car.DynamicArray(carSpeed);

        modelY = c.getYearModel();
        carSpeed = c.getSpeed(carSpeed);
        makeOfCar = c.getMake();
        //odometer = o.getOdometer(odometer);

        System.out.printf("%15s %15s %5s %10s %10s %7s\n","Make", "Model year", "MPH", "Max MPH", "Odometer", "  Fuel gal left");
        System.out.format("%15s %12s %8s %8s %12s %10.1f\n", makeOfCar, modelY, carSpeed, maxSpeed, odometer, fuel);
        System.out.println();
        System.out.println("FOR DEMONSTRATION PURPOSES THE CAR SPEED IS ASSUMED TO CHANGE ONLY EVERY 30 SECONDS, UNLESS DRIVE TIME IS CHOSEN");

        // User choice between an autonomous loop program or a manual program
        while(choice != 'l' || choice != 'm'){
        System.out.println("Do you want to operate the car manually or generate a loop? m = manually, l = loop");
        choice = sc.next().charAt(0);

            // First loops the fuel, from the current level to the max by calling the loopRefuel method
            if(choice == 'l'){
                carSpeed = 0;
                while (fuel < 15.0 && fuel >= 0.0){
                    allowedFuel = 15.0 - fuel;
                    for (int i = 0; i < allowedFuel; i++){
                        fuel += f.loopRefuel();
                        System.out.println("Fuel is now at " + fuel + " gallons");
                    }
                }
                // Checks if fuel tank isn't empty and loops through the acceleration method until maxSpeed, followed by the brake method until 0. Continues until gas tank is empty.
                while (fuel > 0.0){
                    if(carSpeed > 0){
                        carSpeed = 0;
                    }
                        for(int i = 0; i < maxSpeed/5; i++){
                            if (fuel > 0.0 && carSpeed < maxSpeed){
                                carSpeed = c.accelerate(carSpeed);
                                distance2 = arr.addDistance(carSpeed);
                                distance += distance2;
                                odometer += o.odometerAdjuster(distance, odometer);
                                fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                                    // Due to order to variables and methods, the last distance may decrease fuel below 0, thus if loop checks this and resets fuel to 0 in order to run new loop from empty. Breaks out and exits to loop or manual choice prompt.
                                    if(fuel <= 0.0){
                                        System.out.println("This iteration would decrease the fuel below 0. The trip is over.");
                                        fuel = 0.0;
                                        break;
                                    }
                                System.out.printf("%15s %11s %5s %5s\n","|Miles in last 30secs|", "| Odometer |", "| MPH |", "|Fuel gal left|");
                                System.out.format("%20.2f %13.2f %6s %12.2f\n", distance2, odometer, carSpeed, fuel);
                            }
                        }
                    for(int i = 0; i < maxSpeed/5; i++){
                        if (fuel > 0.0){
                        carSpeed = c.brake(carSpeed);
                        distance2 = arr.addDistance(carSpeed);
                        distance += distance2;
                        odometer += o.odometerAdjuster(distance, odometer);
                        fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                            // Due to order to variables and methods, the last distance may decrease fuel below 0, thus if loop checks this and resets fuel to 0 in order to run new loop from empty. Breaks out and exits to loop or manual choice prompt.
                            if(fuel <= 0.0){
                                System.out.println("This iteration would decrease the fuel below 0. The trip is over.");
                                fuel = 0.0;
                                carSpeed = 0;
                                break;
                            }
                        System.out.printf("%15s %11s %5s %5s\n","|Miles in last 30secs|", "| Odometer |", "| MPH |", "|Fuel gal left|");
                        System.out.format("%20.2f %13.2f %6s %12.2f\n", distance2, odometer, carSpeed, fuel);
                        }
                    }
                }
            }

            // If user chooses a manual program, a while loop runs between carSpeed 0 and maxSpeed, until user terminates program.
            // User decision leads to 5 different methods, accelerate, brake, continue, drive for X seconds, or terminate program.
            // 
            else if(choice == 'm'){
                while(carSpeed >= 0 || carSpeed <= maxSpeed){
                    if(carSpeed == 0){
                        System.out.println("Car: STOPPED. Can only accelerate or maintain stopped position. a = accelerate, c = continue, z = terminate program.");
                        decision = sc.next().charAt(0);
                            if(decision == 'z'){
                                System.exit(0);
                                // Brake (or accelerate below) followsthe following logic: carSpeed is passed into decision, which passes carSpeed into appropriate method in order to increment or decrement speed
                                // The new speed is passed into addDistance array and a distance is calculated based on speed over 30 seconds
                                // This 30 second distance is added to overall distance
                                // The odometer is incremented by passing overall distance, and current odometer, into odometerAdjuster method, which will add the distance to the odometer
                                // and store the new value in an array
                                // Fuel is decremented by passing fuel, the 30 sec distance, and carSpeed to fuelAdjuster, and checking if the current trip distance is allowed with remaining fuel
                                // if not, certain conditions are prompted in order to account for this.
                            } else if (decision != 'b' || decision != 'd'){
                                carSpeed = c2.decision(decision);
                                distance2 = arr.addDistance(carSpeed);
                                distance += distance2;
                                odometer += o.odometerAdjuster(distance, odometer);
                                fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                                System.out.printf("%15s %11s %5s %5s\n","|Miles in last 30secs|", "| Odometer |", "| MPH |", "|Fuel gal left|");
                                System.out.format("%20.2f %13.2f %6s %12.2f\n", distance2, odometer, carSpeed, fuel);
                            } else continue;
                        } else if(carSpeed > 0 && carSpeed <= maxSpeed - 5){
                            System.out.println("Do you wish to accelerate, brake, continue at current speed, or drive for X seconds at current speed? a = accelerate, b = brake, c = continue, d = drive for x seconds, z = terminate program.");
                            decision = sc.next().charAt(0);
                            if(decision == 'z'){
                                System.exit(0);
                            } else if (decision == 'd'){
                                // If drive for x seconds is chose, the same logic follows as in accelerate or brake, except a seconds prompt check happens first, by passing current fuel and current speed to
                                // secsCheck method which checks allowed # of secs. If it is exceeded, prompts user to input another amount.
                                System.out.println("How many seconds do you want to drive at current speed?");
                                secs = f.secsCheck(fuel, carSpeed);
                                carSpeed = c2.decision(decision);
                                distance2 = arr.addDistance(carSpeed) * (secs/30.0);
                                distance += distance2;
                                odometer += o.odometerAdjuster(distance, odometer);
                                fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                                System.out.printf("%15s %11s %15s %10s %5s\n","|MPH maintained|", "|For # secs|", " |Miles travelled|", "| Odometer |", "|Fuel gal left|");
                                System.out.format("%15s %9s %17.2f %16.2f %11.2f\n", carSpeed, secs, distance2, odometer, fuel);
                            } else {
                                carSpeed = c2.decision(decision);
                                distance2 = arr.addDistance(carSpeed);
                                distance += distance2;
                                odometer += o.odometerAdjuster(distance, odometer);
                                fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                                System.out.printf("%15s %11s %5s %5s\n","|Miles in last 30secs|", "| Odometer |", "| MPH |", "|Fuel gal left|");
                                System.out.format("%20.2f %13.2f %6s %11.2f\n", distance2, odometer, carSpeed, fuel);
                            }
                        } else if(carSpeed == maxSpeed){
                            System.out.println("Car: AT MAX SPEED. Can only brake or maintain current speed. b = brake, c = continue, d = drive for x seconds, z = terminate program.");
                            decision = sc.next().charAt(0);
                            if(decision == 'z'){
                                System.exit(0);
                            } else if (decision != 'a' && decision != 'd'){
                                carSpeed = c2.decision(decision);
                                distance2 = arr.addDistance(carSpeed);
                                distance += distance2;
                                odometer += o.odometerAdjuster(distance, odometer);
                                fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                                System.out.printf("%15s %11s %5s %5s\n","|Miles in last 30secs|", "| Odometer |", "| MPH |", "|Fuel gal left|");
                                System.out.format("%20.2f %13.2f %6s %11.2f\n", distance2, odometer, carSpeed, fuel);
                            } else if (decision == 'd'){
                                System.out.println("How many seconds do you want to drive at current speed?");
                                secs = f.secsCheck(fuel, carSpeed);
                                carSpeed = c2.decision(decision);
                                distance2 = arr.addDistance(carSpeed) * (secs/30.0);
                                distance += distance2;
                                odometer += o.odometerAdjuster(distance, odometer);
                                fuel -= f.fuelAdjuster(fuel, distance2, carSpeed);
                                System.out.printf("%15s %11s %15s %10s %5s\n","|MPH maintained|", "|For # secs|", " |Miles travelled|", "| Odometer |", "|Fuel gal left|");
                                System.out.format("%15s %9s %17.2f %16.2f %11.2f\n", carSpeed, secs, distance2, odometer, fuel);
                            } else continue;
                        }
                }
            } else System.out.println("Invalid input. Choose again.");
        }
        sc.close();
    }
}
// Car class
class Car{

    // class variables
    private int yearModel, speed, maxSpeed;
    public double fuel;
    public double usedFuel = FuelGauge.getUsedFuel();
    private String make;
    private char x, decision;

    // Scanner object
    Scanner sc = new Scanner(System.in);

    // No arg constructor
    public Car(){
    }
    // Constructor accepting model, make, and max speed arguments, assigning to object's yearModel and make fields. Sets speed field to 0
    public Car(int yearModel, String make, int maxSpeed, double fuel){
        try {
            if (yearModel >= 1900 && yearModel <= 2023){
                this.yearModel = yearModel;
                } else {
                    this.yearModel = 2023;
                    throw new MyException("Model Year must be between 1899 and 2024. Defaults to 2023. Run program again.");
                }
                } catch (Exception e) {
                    System.out.println(e);
                }
            this.make = make;
            this.maxSpeed = maxSpeed;
            this.fuel = fuel;
            speed = 0;
    }
    // Constructor checks that character entered into decision or refuel is allowed, else it defaults to continue.
    public Car(char x){
        this.x = x;

        try {
            if (this.x == 'a') {
                this.x = 'a';
                } else if (this.x == 'b') {
                    this.x = 'b';
                } else if (this.x == 'c') {
                    this.x = 'c';
                } else if (this.x == 'd') {
                    this.x = 'd';
                } else if (this.x == 'f'){
                    this.x = 'f';
                } else {
                    this.x = 'c';
                    throw new MyException("Decision must be a, b, c, d, or z. Defaults to c - continue");
                }
                } catch (Exception e) {
                    System.out.println(e);
                }
    }
    // Accessor method for model year
    public int getYearModel(){
        return yearModel;
    }
    // Accessor method for speed
    public int getSpeed(int speed){
        return speed;
    }
    // Accessor method for make
    public String getMake(){
        return make;
    }
    // Decision method for car movement using switch statements
    public int decision(char x){
        switch(x){
            case 'a': return accelerate(speed);
            case 'b': return brake(speed);
            case 'c': return cont(speed);
            case 'd': return drive(speed);
            default: return cont(speed);
        }
    }
    // Since fuel is a double, a different decision method is created, passing fuel and usedFuel in order to check if fuel will fall below 0 after current trip
    public double decision2(char x){
        switch(x){
            case 'f': return FuelGauge.refuel(fuel, usedFuel);
            default: return FuelGauge.refuel(fuel, usedFuel);
        }
    }

    // Accelerate method adds 5 to speed each time it is called
    public int accelerate(int speed){
        if(speed >= 0 || speed <= maxSpeed - 5){
        return this.speed += 5;
            } else {
                return this.speed += 0;
            }
    }
    // Brake method subtracts 5 from speed each time it is called and decreases fuel
    public int brake(int speed){
        if(speed >= 5 || speed <= maxSpeed){
        return this.speed -= 5;
            } else {
                return this.speed += 0;
            }
    }
    // Continue current speed method
    public int cont(int speed){
        return this.speed + 0;
    }
    // Drive at current speed for X seconds
    public int drive(int speed){
        return this.speed + 0;
    }
// Fuel Gauge class
static class FuelGauge extends Car{

        // Class variables
        public int speed, requestedSecs;
        private double allowedSecs;
        public static double fuel, distance2, newTime, newDist;
        public static double addFuel, usedFuel, newFuel, allowedFuel, minFuel;
        
        // Scanner object
        static Scanner sc = new Scanner(System.in);
        
        // Fuel Gauge contructor
        public FuelGauge(double fuel, double distance2, int speed){
            this.speed = speed;
        }

        // secsCheck method prompts user how many seconds they want to continue driving. If the allowed seconds exceeds the amount of seconds allowed with given fuel (-1 to account for fractions), then
        // a while loop tells user how many seconds are allowed, and prompts for a new input
        public int secsCheck(double fuel, int speed){
            requestedSecs = sc.nextInt();
            allowedSecs = (((fuel * 24.0)/speed)*3600.0) - 1;
            while(requestedSecs > allowedSecs - 1){
                System.out.println("The max amount of seconds allowed is: " + (allowedSecs - 1));
                System.out.println("That distance would use more than a 15 gallon tank and cannot be completed!! Please enter a shorter time to travel at the current speed.");
                requestedSecs = sc.nextInt();
            } return requestedSecs;
        }
        // Fuel gauge adjuster method to calculate how much fuel would be used for the given distance leg. If the trip would exceed 15 gallons, a while loop prompts user for new inputs
        // If the fuel minus usedFuel is 0 or less, user is prompted to select an amount to refuel by
        public double fuelAdjuster(double fuel, double dist, int speed){
            usedFuel = (dist * (1.0/24.0) + (Math.random()/10.0));
            while(usedFuel > 15.0){
                System.out.println("That distance would use more than a 15 gallon tank and cannot be completed. Please enter a shorter time to travel at the current speed.");
                    newTime = sc.nextDouble();
                    newDist = getSpeed(speed) * (newTime/3600.0);
                    usedFuel = (newDist * (1.0/24.0) + (Math.random()/200.0));  
            }
                while(fuel - usedFuel + newFuel <= 0.0){
                    System.out.println("This trip would decrease the fuel to 0 or less.");
                    newFuel = refuel(fuel, usedFuel) + Math.random()/10.0;
                        while(newFuel + fuel - usedFuel > 15.0){
                            allowedFuel = 15.0 - fuel - usedFuel;
                            System.out.printf("This amount is too large for the 15 gal tank! The most that can be added is: %.2f gallons.", allowedFuel);
                            newFuel = refuel(fuel, usedFuel) + Math.random()/10.0;
                        if(fuel - usedFuel + newFuel > 0.0){
                            return ((newFuel * (-1.0)) + usedFuel);
                        }
                        }
                }
                return usedFuel + newFuel;
            }            

        // Refuel method prompts user to input how many gallons they want to refuel by and checks if amount would exceed the gas tank based on current fuel levels
        // If not, it then checks if the fuel user wants to add plus current fuel, minus usedFuel for this trip, would decrease fuel below zero. If so, tells user what the minimum amount to refuel by is
        public static double refuel(double fuel, double usedFuel){
            System.out.println("How many gallons do you want to refuel?");
                addFuel = sc.nextDouble() + Math.random()/10.0;
                if(addFuel + fuel > 15.0){
                    while (addFuel + fuel > 15.0){
                        allowedFuel = 15.0 - fuel;
                        System.out.printf("This amount is too large for the 15 gal tank. The most that can be added is: %.2f gallons.", allowedFuel);
                        System.out.println(" Enter a new amount.");
                        addFuel = refuel(fuel, usedFuel) + Math.random()/10.0;
                    }
                    return addFuel;
                }
                else if(addFuel + fuel - usedFuel <= 0.0){
                    while(addFuel + fuel - usedFuel <= 0.0){
                        minFuel = usedFuel;
                        System.out.printf("This amount of fuel is too small to complete this trip. The least that can be added is: %.2f gallons.", minFuel);
                        addFuel = sc.nextDouble() + Math.random()/10.0;
                    }
                } return addFuel;
                }
        
        // Increments fuel by 1 in the loop choice
        public static double loopRefuel(){
            return 1.0;
        }
        // Fuel gauge getter method
        public double getFuelLevel(){
            return fuel;
        }

        public static double getUsedFuel(){
            return usedFuel;
        }
    } // End FuelGauge class
// Odometer class
static class Odometer extends Car{

    // Class variables
    private double odometer;
    private double odometerArr[];
    private int count2;
    private int sizeOfArray;
    private int speed;
    private double distance;
    private double dist;

    // Odomoter constructor
    public Odometer(double distance, double odometer){
        this.odometer = odometer;
        this.distance = distance;
        this.speed = speed;
        odometerArr = new double[1];
        count2 = 0;
        sizeOfArray = 1;
    }
    // Odometer adjuster method checks if odometer would exceed max for current trip and if so rolls over to zero adding current distance
    public double odometerAdjuster(double distance, double odometer){
        dist = addOdometer(distance);
        if (odometer + dist > 999999.0){
            return (dist - 999999.0);
        }
        else return dist;
    } 
    // Odometer getter method
    public double getOdometer(double distance, double odometer){
        return addOdometer(distance);
    }

    // Dynamic array for the odometer, adding each iteration of change to an array
    public double addOdometer(double distance){
        if(count2 == sizeOfArray){
            growSize();
        }
        odometerArr[count2] = distance;
        count2++;
        if(count2 != 1){
            return (odometerArr[count2 - 1] - odometerArr[count2 - 2]);
                } else { 
                    return (odometerArr[count2 - 1]);
                }
    }
    // Method to grow size of array if size limit reached
    public void growSize(){
        double temp[] = null;
        if(count2 == sizeOfArray)
        {
            temp = new double[sizeOfArray * 2];
            {
                for(int i = 0; i < sizeOfArray; i++){
                    temp[i] = odometerArr[i];
                }
            }
        }
        odometerArr = temp;
        sizeOfArray = sizeOfArray * 2;
    }
} // End Odomoter class

static class DynamicArray extends Car{

    // Class variables
    private int distanceArr[];
    private int count;
    private int sizeOfArray;
    private int carSpeed;

    // Constructor
    public DynamicArray(int carSpeed){
        this.carSpeed = carSpeed;
        distanceArr = new int[1];
        count = 0;
        sizeOfArray = 1;
    }
    // Method to add elements to array
    public double addDistance(int speed){
        if(count == sizeOfArray){
            growSize();
        }
        distanceArr[count] = speed;
        count++;
        if(count > 2){
            // If count is more than 2, then this is the 3rd or more change in speed, thus the distance travelled in the past 30 seconds is
            // 1 second of the avg between the previous speed and the one before that plus 29 seconds of the previous speed
            return (((distanceArr[count - 2] + distanceArr[count - 3])/2) * (1.0/3600.0)) + (distanceArr[count - 2] * (29.0/3600.0));
                // If count is 2, then this is the 2nd change in speed, and thus the distance travelled in the past 30 seconds is 1 second of the 0 to 5mph acceleration plus 29 seconds of the 5mph travel
                } else if (count == 2) { return (((distanceArr[count - 2])/2) * (1.0/3600.0)) + (distanceArr[count - 2] * (29.0/3600.0));
                // If count is 1, then this is the 1st acceleration, and thus the distance travelled in the past 30 seconds is 0.
                } else return 0.0;
    }
    // Method to grow size of array if size limit reached
    public void growSize(){
        int temp[] = null;
        if(count == sizeOfArray)
        {
            temp = new int[sizeOfArray * 2];
            {
                for(int i = 0; i < sizeOfArray; i++){
                    temp[i] = distanceArr[i];
                }
            }
        }
        distanceArr = temp;
        sizeOfArray = sizeOfArray * 2;
    }
} // End DynamicArray class
} // End Car class

// Exeption class extension
class MyException extends Exception { 
    public MyException(String error) {
        super(error);
    }
}
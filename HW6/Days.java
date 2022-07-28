import java.util.Scanner;

public class Days {
    public static void main(String[] args) throws Exception {

        // Method variables
        int days, month, year;
        String monthName;

        // Scanner object
        Scanner sc = new Scanner(System.in);

        // Get inputs from user
        System.out.println("Enter a month (1-12)");
        month = sc.nextInt();
        System.out.println("Enter a year (xxxx)");
        year = sc.nextInt();

        // Month object
        Month monthN = new Month();

        // MonthDays object as object of Month class
        Month.MonthDays monthDay = new Month.MonthDays(month, year);

        // Set month name by calling toString method and passing user month input
        monthName = monthN.toString(month);

        // Set month days by calling getNumberOfDays method and passing user month input
        days = monthDay.getNumberOfDays(month);

        System.out.println(monthName + " " + year + " has " + days + " days.");

        sc.close();
    }
}

class Month {
    
    // class variables
    private int monthNumber;
    private String monthName;

    // monthNumber constructor that sets monthNumber to default 1
    public Month(){
        monthNumber = 1;
    }
    // monthNumber constructor that checks if value is 1 to 12 with exception thrown if not
    public Month(int monthNumber){
        try {
            if (monthNumber >= 1 && monthNumber <= 12){
                this.monthNumber = monthNumber;
            } else {
                monthNumber = 1;
                throw new MyException("Month number must be 1-12");
            }
            } catch (Exception e) {
                System.out.println(e);
            }
    }
    // monthNumber constructor for month name. Checks if input is correct format and throws exception if not
    public Month(String monthName){
        this.monthName = monthName;

        try {
            if (this.monthName.equals("January")) {
                this.monthNumber = 1;
                } else if (this.monthName.equals("February")) {
                    this.monthNumber = 2;
                } else if (this.monthName.equals("March")) {
                    this.monthNumber = 3;
                } else if (this.monthName.equals("April")) {
                    this.monthNumber = 4;
                } else if (this.monthName.equals("May")) {
                    this.monthNumber = 5;
                } else if (this.monthName.equals("June")) {
                    this.monthNumber = 6;
                } else if (this.monthName.equals("July")) {
                    this.monthNumber = 7;
                } else if (this.monthName.equals("August")) {
                    this.monthNumber = 8;
                } else if (this.monthName.equals("September")) {
                    this.monthNumber = 9;
                } else if (this.monthName.equals("October")) {
                    this.monthNumber = 10;
                } else if (this.monthName.equals("November")) {
                    this.monthNumber = 11;
                } else if (this.monthName.equals("December")) {
                    this.monthNumber = 12;
                } else {
                    throw new MyException("Invalid input: Type full name of month with 1st letter capitalized");
                }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
    } // end MonthName constructor
    // setter method to set monthNumber or default to 1 if out of range
    public void setMonthNumber(int monthNumber){
        if (monthNumber >= 1 && monthNumber <= 12){
            this.monthNumber = monthNumber;
        } else {
            monthNumber = 1;
        }
    }
    // getter method to return monthNumber
    public int getMonthNumber(){
        return monthNumber;
    }
    // setMonthNumber method with int argument, which is assigned monthNumber field via switch cases
    public String getMonthName (int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default: 
                return "January";
        }
    }
    // toString method that returns same value as getMonthName method by invoking getMonthName method
    public String toString(int monthNumber){
        return getMonthName(monthNumber);
    }
    // equals method that accepts a Month object as argument. Returns true if argument object is same as calling object
    public boolean equals(Month number){
        if (this.getMonthNumber() == number.getMonthNumber()){
            return true;
        } else {
            return false;
        }
    }

// MonthDays class extends Month. Nested within Month class and set to static to avoid "enclosing instance error" when monthDay object is created
static class MonthDays extends Month {
    
    // class variables
    private int days, monthNumber, year;

    // constructor accepts 2 arguments (integer for month, and integer for year)
    public MonthDays(int monthN, int yearN){
        monthNumber = monthN;
        year = yearN;
    }
    // getNumberOfDays method that returns number of days in month as integer.
    // Returns leap year months as appropriate
    public int getNumberOfDays(int monthNumber){
        // checks February for leap year
        if(monthNumber == 2){
            if (year % 100 == 0 && year % 400 == 0 || year % 100 != 0 && year % 4 == 0){
                days = 29;
                } else {
                    days = 28;
                }
        } // Checks if non-February months have 30 or 31 days
            else if(monthNumber == 4 || monthNumber == 6 || monthNumber == 9 || monthNumber == 11){
                days = 30;
            } else {
                days = 31;
        }
    return days;
    }
} // End MonthDays class
} // End Month class

// Exeption class extension
class MyException extends Exception { 
    public MyException(String error) {
        super(error);
    }
}
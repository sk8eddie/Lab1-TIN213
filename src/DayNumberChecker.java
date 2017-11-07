import javax.swing.*;

public class DayNumberChecker {

    private static JFrame frame = new JFrame();
    private static int[] amountOfDaysForEachMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] weekdays = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

    public static void main(String[] args){

        setupFrame();

        // Setup variables that are needed
        int year;
        int month;
        int day;
        int debug;


        while(true){
            String dateInput = JOptionPane.showInputDialog(frame, "Enter a date (yyyy-mm-dd)");
            if(dateInput == null){
                System.exit(0);
                return;
            }

            // Splitting the date string int year, month and day. Casting the input from String to Integer.
            year = Integer.parseInt(dateInput.substring(0,4));
            month = Integer.parseInt(dateInput.substring(5,7));
            day = Integer.parseInt(dateInput.substring(9,10));
            debug = Integer.parseInt(dateInput.substring(11,12));

            // Verifying that the input data are values that are ok. If so then return the number of the day.
            if(isCorrectDateValues(year, month, day) && isLeapYear(year) && debug == 0){
                String leapOutput = "It is a leap year and the number of the day is:  " + countDayNumber(month, day, true);
                JOptionPane.showMessageDialog(frame, leapOutput);
            } else if(isCorrectDateValues(year, month, day) && !isLeapYear(year) && debug == 0) {
                String notLeapOutput = "It is not a leap year and the number of the day is:  "+ countDayNumber(month, day, false);
                JOptionPane.showMessageDialog(frame, notLeapOutput);
            } else if (debug == 1){
                int numberOfDays = sumHistoryDays(year, month, day);
                String leapOutput = "The weekday is: " + getWeekdayByNumber(numberOfDays);
                JOptionPane.showMessageDialog(frame, leapOutput);
            }
        }

    }

    private static int countDayNumber(int month, int day, boolean leapyear){
        int dayNumber = 0;
        for(int i=0; i < month-1; i++){
            dayNumber += amountOfDaysForEachMonths[i];
        }

        if(leapyear && month > 2){
            dayNumber++;
        }
        return dayNumber+day;
    }

    // Will sum the total amount of days since 1st of january 1754.
    private static int sumHistoryDays(int year, int month, int day){
        int totalDays = 0;

        // Sum all days from the years.
        for(int i=0; i < (year-1754); i++){
            if(isLeapYear(1754+i)){
                totalDays += 366;
            } else {
                totalDays += 365;
            }
        }

        // Add days from the current year
        totalDays += countDayNumber(month, day, isLeapYear(year));

        System.out.println(totalDays);
        return totalDays;
    }

    private static String getWeekdayByNumber(int daynumber){
        return weekdays[daynumber % 7];
    }

    private static boolean isLeapYear(int year){
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    private static boolean isCorrectDateValues(int year, int month, int day){
        return (year > 1754 && (0 < month && month < 13) && (0 < day  && day < 32));
    }

    private static void setupFrame(){
        // Setting up my main window.
        frame.setSize(300, 200);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

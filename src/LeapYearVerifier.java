import javax.swing.*;

public class LeapYearVerifier {

    public static void main(String[] args) {
        // Setting up my main frame
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int year1;

        // Starting a loop so that the program will continue till it is exited.
        while(true){
            String inputYear = JOptionPane.showInputDialog("Enter year", JOptionPane.QUESTION_MESSAGE);

            if(inputYear == null){
                System.exit(0);
                return;
            }

            year1 = Integer.parseInt(inputYear);

            // Verifying that the year is in the correct scope.
            if(year1 <1754){
                JOptionPane.showMessageDialog(frame,"Not a valid year, must be from 1754 and onwards.");
            } else {
                if(isLeapYear(year1)){
                    String correctOutput = "Year: "+year1+" is a leap year";
                    JOptionPane.showMessageDialog(frame, correctOutput);
                } else {
                    String incorrectOutput = "Year: "+year1+" is not a leap year";
                    JOptionPane.showMessageDialog(frame, incorrectOutput);
                }
            }
        }
    }

    private static boolean isLeapYear(int year){
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}

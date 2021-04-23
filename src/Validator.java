import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
    /**
     * This class will store the methods needed to validate the user's input
     * Different methods check for the different appropriate format
     * Some methods will force the user to keep inputting until they fit the proper format
     */
    Scanner scan = new Scanner(System.in);
    PlayerInfo playerInfo = new PlayerInfo();
    //doing this so that i can pass the birthday to it later

    public Validator() {}

    protected int answerValidator() {
        /* This method will ask the user for their guess and check to see if they input something unexpected.
        I wasn't sure if I should have this method handle input, but if I didn't, then I would need to put this try and
        catch elsewhere. In the end, I thought it was best here since the class handles the responsibility of forcing the
        user to input the proper information and isn't affecting score in any way.
         */
        int userGuess;
        while (true) {
            try {
                userGuess = scan.nextInt(); //takes the user's guess
                while (userGuess < 1 || userGuess > 15) {
                    System.out.println("That number isn't between 1 and 15! Try Again");
                    userGuess = scan.nextInt();
                    // This will keep prompting the user to input a number until it is within range, without deducting score.
                }
                break;
            } catch (InputMismatchException e) {
                scan.nextLine(); // to clear the line with the bad input
                System.out.println("Please only input a number.");
            }
        }
        return userGuess; // When a proper answer is finally selected, it returns the guess and lets the game continue.
    }


    protected boolean birthdayValidator(String rawBirthday) {
        // This will try to separate the string that user put as their birthday into the 3 proper values.
        // It will also make sure that the 3 numbers match up with the normal months, days, and a reasonable year.

        String[] dateOfBirth = rawBirthday.split("/");
        // This splits the string into an array. Each string separated by a "/" becomes an index value
        if (dateOfBirth.length != 3) {
            // If there are more or less than 3, its the incorrect format
            System.out.println("Invalid date. Not enough '/'.");
            return false;
        }

        try {
            playerInfo.setDateOfBirth(dateOfBirth);
        } catch (NumberFormatException n) {
            // If the strings within the array fail to be parsed as Integers, the user input something besides numbers.
            System.out.println("Please only type numbers and /'s.");
            return false;
        }

        //used nested if-statements for readability. better than one huge if-statement.
        if ((playerInfo.birthMonth >= 1) && (playerInfo.birthMonth <= 12 )) {
            //verifies month
            if ((playerInfo.birthDay >= 1) && (playerInfo.birthDay <= 31)){
                //verifies day
                if ((playerInfo.birthYear >= 1903)) {
                    /*verifies year. Oldest living person is Kane Tanaka, born January 2, 1903.
                    At first I also included a statement preventing the user from being born in 2021, but who knows
                    if this game will be played 20 years from now?
                     */
                    return true;
                } else {
                    System.out.println("You are not that old");
                    return false;
                }
            } else {
                System.out.println("There are not that many months in a year.");
                return false;
            }
        } else {
            System.out.println("There are not that many days in a month.");
            return false;

        }
    }
}

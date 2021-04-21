import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random(); // makes a random class
    static Validator val = new Validator();
    static PlayerInfo playerInfo = new PlayerInfo();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("What is your name? ");
        String playerName = scan.nextLine();
        System.out.println("Hello, " + playerName + ".");

        System.out.println("What is your date of birth? (MM/DD/YYYY)");
        String rawBirthday = scan.nextLine();

        while (!val.birthdayValidator(rawBirthday)) {
            //loops until a valid birthday is input
            System.out.println("Please enter a valid date of birth. (MM/DD/YYYY)");
            rawBirthday = scan.nextLine();
        }

        while (true) {
            int answer = rand.nextInt(15) + 1; // generates a random number 1-15
            //System.out.println(answer);

            int userScore = 10; // score begins at 10
            System.out.println("I'm thinking of a number between 1 and 15. Which number is it? ");
            int userGuess = scan.nextInt(); //get the first guess from the user

            while (userGuess != answer) {
                //loop the user to keep guessing until they win or lose
                while (!val.answerValidator(userGuess)) {
                    // this will keep prompting the user to input a number until it is within range, without deducting score.
                    userGuess = scan.nextInt();
                }

                userScore--;
                if (userScore <= 0) { //if player runs out of tries, break out of loop
                    break;
                } else {
                    System.out.print("Too bad! ");
                    if (userGuess > answer) {
                        System.out.println("Your guess was too high. Try again! ");
                    } else {
                        System.out.println("Your guess was too low. Try again! ");
                    }
                    userGuess = scan.nextInt();
                }
            }

            if (userScore > playerInfo.bestScore) {
                playerInfo.bestScore = userScore;
                //if the user did better than their previous best (or default) save it so we can tell them at the end
            }

            if (userGuess == answer) {
                System.out.println("Good job! Your score is " + userScore);
                if (userScore == 10) {
                    System.out.println("Wow! First try!");
                }
            } else {
                System.out.println("Sorry, you ran out of guesses! \nThe correct number was " + answer);
            }

            System.out.println("Would you like to play again? (y/n) ");
            scan.nextLine();
            if (scan.nextLine().startsWith("n")) {
                System.out.println("Your best score was " + playerInfo.bestScore);
                break;
                // if they say no, break out of the infinite loop and end the program.
            }
        }
    }
}


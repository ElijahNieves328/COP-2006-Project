import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /**
         * Generates a random number for the user to guess. Passes score, name, and birthday to other methods.
         */
        Random rand = new Random(); //to create random numbers
        Validator val = new Validator();
        PlayerInfo playerInfo = new PlayerInfo();
        Scanner scan = new Scanner(System.in);
        HighScoreManager scores = new HighScoreManager();
        //Importing all the classes I need for main.

        System.out.println("Welcome to the Number Guessing Game 2006! What is your name? (First/MI/Last) ");
        playerInfo.playerName = scan.nextLine().trim(); //passing all credentials to the PLayerInfo class
        System.out.println("Hello, " + playerInfo.playerName + ".");

        System.out.println("What is your date of birth? (MM/DD/YYYY)");
        String rawBirthday = scan.nextLine().trim();

        while (!val.birthdayValidator(rawBirthday)) {
            //loops until a valid birthday is input
            System.out.println("Please enter a valid date of birth. (MM/DD/YYYY)");
            rawBirthday = scan.nextLine();
        }

        System.out.println("The current high scores are: ");
        scores.printHighScores();
        System.out.print("Try your best to beat them!");
        scan.nextLine(); //added just as a 'break.' literally just a space to press enter before the game starts.

        while (true) {
            int answer = rand.nextInt(15) + 1; // generates a random number 1-15

            int userScore = 10; // score begins at 10

            System.out.println("I'm thinking of a number between 1 and 15. Which number is it? ");
            int userGuess = val.answerValidator(); //get the first guess from the user

            while (userGuess != answer) {
                //loop the user to keep guessing until they win or lose

                userScore--; //deduct score for each wrong answer

                if (userScore <= 0) { //if player runs out of tries, break out of loop
                    break;
                } else {
                    System.out.print("Too bad! ");
                    if (userGuess > answer) {
                        System.out.println("Your guess was too high. Try again! ");
                    } else {
                        System.out.println("Your guess was too low. Try again! ");
                    }
                    userGuess = val.answerValidator(); //get a new guess
                }
            }

            if (userScore > playerInfo.bestScore) {
                playerInfo.bestScore = userScore;
                // If the user did better than their previous best (or default) save it so we can tell them at the end
            }

            if (userGuess == answer) {
                System.out.println("Good job! Your score was " + userScore);

                if (userScore == 10) {
                    // if player scores perfectly
                    System.out.println("Wow! First try!");
                } else if (userScore > 5) {
                    // player score high
                    System.out.println("You're pretty good at this!");
                }else {
                    // if player scores low
                    System.out.println("Eventually, you had to get it, right?");
                }
            } else {
                // if player ran out of 'score' and lost
                System.out.println("Sorry, you ran out of guesses! \nThe correct number was " + answer);
            }

            playerInfo.totalScore += userScore;

            if (userScore > 0) {
                System.out.println("Would you like to play again? (y/n) ");
                // They have no choice but to stop if they lost. If they won, they will be asked.
            }
            if (!scan.nextLine().startsWith("y") || userScore == 0) {
                // If they lost or if they choose to stop, their final scores will be sent through.
                System.out.println("Your best score was " + playerInfo.bestScore);
                System.out.println("Your total score was " + playerInfo.totalScore);
                scores.highScoreUpdater(playerInfo.totalScore, playerInfo.playerName);
                break;
                // Once they are done playing, break out of the infinite loop and end.
            } else {
                if (playerInfo.totalScore != userScore) {
                    System.out.println("Your total score is " + playerInfo.totalScore);
                    // I will only tell them their "total" after their first round rather than repeating their score.
                }
            }
        }
    }
}


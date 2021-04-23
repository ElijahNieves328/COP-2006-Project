import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class HighScoreManager {
    /**
     * Manages the high scores. Reads, prints, and replaces the high scores when necessary.
     */

    List<Integer> scoresList = new ArrayList<>(); //initializing arrays that are soon to be changed
    List<String> namesList = new ArrayList<>();
    /*At first, I was messing around with sorted data structures (Maps) because I knew I wanted the scores sorted
     * But I realized that the high scores file will already be sorted. So I can use an unordered ArrayList.
     * I am using ArrayList here for the flexibility of allowing me to 'bump down' someones score.*/

    public HighScoreManager() {
        //default constructor that will always assign the high scores and names in the .txt into two arrays
        String line;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/HighScores.txt"))) {
            /* Opens the file, reads each line, and stores data from those lines into arrays until it finds a
                blank line*/

            while ((line = reader.readLine()) != null) {
                int score = Integer.parseInt(line.substring(0, line.indexOf(":")));
                //isolates the number on the .txt file. made to an int so we can compare to the current players score
                scoresList.add(score);

                String name = line.substring(line.indexOf(":") + 1);
                //isolates the names on the .txt file.
                namesList.add(name);
            }
            // These should not happen and therefore should not have a message berating the user for breaking my code.
        } catch (NoSuchFileException noFile) {
            // If the .txt does not exist, create one
            System.out.println("No 'HighScores.txt' found");
            File highScores = new File("src/HighScores.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printHighScores() {
        String line;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/HighScores.txt"))) {
            // Opens the file, reads each line, and prints those lines until it finds a blank line.
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        // No "file not found" catch because the default constructor would have made one.
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void highScoreUpdater(int totalScore, String playerName) {
        //will go down each score in the high scores list and compare, checking to see if the player's is better.
        for (int i = 0; i < scoresList.size(); i++) {
            int difference = totalScore - scoresList.get(i);
            if (difference == 0 || difference > 0) {
                scoresList.add(i, totalScore);
                //AKA if the player's best is the same or better as the score, place it above that score
                //(it bumps the old score down once)
                namesList.add(i, playerName);
                //same for name
                System.out.println("You got a new high score!");
                break;
            }
        }
        if (scoresList.size() == 6 && namesList.size() == 6) {
            scoresList.remove(5);
            namesList.remove(5);
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(("src/HighScores.txt")))) {
                //overwrite the file
                for (int i = 0; i < scoresList.size(); i++) {
                    writer.write(scoresList.get(i) + ":" + namesList.get(i) + "\n");
                    //This will get the new values from the arraylists and write them into the file with correct formatting.
                }
                // No "file not found" catch because the default constructor would have made one.
            } catch (IOException e) {
                e.printStackTrace();
            }
            printHighScores();
            //if the player got a new high score, show them the new list. if not, don't.
        } else if (scoresList.size() > 6 || namesList.size() > 6 || scoresList.size() != namesList.size()) {
            System.out.println("ERROR: ArrayList(s) are not the correct size");
            //none of these should possibly happen.
        } else {
            System.out.println("Too bad! You didn't beat any high scores.");
            // The first if will only fail if the lists are not 6 entries long.
            // This will only be the case because they did not beat any scores, therefore not adding any.
        }
    }
}
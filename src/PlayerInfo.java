public class PlayerInfo {
    /**
     * This will store the variables of the CURRENT player.
     * Name, birthday, and best score
     */

    protected String playerName;
    protected int birthMonth;
    protected int birthDay;
    protected int birthYear;
    protected int bestScore;
    protected int totalScore;


    public PlayerInfo() {
        //by default, these are the values set for the player
        playerName = "Player";

        birthMonth = 1;
        birthDay = 1;
        birthYear = 2000;

        bestScore = 0;
        totalScore = 0;
    }

    protected void setDateOfBirth(String[] dateOfBirth) {
        // This is a boolean specifically so it can pass whether the try failed or succeeded to the validator.
            birthMonth = Integer.parseInt(dateOfBirth[0].trim());
            birthDay = Integer.parseInt(dateOfBirth[1].trim());
            birthYear = Integer.parseInt(dateOfBirth[2].trim());

    }

    protected void printDateOfBirth() {
        // For now, just a method to make sure everything assigns properly
        // The project instructions don't actually have us do anything with then user's birthday. We just... have it.
        System.out.println(birthMonth+birthDay+birthYear);
    }
}

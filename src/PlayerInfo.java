public class PlayerInfo {
    /**
     * This will store the variables of the CURRENT player.
     * Name, birthday, and best score
     */
    protected String playerName;

    protected String birthMonth;
    protected String birthDay;
    protected String birthYear;

    protected int bestScore;


    public PlayerInfo() {
        playerName = "Player";

        birthMonth = "01";
        birthDay = "01";
        birthYear = "2000";

        bestScore = 0;
    }

    protected void setDateOfBirth(String[] dateOfBirth) {
        birthMonth = dateOfBirth[0].trim();
        birthDay = dateOfBirth[1].trim();
        birthYear = dateOfBirth[2].trim();
    }

    protected void printDateOfBirth() {
        //for now, just a method to make sure everything assigns properly
        System.out.println(birthMonth+birthDay+birthYear);
    }
}

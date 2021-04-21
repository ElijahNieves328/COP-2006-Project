public class Validator {
    /**
     * This class will store the methods needed to validate the user's input
     * Different methods check for the different appropriate format
     */
    PlayerInfo playerInfo = new PlayerInfo();
    //doing this so that i can pass the birthday to it later

    public Validator() {}

    protected boolean answerValidator(int userGuess) {
        while (userGuess < 1 || userGuess > 15) {
            System.out.println("That number isn't between 1 and 15! Try Again");
            return false;
        }
        return true;
    }
    protected boolean birthdayValidator(String rawBirthday) {
        String[] dateOfBirth = rawBirthday.split("/");

        try {
            playerInfo.setDateOfBirth(dateOfBirth);

            if (playerInfo.birthMonth.length() == 2 && playerInfo.birthDay.length() == 2 && playerInfo.birthYear.length() ==4) {
                return true;
            } else {
                System.out.println("Invalid date. Month, day, or year are not appropriate length.");
                return false;
            }
        } catch (Exception ArrayIndexOutOfBoundsException) {
            System.out.println("Invalid date. Not enough '/'.");
            return false;
        }
    }
}

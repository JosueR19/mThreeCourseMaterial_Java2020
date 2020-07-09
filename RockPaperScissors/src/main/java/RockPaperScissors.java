import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        String letsPlay = "y";

        while (letsPlay.equals("y")){

            System.out.println("How many rounds do you want to play?");
            int numRound = sc.nextInt();
            sc.nextLine();

            if (numRound < 1 || numRound > 10) {
                System.out.println("Aww too bad! I wished we could have played. Oh well, have a great day!");
                break;
            } 
            else {
                System.out.println("All Right! Good Luck!");
                int computerWins = 0;
                int userWins = 0;
                int ties = 0;

                while (numRound > 0) {
                    System.out.println("Enter 1 for Rock, 2 for Paper, or 3 for Scissors.");
                    int usersChoice = sc.nextInt();
                    sc.nextLine();
                    int computersChoice = r.nextInt(3) + 1;

                    while (usersChoice < 1 || usersChoice > 3) {
                        System.out.println("Remember you must enter 1 for Rock, 2 for Paper, or 3 for Scissors.");
                        usersChoice = sc.nextInt();
                    }

                    String roundWinner = playRockPaperScissors(usersChoice, computersChoice);
                    if(roundWinner.equals("User")) {
                        userWins++;
                    }
                    else if(roundWinner.equals("Computer")){
                        computerWins++;
                    }
                    else{
                        ties++;
                    }
                    numRound--;
                }
                System.out.println("----------FINAL RESULTS---------- ");
                System.out.println(theWinner(userWins, computerWins));
                System.out.println("Computer: " + computerWins);
                System.out.println("User: " + userWins);
                System.out.println("Ties: " + ties);

                System.out.println("Do you want to play again? (y/n)");
                letsPlay = sc.nextLine();
            }
        }
        if(letsPlay.equals("n")){
            System.out.println("Thanks for playing!");
        }
    }

    public static String playRockPaperScissors(int usersChoice, int computersChoice){
        if((usersChoice == 1 && computersChoice == 3) || (usersChoice == 2 && computersChoice == 1) || (usersChoice == 3 && computersChoice == 2)){
            System.out.println("Congrats you won!");
            return "User";
        }
        else if((computersChoice == 1 && usersChoice == 3) || (computersChoice == 2 && usersChoice == 1) || (computersChoice == 3 && usersChoice == 2)){
            System.out.println("The Computer got the best of you!");
            return "Computer";
        }
        else {
            System.out.println("Its a Tie!");
            return "Tie";
        }
    }

    public static String theWinner(int userWins, int computerWins){
        if(userWins > computerWins) {
            return "The User won!";
        }
        else if(computerWins > userWins) {
            return "The Computer won!";
        }
        else{
            return "It was a tie!";
        }
    }
}

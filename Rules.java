import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.SecureRandom;
import java.util.Scanner;

public class Rules{
    public ArrayList<String> obj;

    Rules(String[] objects){
        obj = new ArrayList<>();
        obj.addAll(Arrays.asList(objects));
    }

    public String calcComputerMove(){
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(obj.size());
        return obj.get(number);
    }

    public void availableMoves(){
        System.out.println("Available moves:");
        for(int i = 1; i <= obj.size(); i++){
            System.out.println(""+i+" - "+obj.get(i-1));
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
    }

    public boolean userMove(String computersMove, String key){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your move: ");
        if(in.hasNextInt()) {
            return doMove(in, computersMove, key);
        }
        else{
            String input = in.nextLine();
            if (input.contentEquals("?")){
                MyTable.printTable(this);
                continueGame();
            }
            else {
                return incorrectMove(computersMove, key);
            }
        }
        return true;
    }

    public String isWinner(int userMove, int computerMove){
        int totalMoves = obj.size();
        double halfMoves = totalMoves / 2.0;
        int res = userMove - computerMove;
        if(res > 0){
            if(res > halfMoves) { return "You Lose"; }
            else{ return "You Win"; }
        }
        else if(res < 0){
            if(res + totalMoves > halfMoves) { return "You Lose"; }
            else{ return "You Win"; }
        }
        else if(res == 0){ return "Draw"; }
        else{ return "Error"; }
    }

    private boolean doMove(Scanner in, String computersMove, String key) {
        int userMove = in.nextInt() - 1;
        if(userMove == -1){
            System.out.println("Exit");
            return false;
        }
        if (0 <= userMove && userMove < obj.size()) {
            return doUserMove(computersMove, userMove, key);
        }
        else {
            return incorrectMove(computersMove, key);
        }
    }

    private boolean doUserMove(String computersMove, int userMove, String key){
        System.out.println("Your move: " + obj.get(userMove));
        System.out.println("Computer move: " + computersMove);
        int compMove = obj.indexOf(computersMove);
        System.out.println(isWinner(userMove, compMove));
        System.out.println("HMAC key: " + key);
        continueGame();
        return true;
    }

    private boolean incorrectMove(String computersMove, String key){
        System.out.println("Error. Please, enter the correct value.");
        return userMove(computersMove, key);
    }

    private void continueGame(){
        System.out.print("Press any key to continue: ");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}



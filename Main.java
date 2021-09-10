
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (movesVerification(args)) {
            boolean flag = true;
            while (flag) {
                System.out.println("_______________New game!_______________");
                Rules rule = new Rules(args);
                KeyGenerator key = new KeyGenerator();
                String computerMove = rule.calcComputerMove();
                byte[] hmac = key.calcHmacSha256(key.genKey.getBytes(StandardCharsets.UTF_8), computerMove.getBytes(StandardCharsets.UTF_8));
                System.out.println(String.format("HMAC: %032X", new BigInteger(1, hmac)));
                rule.availableMoves();
                flag = rule.userMove(computerMove, key.genKey);
            }
        }
    }

    private static boolean movesVerification(String[] moves) {
        if (moves.length % 2 == 0 || moves.length < 2) {
            System.out.println("Error. Please, enter not even number of moves (3, 5, 7, 9, ...)\n" +
                    "For example: one two three four five");
            return false;
        } else {
            Set set = new HashSet();
            for (String arg : moves) {
                if (!set.add(arg)) {
                    System.out.println("Error. Please, enter non-repeating elements\n" +
                            "For example: one two three four five");
                    return false;
                }
            }
            return true;
        }
    }
}

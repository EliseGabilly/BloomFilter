import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 128;

    private static final List<String> HASH_ALGORITHM = List.of("SHA-1", "SHA-256", "SHA-512");

    public static void main(String[] args) {
        SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter(SIZE, HASH_ALGORITHM);
        EvolvedBloomFilter evolvedBloomFilter = new EvolvedBloomFilter(SIZE, HASH_ALGORITHM);

        System.out.println("""
                Welcome to BloomFilter !
                    to use the simple filter input : "simple" / "s"
                    to use the evolved filter input : "evolved" / "e"
                    to exit input : "exit"
                """);

        Scanner scan = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Input : ");
            String s = scan.nextLine();
            String action = s.split(" ")[0];

            switch (action) {
                case "simple", "s" -> simpleBloomFilter.askInput();
                case "evolved", "e" -> evolvedBloomFilter.askInput();
                case "exit" -> isRunning = false;

                default -> System.out.printf("Unrecognized action : %s\n", action);
            }
        }
    }

}

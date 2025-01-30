import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 128;

    private static final List<String> HASH_ALGORITHM = List.of("SHA-1", "SHA-256", "SHA-512");

    public static void main(String[] args) {
        System.out.println("""
                Welcome to BloomFilter !
                    to use the simple filter input : "simple" / "s"
                    to use the simple filter input with default input : "sd"
                    to use the evolved filter input : "evolved" / "e"
                    to use the evolved filter input with default input  : "ed"
                    to exit input : "exit"
                """);

        Scanner scan = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Input : ");
            String s = scan.nextLine();
            String action = s.split(" ")[0];

            switch (action) {
                case "simple", "s" -> {
                    SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter(SIZE, HASH_ALGORITHM);
                    simpleBloomFilter.askInput();
                }
                case "sd" -> {
                    SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter(SIZE, HASH_ALGORITHM);
                    simpleBloomFilter.defaultInput();
                }
                case "evolved", "e" -> {
                    EvolvedBloomFilter evolvedBloomFilter = new EvolvedBloomFilter(SIZE, HASH_ALGORITHM);
                    evolvedBloomFilter.askInput();
                }
                case "ed" -> {
                    EvolvedBloomFilter evolvedBloomFilter = new EvolvedBloomFilter(SIZE, HASH_ALGORITHM);
                    evolvedBloomFilter.defaultInput();
                }
                case "exit" -> isRunning = false;

                default -> System.out.printf("Unrecognized action : %s\n", action);
            }
        }
    }

}

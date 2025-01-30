import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EvolvedBloomFilter {

    private final int size;
    private final Set<String>[] table;
    private final List<String> hashAlgorithm;

    EvolvedBloomFilter(
            final int size,
            final List<String> hashAlgorithm
    ) {
        this.size = size;
        this.table = (Set<String>[]) new Set[size];
        this.hashAlgorithm = hashAlgorithm;
    }


    public void askInput() {
        System.out.println("""
                Welcome to SimpleBloomFilter !
                    to add an entry to the array input : "add yourentry" / "a yourentry"
                    to test for the string presence in the array input : "test yourtest" / "t yourtest"
                    to go back input : "back" / "b"
                    to exit input : "exit" / "e"
                """);

        Scanner scan = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Input : ");
            String s = scan.nextLine();
            String action = s.split(" ", 2)[0];
            String entry = s.contains(" ") ? s.split(" ", 2)[1] : null;

            switch (action) {
                case "add", "a" -> add(entry);
                case "test", "t" -> test(entry);
                case "back", "b" -> isRunning = false;
                case "exit", "e" -> System.exit(0);

                default -> System.out.printf("Unrecognized action : %s\n", action);
            }
        }
    }

    private void add(String entry) {
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            table[key].add(entry);
            System.out.printf("   Index %s : %s \n", key, String.join(", ",table[key]));
        }
    }

    private void test(String entry){
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            if(!table[key].contains(entry)) {
                System.out.printf("   '%s' is NOT present\n", entry);
                return;
            }
        }

        System.out.printf("   '%s' might be present\n", entry);
    }

    public int hash(String data, String algorithm) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        int a = ByteBuffer.wrap(hash).getInt();
        return Math.abs(a) % size;
    }

}

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class SimpleBloomFilter {

    private final int size;
    private final BitSet table;

    private final List<String> hashAlgorithm;

    SimpleBloomFilter(
            final int size,
            final List<String> hashAlgorithm
    ) {
        this.size = size;
        this.table = new BitSet(size);
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
        System.out.print("   added keys :");
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            table.set(key, true);
            System.out.printf(" %s", key);
        }
        System.out.println();
    }


    private void test(String entry){
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            if(!table.get(key)) {
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

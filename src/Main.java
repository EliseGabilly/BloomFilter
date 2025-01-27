import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Scanner;

public class Main {

    public static final int SIZE = 128;
    private static final BitSet table = new BitSet(SIZE);

    public static void main(String[] args) {
        System.out.println("""
                Welcome to BloomFilter !
                    to add an entry to the array input : "add yourentry" / "a yourentry"
                    to test for the string presence in the array input : "test yourtest" / "t yourtest"
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
                case "exit", "e" -> isRunning = false;
                default -> System.out.printf("Unrecognized action : %s", action);
            }
        }
    }

    private static void add(String entry) {
        int key1 = hash(entry, "SHA-1");
        int key2 = hash(entry, "SHA-256");
        int key3 = hash(entry, "SHA-512");

        table.set(key1, true);
        table.set(key2, true);
        table.set(key3, true);

        System.out.printf("   added keys : %s %s %s\n", key1, key2, key3);
    }

    private static void test(String entry){
        int key1 = hash(entry, "SHA-1");
        int key2 = hash(entry, "SHA-256");
        int key3 = hash(entry, "SHA-512");

        boolean isPresent = table.get(key1) && table.get(key2) && table.get(key3);

        System.out.printf("   '%s' is present : %s\n", entry, isPresent);
    }

    public static int hash(String data, String algorithm) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        int a = ByteBuffer.wrap(hash).getInt();
        return Math.abs(a) % SIZE;
    }

}

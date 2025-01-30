import java.util.BitSet;
import java.util.List;

public class SimpleBloomFilter extends BloomFilter {

    private final BitSet table;


    SimpleBloomFilter(
            final int size,
            final List<String> hashAlgorithm
    ) {
        super(size, hashAlgorithm);

        this.table = new BitSet(size);
    }

    @Override
    protected void add(String entry) {
        System.out.printf("   Index for '%s' : ", entry);
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            table.set(key, true);
            System.out.printf(" %s", key);
        }
        System.out.println();
    }

    @Override
    protected void test(String entry){
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            if(!table.get(key)) {
                System.out.printf("   '%s' is NOT present\n", entry);
                return;
            }
        }

        System.out.printf("   '%s' might be present\n", entry);
    }

}

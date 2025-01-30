import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EvolvedBloomFilter extends BloomFilter {

    private final Set<String>[] table;

    EvolvedBloomFilter(
            final int size,
            final List<String> hashAlgorithm
    ) {
        super(size, hashAlgorithm);

        this.table = (Set<String>[]) new Set[size];
        for (int i = 0; i < size; i++) {
            table[i] = new HashSet<>();
        }
    }

    @Override
    protected void add(String entry) {
        System.out.printf("   Index %s : ", entry);
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            table[key].add(entry);
            System.out.printf(" %s ", key);
        }
        System.out.println();
    }

    @Override
    protected void test(String entry){
        List<Integer> keys;
        for(String algo: hashAlgorithm) {
            int key = hash(entry, algo);
            if(!table[key].contains(entry)) {
                System.out.printf("   '%s' is NOT present\n", entry);
                return;
            }
            System.out.printf("   Index %s : %s \n", key, String.join(", ",table[key]));
        }

        System.out.printf("   '%s' might be present\n", entry);
    }

}

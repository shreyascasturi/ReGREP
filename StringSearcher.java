import java.util.HashMap;

// this will not work with repeating letter patterns
public class StringSearcher {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("GREP will not work. Terminating.");
            
        } else {
            StringSearcher.reGREP(args[0], args[1]);
        }
    }

    public static void reGREP(String pattern, String sequence) {
        
        int num_occurrences = 0;
        HashMap<Character, Integer> pat_table = StringSearcher.pprocess(pattern);
        int seq_length = sequence.length();
        int pat_length = pattern.length();
        while (seq_length >= pat_length) {
            
        }
        
    }

    public static HashMap<Character, Integer> pprocess(String pattern) {
        HashMap<Character, Integer> pat_map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            pat_map.put(pattern.charAt(i), i);
        }
        for (Character c : pat_map.keySet()) {
            System.out.printf("%c %d\n", c, pat_map.get(c));
        }
        return pat_map;
    }
}

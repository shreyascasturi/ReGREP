import java.util.HashMap;

// this will not work with repeating letter patterns
public class StringSearcher {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("GREP will not work. Terminating.");
            
        } else {
            int pat_num_occurrences = StringSearcher.reGREP(args[0], args[1]);
            System.out.printf("the number of occurrences of %s in %s is %d. Terminating.\n", args[0], args[1], pat_num_occurrences);
        }
    }

    public static int reGREP(String pattern, String sequence) {
        
        int num_occurrences = 0;
        HashMap<Character, Integer> pat_table = StringSearcher.pprocess(pattern);
        int seq_length = sequence.length();
        System.out.printf("sequence length is: %d\n", seq_length);
        int pat_length = pattern.length();
        while (seq_length >= pat_length) {
            int pat_iterator = pat_length - 1;
            while (pat_iterator >= -1) {
                if (pat_iterator == -1) {
                    num_occurrences++;
                    seq_length -= pat_length;
                    break;
                }
                char pat_char = pattern.charAt(pat_iterator);
                System.out.printf("char in pattern at %d is %c\n", pat_iterator, pat_char);
                char seq_char = sequence.charAt(pat_iterator);
                System.out.printf("char in sequence at %d is %c\n", pat_iterator, seq_char);
                if (pat_char == seq_char) {
                    pat_iterator--;
                }
            }
            if (seq_length < pat_length) {
                return num_occurrences;
            } else {
                String next_substring = sequence.substring(pat_length + 1);
            }
            
        }

        return 0;
        
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

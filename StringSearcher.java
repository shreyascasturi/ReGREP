import java.util.HashMap;

// this will not work with repeating letter patterns
public class StringSearcher {
    private static HashMap<Character, Integer> pat_table;
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("GREP will not work. Terminating.");
            
        } else {
            pat_table = StringSearcher.pprocess(args[0]);
            int pat_num_occurrences = StringSearcher.reGREP(args[0], args[1]);
            System.out.printf("the number of occurrences of %s in %s is %d. Terminating.\n", args[0], args[1], pat_num_occurrences);
        }
    }

    // implementation of boyer moore string searching algorithm (basic)
    public static int reGREP(String pattern, String sequence) {
        
        int num_occurrences = 0;

        int seq_length = sequence.length();
        int pat_length = pattern.length();
        
        while (seq_length >= pat_length) {

            int pat_iter_idx = pat_length - 1;

            while (pat_iter_idx >= -1) {

                if (pat_iter_idx == -1) {
                    num_occurrences++;
                    seq_length -= pat_length;
                    break;
                }
                
                char pat_char = pattern.charAt(pat_iter_idx);
                char seq_char = sequence.charAt(pat_iter_idx);

                if (pat_char == seq_char) {
                    pat_iter_idx--;
                } else if (pat_map.keySet().contains(seq_char)) {
                    StringSearcher.next_shift(pat_map, pat_char, seq_char);                        
                   }
                }
            }
            if (need_to_shift) {
                need_to_shift = false;
                int seq_char_pat_idx = pat_map.get(seq_char);
            }
            if (seq_length < pat_length) {
                return num_occurrences;
            } else {
                String seq_rem_string = sequence.substring(pat_length + 1);
                sequence = seq_rem_string;
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

    public static void find_shift(HashMap<Character, Integer> pat_map, char pat_char, char seq_char) {
        int seq_char_pat_idx = pat_map.get(seq_char);
        // pat: ABCDE
        // seq: XYADE
        // seq: A at loc 2 (pat iter idx)
        // pat: A at loc 0 (seq char pat idx)
        // move!
        if (seq_char_pat_idx < pat_iter_idx ) {
            pat_iter_idx = pat_length - 1;
        
    }
}
                // System.out.printf("char in sequence at %d is %c\n", pat_iter_idx, seq_char);

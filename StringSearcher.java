import java.util.HashMap;

// a very basic implementation of Boyer-Moore with only bad-character rule.
public class StringSearcher {

    private static HashMap<Character, Integer> pat_map;
    private static String pattern;
    private static String sequence;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("GREP will not work. Terminating.");
            
        } else {
            pattern = args[0];
            sequence = args[1];
            pat_map = StringSearcher.pre_process(args[0]);
            int pat_num_occurrences = StringSearcher.reGREP(pattern, sequence);
            System.out.printf("the number of occurrences of %s in %s is %d. Terminating.\n", pattern, sequence, pat_num_occurrences);
        }
    }

    // implementation of boyer moore string searching algorithm (basic)
    public static int reGREP(String pattern, String sequence) {

        System.out.printf("pattern is: %s\n", pattern);
        System.out.printf("sequence is: %s\n", sequence);
        
        int num_occurrences = 0;

        int seq_length = sequence.length();
        int pat_length = pattern.length();
        
        while (seq_length >= pat_length) {

            int pat_iter_idx = pat_length - 1;

            while (pat_iter_idx >= -1) {

                if (pat_iter_idx == -1) {
                    num_occurrences++;
                    int shift_up_amt = seq_length - pat_length;
                    StringSearcher.sequence.substring(shift_up_amt);
                    seq_length -= pat_length;
                    break;
                }
                
                char pat_char = pattern.charAt(pat_iter_idx);
                char seq_char = sequence.charAt(pat_iter_idx);

                // check each char.
                // if equal, continue
                // else, do shift
                if (pat_char == seq_char) {
                    pat_iter_idx--;
                } else if (pat_map.keySet().contains(seq_char)) {
                    // do bad char shift if possible. 
                    int shift_up_amt = StringSearcher.bad_char_shift(pat_map, pat_iter_idx, pat_length);
                    seq_length -= shift_up_amt;
                    System.out.printf("new substring after partial bad char shift: %s\n", sequence);
                    break;
                } else {
                    // do whole shift
                    int shift_up_amt = pat_length;
                    seq_length -= shift_up_amt;
                    sequence = sequence.substring(shift_up_amt);
                    System.out.printf("new substring after whole shift: %s\n", sequence);
                    break;
                }
            }
            
            if (seq_length < pat_length) {
                return num_occurrences;
            } 

        }
        return 0;
            
    }

    public static HashMap<Character, Integer> pre_process(String pattern) {
        HashMap<Character, Integer> pat_map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            pat_map.put(pattern.charAt(i), i);
        }
        for (Character c : pat_map.keySet()) {
            System.out.printf("%c %d\n", c, pat_map.get(c));
        }
        return pat_map;
    }

    // pat: ABCDE
    // seq: XYADE
    // seq: A at loc 2 (pat iter idx)
    // pat: A at loc 0 (seq char pat idx)
    // move!
    public static int bad_char_shift(HashMap<Character, Integer> pat_map, int pat_iter_idx, int pat_length) {
        int seq_char_pat_idx = pat_map.get(StringSearcher.sequence.charAt(pat_iter_idx));

        if (seq_char_pat_idx < pat_iter_idx ) {
            pat_iter_idx = pat_length - 1; // reset pat_iter_idx
            int shift_up_amt = pat_iter_idx - seq_char_pat_idx;
            StringSearcher.sequence = StringSearcher.sequence.substring(shift_up_amt);
            return shift_up_amt;
        } else { // shift by reg amt 
            int shift_up_amt = 1;
            StringSearcher.sequence = StringSearcher.sequence.substring(shift_up_amt);
            return shift_up_amt;
        }
    }
}
                // System.out.printf("char in sequence at %d is %c\n", pat_iter_idx, seq_char);

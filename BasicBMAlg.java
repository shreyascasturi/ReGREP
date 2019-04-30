 public class BasicBMAlg {
 
 
   public static List<Integer> boyerMoore(CharSequence pattern,
                                             CharSequence text,
                                             CharacterComparator comparator) {
          if (pattern == null
              || text == null || pattern.length() == 0 || comparator == null) {
              throw new IllegalArgumentException("We won't work with nulls.");
          } else if (pattern.length() > text.length()) {
              return new LinkedList<Integer>(); 
          } else {
              Map<Character, Integer> lastTable =
                  PatternMatching.buildLastTable(pattern); // lastTable.
              LinkedList<Integer> patternMatchTable = new LinkedList<>(); // list.
              int i = 0; // is what'll keep us shifting!
              int strlen = text.length();
              int patlen = pattern.length();
              while (i <= (strlen - patlen)) {
                  int j = patlen - 1; // begin at the end of pattern.
                  while (j >= 0
                          && comparator.compare(text.charAt(i + j),
                          pattern.charAt(j)) == 0) {
                      j--;
                      // if a successful match is between text[i + j],
                      // pattern[j], we can iterate backwards.
                  }
                  if (j == -1) { // assumes full match.
                      patternMatchTable.add(i); // "i" is where match begins.
                      i++; // iterate over.
                  } else { // we assume text[i + j] != pattern[j]. Check lastTab.
                      Character mismatched = new Character(text.charAt(i + j));
                      int shiftedIndex = 0;
                      if (lastTable.get(mismatched) == null) { // if not in tab.
                          shiftedIndex = -1;
                          // this suggests "i" will shift by patlen/
                          // a major shift, as we'll see.
                      } else { // if in tab.
                          shiftedIndex = lastTable.get(mismatched);
                          // this suggests "i" will shift by (j - shiftedIndex),
                          // as we'll see.
                      }
                      if (j > shiftedIndex) {
                          i = i + (j - shiftedIndex);

                      } else {
                          i = i + 1;
                      }

                  }
              }
              return patternMatchTable;
          }


      }
 }  

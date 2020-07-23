package com.vaani.leetcode.hashing;

/** https://leetcode.com/problems/verifying-an-alien-dictionary/
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 */
public class VerifyingAnAlienDictionary {

    // Time - O(n * n * maxlength of string), Space - O(c)
    public boolean isAlienSorted(String[] words, String order) {
        int[] alphabets = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            alphabets[order.charAt(i) - 'a'] = i; // if h occurs, at 0 we hve a[h] = 0
        }

        for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            // Find the first difference word1[k] != word2[k].
            for (int j = i+1; j < words.length; ++j) {
                String word2 = words[j];
                int min = Math.min(word1.length(), word2.length());

                for(int k = 0; k < min; k++){
                    char ch1 = word1.charAt(k);
                    char ch2 = word2.charAt(k);

                    int idx1 = ch1 -'a';
                    int idx2 = ch2 -'a';

                    if(alphabets[idx1] < alphabets[idx2]){ // it is already in order
                        break ;
                    }else if (alphabets[idx1] > alphabets[idx2]){ // wrong alien order
                        return false;
                    }else if(k == min-1 && word1.length() > word2.length()){ // all chars were equal so far but word1 is longer
                        return false;
                    }
                }
            }
        }

        return true;
    }
    public boolean isAlienSortedWithContinue(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            index[order.charAt(i) - 'a'] = i;
        }

        search:
        for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i + 1];

            // Find the first difference word1[k] != word2[k].
            for (int k = 0; k < Math.min(word1.length(), word2.length()); ++k) {
                if (word1.charAt(k) != word2.charAt(k)) {
                    // If they compare badly, it's not sorted.
                    if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a'])
                        return false;
                    continue search;
                }
            }

            // If we didn't find a first difference, the
            // words are like ("app", "apple").
            if (word1.length() > word2.length())
                return false;
        }

        return true;
    }
}

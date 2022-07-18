package com.vaani.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 09/03/2017. Given an array of words and a length L, format the
 * text such that each line has exactly L characters and is fully (left and right) justified.
 *
 * <p>You should pack your words in a greedy approach; that is, pack as many words as you can in
 * each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *
 * <p>Extra spaces between words should be distributed as evenly as possible. If the number of
 * spaces on a line do not divide evenly between words, the empty slots on the left will be assigned
 * more spaces than the slots on the right.
 *
 * <p>For the last line of text, it should be left justified and no extra space is inserted between
 * words.
 *
 * <p>For example, words: ["This", "is", "an", "example", "of", "text", "justification."] L: 16.
 *
 * <p>Return the formatted lines as: [ "This is an", "example of text", "justification. " ] Note:
 * Each word is guaranteed not to exceed L in length.
 */
public class TextJustification {

    static class Approach1 {
        public List<String> fullJustify(String[] words, int L) {
            int wCount = 0, charCount = 0;
            List<String> line = new ArrayList<>();
            List<String> result = new ArrayList<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 0, l = words.length; i < l; i++) {
                String next = words[i];
                if ((L - (charCount + wCount)) >= next.length()) {
                    line.add(next);
                    charCount += next.length();
                    wCount++;
                } else {
                    // justify and add to list
                    sb.append(line.get(0));
                    StringBuilder space = new StringBuilder();
                    if (line.size() > 1) {
                        int spaceCount = (L - charCount) / (wCount - 1);
                        int remaining = (L - charCount) % (wCount - 1);

                        for (int j = 0; j < spaceCount; j++) space.append(' ');

                        for (int k = 1, kl = line.size(); k < kl; k++) {
                            sb.append(space);
                            if (remaining > 0) {
                                sb.append(' ');
                                --remaining;
                            }
                            sb.append(line.get(k));
                        }
                    } else {
                        int balance = L - (charCount + (wCount - 1));
                        for (int j = 0; j < balance; j++) sb.append(' ');
                    }
                    result.add(sb.toString());
                    sb = new StringBuilder();
                    line.clear();
                    line.add(next);
                    charCount = next.length();
                    wCount = 1;
                }
            }
            if (!line.isEmpty()) {
                sb.append(line.get(0));
                for (int i = 1, l = line.size(); i < l; i++) {
                    sb.append(' ');
                    sb.append(line.get(i));
                }
            }
            int balance = L - (charCount + (wCount - 1));
            for (int i = 0; i < balance; i++) sb.append(' ');
            result.add(sb.toString());
            return result;
        }
    }

    static class ModularApproach1 {
        public List<String> fullJustify(String[] words, int maxWidth) {
            int left = 0;
            List<String> ans = new ArrayList<>();

            while (left < words.length) {
                int right = findRight(left, words, maxWidth);
                ans.add(justify(left, right, words, maxWidth));
                left = right + 1;
            }

            return ans;
        }

        private int findRight(int left, String[] words, int maxWidth) {
            int right = left;
            int sum = words[right++].length();
            // we are doing sum+1 because after each word, a space follows
            while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
                sum += 1 + words[right++].length();
            }
            return right - 1;
        }

        private String justify(int left, int right, String[] words, int maxWidth) {
            // only one word
            if (right == left) {
                return padRight(words[left], maxWidth);
            }

            boolean isLastLine = right == words.length - 1;

            int numSpaceSlots = right - left; // words are right-left+1
            int totalSpace = maxWidth - wordsLength(left, right, words);

            int numSpacesBetween = isLastLine ? 1 : totalSpace / numSpaceSlots;
            String spaceBetween = whitespace(numSpacesBetween);

            int remainingSpaceCount = isLastLine ? 0 : totalSpace % numSpaceSlots;

            StringBuilder lineSB = new StringBuilder();
            for (int i = left; i <= right; i++) {
                lineSB.append(words[i]);
                lineSB.append(spaceBetween);// add allocated space
                lineSB.append(remainingSpaceCount-- > 0 ? " " : ""); // add more space if remainder space exists
            }
            // note: No need to check if last line, because we have already adjusted space accordingly earlier
            return padRight(lineSB.toString().trim(), maxWidth);
        }

        private int wordsLength(int left, int right, String[] words) {
            int wordsLength = 0;
            for (int i = left; i <= right; i++) {
                wordsLength += words[i].length();
            }
            return wordsLength;
        }

        private String padRight(String result, int maxWidth) {
            return result + whitespace(maxWidth - result.length());
        }

        private String whitespace(int length) {
            // old method
            // return new String(new char[length]).replace('\0', ' ');
            return " ".repeat(length);
        }

    }

}

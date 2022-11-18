package com.vaani.leetcode.bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/
 * 126. Word Ladder II
 * Hard
 * <p>
 * <p>
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 1000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
public class WordLadderII {
    private static List<String> getManyWords() {
        return Arrays.asList(
                "kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay",
                "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay",
                "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay",
                "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui",
                "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu",
                "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal",
                "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie",
                "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac",
                "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log",
                "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib",
                "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom",
                "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe",
                "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin",
                "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen",
                "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod",
                "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere",
                "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam",
                "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum",
                "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad",
                "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao",
                "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may",
                "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava",
                "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you",
                "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate",
                "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed",
                "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah",
                "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son",
                "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin",
                "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid",
                "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin",
                "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay",
                "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee",
                "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit",
                "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw",
                "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix",
                "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton",
                "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did",
                "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov",
                "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode",
                "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot",
                "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him",
                "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com",
                "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran",
                "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac",
                "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid",
                "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib",
                "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy",
                "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot",
                "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got",
                "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob");
    }

    public static void main(String[] args) throws Exception {
        List<String> dic = getManyWords();
//        System.out.println(new UsingBFS().findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
//        new WordLadderII.UsingBfsDfs1().findLadders("cet", "ism", dic);
        System.out.println(new UsingBFS3().findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    static class UsingBFS {

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> ans = new ArrayList<>();

            // Not using visited as otherwise we have to add endWord to it
            Set<String> dict = new HashSet<>(wordList);
            if (!dict.contains(endWord)) {
                return ans;
            }
            Set<String> visited = new HashSet<>();

            Queue<WordNode> queue = new LinkedList<>();
            queue.offer(new WordNode(beginWord, 0, null));

            int minLen = Integer.MAX_VALUE;

            while (!queue.isEmpty()) {
                WordNode top = queue.poll();

                //top if have shorter result already
                if (ans.size() > 0 && top.depth > minLen) {
                    return ans;
                }

                for (int i = 0; i < top.word.length(); i++) {
                    char c = top.word.charAt(i);
                    char[] arr = top.word.toCharArray();
                    for (char j = 'z'; j >= 'a'; j--) {
                        if (j == c) {
                            continue;
                        }
                        arr[i] = j;
                        String newWord = new String(arr);

                        if (newWord.equals(endWord)) {
                            //add to result
                            List<String> subAns = new LinkedList<>();
                            subAns.add(endWord);
                            WordNode p = top;
                            while (p != null) {
                                subAns.add(0, p.word);
                                p = p.prev;
                            }

                            ans.add(subAns);

                            //stop if get shorter result
                            if (top.depth <= minLen) {
                                minLen = top.depth;
                            } else {
                                return ans;
                            }
                        }

                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            WordNode n = new WordNode(newWord, top.depth + 1, top);
                            queue.offer(n);
                            visited.add(newWord);
                        }
                    }
                }
            }

            return ans;
        }


        static class WordNode {
            public String word;
            public int depth;
            public WordNode prev;

            public WordNode(String word, int depth, WordNode prev) {
                this.word = word;
                this.depth = depth;
                this.prev = prev;
            }
        }

    }

    // https://leetcode.com/problems/word-ladder-ii/discuss/2221331/Java-BFS-Solution
    static class UsingBfs2 {

        static class WordNode {
            String word; // current word
            List<String> seq;

            WordNode(String word, List<String> seq) {
                this.word = word;
                this.seq = seq;
            }
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);

            List<List<String>> ans = new ArrayList<>();

            // BFS
            Queue<WordNode> q = new LinkedList<>();
            WordNode begin = new WordNode(beginWord, new ArrayList<>());
            begin.seq.add(beginWord);
            q.add(begin);
            boolean ansFound = false;

            while (!q.isEmpty()) {
                int size = q.size();
                Set<String> currLevelSet = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    WordNode curr = q.poll();

                    if (curr.word.equals(endWord)) {
                        ans.add(curr.seq);
                        ansFound = true;
                        continue;
                    }

                    for (int j = 0; j < curr.word.length(); j++) {
                        char[] chars = curr.word.toCharArray();
                        for (char ch = 'a'; ch <= 'z'; ch++) {
                            chars[j] = ch;
                            String newWord = new String(chars);
                            if (wordSet.contains(newWord)) {
                                List<String> t = new ArrayList<>(curr.seq);
                                t.add(newWord);
                                q.add(new WordNode(newWord, t));
                                currLevelSet.add(newWord);
                            }
                        }
                    }
                }

                wordSet.removeAll(currLevelSet);
                if (ansFound)
                    break;
            }

            return ans;
        }

    }

    static class UsingBFS3 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> dictionary = new HashSet<>(wordList);
            if (beginWord == null || endWord == null || dictionary == null || beginWord.isEmpty() || endWord.isEmpty() || dictionary.isEmpty() || !dictionary.contains(endWord)) {
                return Collections.EMPTY_LIST;
            }
            //Queue to traverse in BFS
            Queue<String> queue = new ArrayDeque<>();
            //path from a node to its parent along the BFS traversal
            Map<String, String> parent = new HashMap<>();
            //level of a word appeared in the DAG
            Map<String, Integer> pathLen = new HashMap<>();
            //min length path so far
            int minLen = Integer.MAX_VALUE;
            //resulting shortest paths
            List<List<String>> paths = new ArrayList<>();
            //resulting shortest path last nodes
            Set<String> shortestPathLeaves = new HashSet<>();

            //add source to queue to start traversing
            queue.add(beginWord);
            pathLen.put(beginWord, 0);
            while (!queue.isEmpty()) {
                String top = queue.poll();
                //we already have a shortest path, so discard this longer path
                if (pathLen.get(top) >= minLen) {
                    continue;
                }

                //BFS to each possible 1 edit distance neighbors in dictionary
                for (int i = 0; i < top.length(); i++) {
                    char[] chars = top.toCharArray();
                    char k = chars[i];
                    //all possible words with current character variations
                    for (char c = 'a'; c < 'z'; c++) {
                        if (k == c) {
                            continue;
                        }
                        chars[i] = c;
                        String newWord = new String(chars);

                        if (!pathLen.containsKey(newWord)) {
                            pathLen.put(newWord, Integer.MAX_VALUE);
                        }
                        //Dijktra's shortest path formullae
                        if (pathLen.get(top) + 1 > pathLen.get(newWord)) {
                            continue;
                        }

                        //if we reach a solution, add it to solution
                        if (newWord.equals(endWord)) {
                            shortestPathLeaves.add(top);
                            minLen = Math.min(minLen, pathLen.get(top) + 1);
                        }
                        //otherwise if this intermediate word is present in dictionary then
                        //add it as children and update the path len
                        else if (dictionary.contains(newWord)) {
                            parent.put(newWord, top);
                            pathLen.put(newWord, pathLen.get(top) + 1);
                            queue.add(newWord);
                        }
                    }
                }
            }

            //Add all paths to result set
            for (String path : shortestPathLeaves) {
                paths.add(getPath(parent, path, beginWord, endWord));
            }

            return paths;
        }

        private List<String> getPath(Map<String, String> parentMap, String leaf, String src, String dst) {
            List<String> path = new ArrayList<String>();

            String node = leaf;
            path.add(dst);
            path.add(0, leaf);
            while (parentMap.get(node) != null && !parentMap.get(node).equals(src)) {
                node = parentMap.get(node);
                path.add(0, node);
            }
            path.add(0, src);

            return path;
        }
    }

    static class UsingBFSOld {

        static class WordNode {
            String word;
            int numSteps;
            WordNode pre;

            public WordNode(String word, int numSteps, WordNode pre) {
                this.word = word;
                this.numSteps = numSteps;
                this.pre = pre;
            }
        }

        public List<List<String>> findLadders(String start, String end, Set<String> dict) {
            List<List<String>> ans = new ArrayList<List<String>>();

            Queue<WordNode> queue = new LinkedList<WordNode>();
            queue.add(new WordNode(start, 1, null));

            dict.add(end);
            // there are multiple solutions, but we need one with min steps
            int minStep = 0;

            Set<String> visited = new HashSet<String>();
            Set<String> unvisited = new HashSet<String>(dict);

            int preNumSteps = 0;

            while (!queue.isEmpty()) {
                WordNode top = queue.remove();
                String word = top.word;
                int currNumSteps = top.numSteps;

                if (word.equals(end)) {
                    if (minStep == 0) {
                        minStep = top.numSteps;
                    }

                    if (top.numSteps == minStep && minStep != 0) {
                        //nothing
                        List<String> subAns = new ArrayList<>();
                        subAns.add(top.word);
                        while (top.pre != null) {
                            subAns.add(0, top.pre.word);
                            top = top.pre;
                        }
                        ans.add(subAns);
                        continue;
                    }

                }

                if (preNumSteps < currNumSteps) {
                    unvisited.removeAll(visited);
                }

                preNumSteps = currNumSteps;

                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char temp = arr[i];
                        if (arr[i] != c) {
                            arr[i] = c;
                        }

                        String newWord = new String(arr);
                        if (unvisited.contains(newWord)) {
                            queue.add(new WordNode(newWord, top.numSteps + 1, top));
                            visited.add(newWord);
                        }

                        arr[i] = temp;
                    }
                }

            }

            return ans;
        }
    }

    static class UsingBfsDfs1 {
        private static final Queue<String> queue = new ArrayDeque<>();
        private static final Set<String> dictionary = new HashSet<>();
        private static final String ALPHABET_SET = "abcdefghijklmnopqrstuvwxyz";
        private static final Map<String, Set<String>> graph = new HashMap<>();
        private static final Map<String, Integer> minDistance = new HashMap<>();
        private static final List<List<String>> result = new ArrayList<>();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            dictionary.addAll(wordList);
            bfs(beginWord, endWord, wordList);
            List<String> path = new ArrayList<>();
            path.add(beginWord);
            dfs(beginWord, endWord, path);
            return result;
        }

        private void bfs(String beginWord, String endWord, List<String> wordList) {
            queue.offer(beginWord);
            minDistance.put(beginWord, 0);
            while (!queue.isEmpty()) {
                String currWord = queue.poll();
                StringBuilder childSb = new StringBuilder(currWord);
                for (int j = 0, ln = childSb.length(); j < ln; j++) {
                    for (int i = 0, l = ALPHABET_SET.length(); i < l; i++) {
                        char old = childSb.charAt(j);
                        childSb.replace(j, j + 1, String.valueOf(ALPHABET_SET.charAt(i)));
                        String child = childSb.toString();
                        if (dictionary.contains(child)) {
                            if (minDistance.get(child) == null) {
                                minDistance.put(child, minDistance.get(currWord) + 1);
                                addChild(currWord, child);
                                if (!child.equals(endWord)) queue.offer(child);
                            } else {
                                if (minDistance.get(child) == (minDistance.get(currWord) + 1))
                                    addChild(currWord, child);
                            }
                        }
                        childSb.replace(j, j + 1, String.valueOf(old));
                    }
                }
            }
        }

        private void addChild(String parent, String child) {
            Set<String> children = graph.get(parent);
            if (children == null) children = new HashSet<>();
            children.add(child);
            graph.put(parent, children);
        }

        private void dfs(String currWord, String endWord, List<String> path) {
            if (currWord.equals(endWord)) {
                result.add(new ArrayList<>(path));
            } else {
                Set<String> children = graph.get(currWord);
                if (children != null) {
                    for (String c : children) {
                        path.add(c);
                        dfs(c, endWord, path);
                        path.remove(path.size() - 1);
                    }
                }
            }
        }
    }

    static class UsingDFS {
        private final Map<String, Set<String>> graph = new HashMap<>();
        private final List<List<String>> result = new ArrayList<>();
        private final Map<String, Integer> distanceMap = new HashMap<>();


        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            buildGraph(graph, beginWord, wordList);
            dfs(beginWord, endWord, new ArrayList<>());
            return result;
        }

        private void buildGraph(Map<String, Set<String>> graph, String beginWord, List<String> wordList) {
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            distanceMap.put(beginWord, 0);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String word = queue.poll();
                    Set<String> set = graph.getOrDefault(word, new HashSet<>());
                    graph.put(word, set);
                    for (String s : wordList) {
                        int cnt = 0;
                        for (int j = 0; j < word.length(); j++) {
                            if (s.charAt(j) != word.charAt(j)) {
                                cnt++;
                            }
                        }
                        if (cnt == 1) {
                            if (!distanceMap.containsKey(s)) {
                                queue.add(s);
                                distanceMap.put(s, distanceMap.get(word) + 1);
                            }
                            set.add(s);
                        }
                    }
                }
            }
        }

        private void dfs(String word, String target, List<String> ans) {
            ans.add(word);
            if (target.equals(word)) {
                result.add(ans);
            } else {
                for (String child : graph.get(word)) {
                    if (distanceMap.get(word) + 1 == distanceMap.getOrDefault(child, Integer.MAX_VALUE)) {
                        dfs(child, target, new ArrayList<>(ans));
                    }
                }
            }
        }
    }

    static class UsingBfsDfs2 {
        Set<String> dictionary = new HashSet<>();
        Map<String, Integer> dist = new HashMap();
        List<List<String>> ans;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            this.ans = new ArrayList<>();
            dictionary.addAll(wordList);
            if(!dictionary.contains(endWord)){
                return ans;
            }
            bfs(beginWord, endWord);
            if (dist.get(endWord) == null) {
                return ans;
            }
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, beginWord, path);
            return ans;
        }

        private void bfs(String beginWord, String endWord) {
            Queue<String> q = new LinkedList<>();
            q.offer(beginWord);
            dist.put(beginWord, 0);
            while (q.size() > 0) {
                String cur = q.poll();
                if (cur.equals(endWord)) break;
                char[] charCur = cur.toCharArray();
                for (int i = 0; i < cur.length(); i++) {
                    char c = charCur[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        charCur[i] = j;
                        String s = new String(charCur);
                        if (dictionary.contains(s) && dist.get(s) == null) {
                            dist.put(s, dist.get(cur) + 1);
                            q.offer(s);
                        }

                    }
                    charCur[i] = c;
                }
            }
        }

        private void dfs(String word, String beginWord, List<String> path) {
            if (word.equals(beginWord)) {
                List<String> subAns = new ArrayList<>(path);
                Collections.reverse(subAns);
                ans.add(subAns);
                return;
            }
            char[] charCur = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                char c = charCur[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    charCur[i] = j;
                    String s = new String(charCur);
                    if (dist.get(s) != null && dist.get(s) + 1 == dist.get(word)) {
                        path.add(s);
                        dfs(s, beginWord, path);
                        path.remove(path.size() - 1);
                    }

                }
                charCur[i] = c;
            }
        }

    }
}

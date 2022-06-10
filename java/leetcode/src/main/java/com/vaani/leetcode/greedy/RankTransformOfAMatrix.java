package com.vaani.leetcode.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/rank-transform-of-a-matrix/
 * 1632. Rank Transform of a Matrix
 * Hard
 * <p>
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 * <p>
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 * <p>
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * It is guaranteed that answer is unique under the given rules.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 * Example 3:
 * <p>
 * <p>
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 * Example 4:
 * <p>
 * <p>
 * Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
 * Output: [[5,1,4],[1,2,3],[6,3,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -109 <= matrix[row][col] <= 10^9
 */
public class RankTransformOfAMatrix {

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public void union(int a, int b) {
            int rootP = find(a), rootQ = find(b);
            if (rootP == rootQ) {
                return;
            }
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }

        }

        public int find(int a) {
            if (a == parent[a]) {
                return a;
            }
            parent[a] = find(parent[a]);
            return parent[a];

        }
    }

    static class Position {
        private int index;
        private int row;
        private int col;

        Position(int index, int row, int col) {
            this.index = index;
            this.row = row;
            this.col = col;
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] ans = new int[m][n];
        int[] maxRankInCol = new int[n];
        int[] maxRankInRow = new int[m];
        Map<Integer, List<Position>> positionNumMap = new HashMap<>();
        List<Integer> uniqueNums = new ArrayList<>();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                if (!positionNumMap.containsKey(num)) {
                    positionNumMap.put(num, new ArrayList<>());
                    uniqueNums.add(num);
                }
                positionNumMap.get(num).add(new Position(positionNumMap.get(num).size(), i, j));
            }


        Collections.sort(uniqueNums);

        for (int num : uniqueNums) {
            List<Position> positions = positionNumMap.get(num);
            UnionFind uf = new UnionFind(positions.size());

            positions.sort(Comparator.comparingInt(p -> p.row));
            for (int i = 0; i < positions.size() - 1; i++) {
                if (positions.get(i).row == positions.get(i + 1).row) {
                    uf.union(positions.get(i).index, positions.get(i + 1).index);
                }
            }

            positions.sort(Comparator.comparingInt(p -> p.col));
            for (int i = 0; i < positions.size() - 1; i++) {
                if (positions.get(i).col == positions.get(i + 1).col) {
                    uf.union(positions.get(i).index, positions.get(i + 1).index);
                }
            }

            HashMap<Integer, List<Position>> positionMap = new HashMap<>();
            for (Position position : positions) {
                int parent = uf.find(position.index);
                if (!positionMap.containsKey(parent)) {
                    positionMap.put(parent, new ArrayList<>());
                }
                positionMap.get(parent).add(position);
            }

            for (List<Position> list : positionMap.values()) {
                int max = 0;
                for (Position pos : list) {
                    max = Math.max(maxRankInRow[pos.row], max);
                    max = Math.max(maxRankInCol[pos.col], max);
                }

                int rank = max + 1;
                for (Position pos : list) {
                    ans[pos.row][pos.col] = rank;
                    maxRankInRow[pos.row] = rank;
                    maxRankInCol[pos.col] = rank;
                }
            }
        }

        return ans;
    }


}

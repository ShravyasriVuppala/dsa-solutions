/**
 * OPTIMAL SOLUTION: O(m×n) Time, O(m×n) Space
 *
 * Approach: Dynamic Programming with Suffix Frequency Preprocessing
 * - Preprocess: Build character frequency arrays for suffixes O(m+n)
 * - DP: Fill m×n table, each cell queries in O(26) = O(1)
 * - Total: O(m×n)
 */
public class Solution {

    public static int getMinimumConflicts(String primary, String secondary) {
        int m = primary.length();
        int n = secondary.length();

        // PREPROCESSING: Build suffix character counts - O(m + n)
        int[][] primarySuffixCounts = new int[m + 1][26];
        for (int i = m - 1; i >= 0; i--) {
            for (int c = 0; c < 26; c++) {
                primarySuffixCounts[i][c] = primarySuffixCounts[i + 1][c];
            }
            primarySuffixCounts[i][primary.charAt(i) - 'a']++;
        }

        int[][] secondarySuffixCounts = new int[n + 1][26];
        for (int j = n - 1; j >= 0; j--) {
            for (int c = 0; c < 26; c++) {
                secondarySuffixCounts[j][c] = secondarySuffixCounts[j + 1][c];
            }
            secondarySuffixCounts[j][secondary.charAt(j) - 'a']++;
        }

        // DP: dp[i][j] = min conflicts to merge first i from primary + first j from secondary
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;

        // Fill DP table - O(m × n)
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue;

                // Option 1: Take primary[i]
                if (i < m) {
                    char primaryChar = primary.charAt(i);
                    // Count chars in secondary[j:] that are < primaryChar (they create conflicts)
                    int conflicts = 0;
                    for (int c = 0; c < (primaryChar - 'a'); c++) {
                        conflicts += secondarySuffixCounts[j][c];
                    }
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + conflicts);
                }

                // Option 2: Take secondary[j]
                if (j < n) {
                    char secondaryChar = secondary.charAt(j);
                    // Count chars in primary[i:] that are < secondaryChar (they create conflicts)
                    int conflicts = 0;
                    for (int c = 0; c < (secondaryChar - 'a'); c++) {
                        conflicts += primarySuffixCounts[i][c];
                    }
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + conflicts);
                }
            }
        }

        return dp[m][n];
    }
}
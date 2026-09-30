class Solution {
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(0, 0, m - 1, n - 1, dp);
    }

    int solve(int i, int j, int m, int n, int dp[][]) {

        // Reached destination
        if (i == m && j == n) {
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int p1 = 0;
        int p2 = 0;

        // Move down
        if (i < m) {
            p1 = solve(i + 1, j, m, n, dp);
        }

        // Move right
        if (j < n) {
            p2 = solve(i, j + 1, m, n, dp);
        }

        return dp[i][j] = p1 + p2;
    }
}
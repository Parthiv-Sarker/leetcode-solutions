class Solution {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        return solve(0, n, dp);
    }

    public int solve(int i, int n, int[] dp) {

        if (i == n) {
            return 1;
        }

        if (i > n) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int a1 = solve(i + 1, n, dp);
        int a2 = solve(i + 2, n, dp);

        dp[i] = a1 + a2;

        return dp[i];
    }
}
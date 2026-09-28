class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(0, 1, nums, dp);
    }

    public int solve(int i, int pick, int[] nums, int[][] dp) {
        if (i == nums.length) {
            return 0;
        }

        if (dp[i][pick] != -1) {
            return dp[i][pick];
        }

        if (pick == 0) {
            return dp[i][pick] = solve(i + 1, 1, nums, dp);
        }

        int c1 = nums[i] + solve(i + 1, 0, nums, dp);

        int c2 = solve(i + 1, 1, nums, dp);

        int ans = Math.max(c1, c2);

        return dp[i][pick] = ans;
    }
}
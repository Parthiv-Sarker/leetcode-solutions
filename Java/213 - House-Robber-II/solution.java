class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        // Case 1: First house is considered, so last house cannot be robbed.
        int[][] dp1 = new int[n][2];
        for (int[] row : dp1) {
            Arrays.fill(row, -1);
        }

        // Case 2: First house is excluded, so last house can be robbed.
        int[][] dp2 = new int[n][2];
        for (int[] row : dp2) {
            Arrays.fill(row, -1);
        }

        int c1 = solve(0, 1, nums, true, dp1);
        int c2 = solve(1, 1, nums, false, dp2);

        return Math.max(c1, c2);
    }

    int solve(int i, int rob, int[] nums, boolean firstHouse, int[][] dp) {

        if (i == nums.length) {
            return 0;
        }

        // If first house was selected, last house cannot be selected.
        if (firstHouse && i == nums.length - 1) {
            return 0;
        }

        if (dp[i][rob] != -1) {
            return dp[i][rob];
        }

        // Previous house was robbed, so we cannot rob current house.
        if (rob == 0) {
            return dp[i][rob] =
                    solve(i + 1, 1, nums, firstHouse, dp);
        }

        // Rob current house.
        int take = nums[i] +
                solve(i + 1, 0, nums, firstHouse, dp);

        // Skip current house.
        int skip =
                solve(i + 1, 1, nums, firstHouse, dp);

        return dp[i][rob] = Math.max(take, skip);
    }
}
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[] len = {row - 1, col - 1};

        int dp[][] = new int[row][col];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        return solve(0, 0, obstacleGrid, len, dp);
    }

    int solve(int i, int j, int grid[][], int len[], int dp[][]){
        if(grid[i][j] == 1){
            return 0;
        }

        if(i == len[0] && j == len[1]){
            return 1;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int p1 = 0;
        int p2 = 0;

        if(i < len[0]){
            p1 = solve(i + 1, j, grid, len, dp);
        }

        if(j < len[1]){
            p2 = solve(i, j + 1, grid, len, dp);
        }

        return dp[i][j] = p1 + p2;

    }
}
class Solution {

    int[] xAxis = {1, -1, 0, 0};
    int[] yAxis = {0, 0, -1, 1};

    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int noOfIsland = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == '1' && !visited[i][j]) {

                    dfs(grid, visited, i, j, m, n);

                    noOfIsland++;
                }
            }
        }

        return noOfIsland;
    }

    boolean isValid(int i, int j, int m, int n) {

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }

        return true;
    }

    void dfs(
        char[][] grid,
        boolean[][] visited,
        int i,
        int j,
        int m,
        int n
    ) {

        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {

            int x = i + xAxis[k];
            int y = j + yAxis[k];

            if (
                isValid(x, y, m, n) &&
                grid[x][y] == '1' &&
                !visited[x][y]
            ) {
                dfs(grid, visited, x, y, m, n);
            }
        }
    }
}
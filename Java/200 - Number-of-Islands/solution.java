class Solution {
    int xAxis[] = {-1, 1, 0, 0};
    int yAxis[] = {0, 0, -1, 1};

    public int numIslands(char[][] grid) {
        int noOfIsland = 0;
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        for(boolean arr[] : visited){
            Arrays.fill(arr, false);
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    dfs(i, j, m, n, grid, visited);
                    noOfIsland++;
                }
            }
        }

        return noOfIsland;
    }

    boolean isValidAxis(int i, int j, int m, int n){
        if(i < 0 || i >= m || j < 0 || j >= n){
            return false;
        }

        return true;
    }

    void dfs(int i, int j, int m, int n, char[][] grid, boolean[][] visited){

        visited[i][j] = true;

        for(int k = 0; k < 4; k++){

            int x = i + xAxis[k];
            int y = j + yAxis[k];

            if(isValidAxis(x, y, m, n) && !visited[x][y] && grid[x][y] == '1'){
                dfs(x, y, m, n, grid, visited);
            }
        }
    }
}
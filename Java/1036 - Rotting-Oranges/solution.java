class Solution {
    public int orangesRotting(int[][] grid) {
        int time = 0;
        int freshOrange = 0;
        int m = grid.length;
        int n = grid[0].length;

        int[] xAxis = {-1, 1, 0, 0};
        int[] yAxis = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshOrange++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty() && freshOrange > 0) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int[] cell = queue.poll();
                int i = cell[0];
                int j = cell[1];

                for (int k = 0; k < 4; k++) {
                    int x = i + xAxis[k];
                    int y = j + yAxis[k];

                    if (isValidAxis(x, y, m, n)
                            && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        freshOrange--;
                        queue.offer(new int[]{x, y});
                    }
                }
            }

            time++;
        }

        return freshOrange == 0 ? time : -1;
    }

    boolean isValidAxis(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}

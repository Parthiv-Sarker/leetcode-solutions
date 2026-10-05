class Solution {

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        boolean[] isVisited = new boolean[n];

        int provinces = 0;

        for (int i = 0; i < n; i++) {

            if (!isVisited[i]) {

                dfs(isConnected, isVisited, i);

                provinces++;
            }
        }

        return provinces;
    }

    void dfs(
        int[][] isConnected,
        boolean[] isVisited,
        int city
    ) {

        isVisited[city] = true;

        for (int nextCity = 0; nextCity < isConnected.length; nextCity++) {

            if (
                isConnected[city][nextCity] == 1 &&
                !isVisited[nextCity]
            ) {

                dfs(isConnected, isVisited, nextCity);
            }
        }
    }
}
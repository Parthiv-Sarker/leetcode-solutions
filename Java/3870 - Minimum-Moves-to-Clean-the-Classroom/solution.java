class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int start = -1;
        List<Integer> litter = new ArrayList<>();

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    start = r * n + c;
                } else if (ch == 'L') {
                    litterId[r][c] = litter.size();
                    litter.add(r * n + c);
                }
            }
        }

        int k = litter.size();

        if (k == 0) {
            return 0;
        }

        int maskCount = 1 << k;
        int allCollected = maskCount - 1;

        int cells = m * n;
        int[][] best = new int[cells][maskCount];

        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        int maxStates = cells * maskCount * (energy + 1);
        int[] queue = new int[maxStates];
        int head = 0, tail = 0;

        int initialState = ((start * maskCount) * (energy + 1)) + energy;
        queue[tail++] = initialState;
        best[start][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (head < tail) {
            int levelEnd = tail;

            while (head < levelEnd) {
                int state = queue[head++];

                int remainingEnergy = state % (energy + 1);
                int temp = state / (energy + 1);

                int mask = temp % maskCount;
                int cell = temp / maskCount;

                int r = cell / n;
                int c = cell % n;

                if (mask == allCollected) {
                    return moves;
                }

                if (remainingEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char nextChar = classroom[nr].charAt(nc);

                    if (nextChar == 'X') {
                        continue;
                    }

                    int nextCell = nr * n + nc;

                    int nextEnergy = remainingEnergy - 1;

                    int nextMask = mask;

                    if (nextChar == 'L') {
                        int id = litterId[nr][nc];
                        nextMask |= (1 << id);
                    }

                    if (nextChar == 'R') {
                        nextEnergy = energy;
                    }

                    if (nextEnergy <= best[nextCell][nextMask]) {
                        continue;
                    }

                    best[nextCell][nextMask] = nextEnergy;

                    int nextState =
                            ((nextCell * maskCount + nextMask)
                                    * (energy + 1))
                                    + nextEnergy;

                    queue[tail++] = nextState;
                }
            }

            moves++;
        }

        return -1;
    }
}
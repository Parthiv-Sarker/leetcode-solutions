class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> cur = intervals.get(i);

            arr[i] = new Interval(
                cur.get(0),
                cur.get(1),
                cur.get(2),
                i
            );
        }

        // Sort by right endpoint.
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r) {
                return Integer.compare(a.r, b.r);
            }
            return Integer.compare(a.idx, b.idx);
        });

        // Store right endpoints.
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            right[i] = arr[i].r;
        }

        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = lowerBound(right, arr[i].l, i);
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[0][k] = new State(0, new int[0]);
        }

        for (int i = 1; i <= n; i++) {

            Interval cur = arr[i - 1];

            for (int k = 0; k <= 4; k++) {

                State best = dp[i - 1][k];

                if (k > 0) {

                    State previous =
                        dp[prev[i - 1]][k - 1];

                    int[] ids = addSorted(
                        previous.ids,
                        cur.idx
                    );

                    State take = new State(
                        previous.score + cur.w,
                        ids
                    );

                    if (better(take, best)) {
                        best = take;
                    }
                }

                dp[i][k] = best;
            }
        }

        return dp[n][4].ids;
    }

    private int lowerBound(int[] right, int target, int limit) {

        int lo = 0;
        int hi = limit;

        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            if (right[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    private int[] addSorted(int[] ids, int value) {

        int[] result = Arrays.copyOf(ids, ids.length + 1);

        int i = result.length - 1;

        while (i > 0 && result[i - 1] > value) {
            result[i] = result[i - 1];
            i--;
        }

        result[i] = value;

        return result;
    }

    private boolean better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score;
        }

        return lexicographicallySmaller(a.ids, b.ids);
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}
class Solution {

    class Pair {
        int[] point;
        int sqrt;

        Pair(int[] point, int sqrt) {
            this.point = point;
            this.sqrt = sqrt;
        }
    }

    public int calSqrt(int[] point) {
        int p1 = point[0];
        int p2 = point[1];

        return (p1 * p1) + (p2 * p2);
    }

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.sqrt, b.sqrt)
        );

        for (int[] point : points) {
            int sqrt = calSqrt(point);
            minHeap.offer(new Pair(point, sqrt));
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            Pair pair = minHeap.poll();
            result[i] = pair.point;
        }

        return result;
    }
}
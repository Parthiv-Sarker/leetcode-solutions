class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        int left = 0;
        int right = n - k;

        // Find the starting index of the best window
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (x - arr[mid] > arr[mid + k] - x) {
                // Left side is farther away,
                // so move the window to the right.
                left = mid + 1;
            } else {
                // Right side is farther away (or equal).
                // Keep the left window because ties prefer smaller values.
                right = mid;
            }
        }

        // [left, left + k - 1] is the answer
        List<Integer> result = new ArrayList<>();

        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
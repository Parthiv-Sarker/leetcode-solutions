class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long power = 1000;
        long commas = 1;

        while (power <= n) {
            ans += n - power + 1;

            power *= 1000;
            commas++;
        }

        return ans;
    }
}
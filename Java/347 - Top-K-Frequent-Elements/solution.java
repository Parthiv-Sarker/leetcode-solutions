class Solution {
    class Pair{
        int num;
        int frq;

        Pair(int num, int frq){
            this.num = num;
            this.frq = frq;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        int result[] = new int[k];

        HashMap<Integer, Integer> frqMap = new HashMap<>();

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.frq, b.frq)
        );

        for(int num : nums){
            frqMap.put(num, frqMap.getOrDefault(num, 0) + 1);
        }

        for(int num : frqMap.keySet()){
            int frq = frqMap.get(num);

            minHeap.offer(new Pair(num, frq));

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        for (int i = 0; i < k; i++) {
            Pair pair = minHeap.poll();
            result[i] = pair.num;
        }

        return result;
    }
}
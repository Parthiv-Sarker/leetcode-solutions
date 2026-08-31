class Solution {
    class Pair{
        int elem;
        int diff;

        Pair(int elem, int diff){
            this.elem = elem;
            this.diff = diff;
        }
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
            (a, b) -> { 
                if (a.diff != b.diff) { 
                    return Integer.compare(b.diff, a.diff); 
                }
                return Integer.compare(b.elem, a.elem); 
            }
        );

        for(int elem : arr){
            int diff = Math.abs(x - elem);

            minHeap.offer(new Pair(elem, diff));

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        while(!minHeap.isEmpty()){
            Pair pair = minHeap.poll();
            result.add(pair.elem);
        }

        Collections.sort(result);

        return result;
    }
}
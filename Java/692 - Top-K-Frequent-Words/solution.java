class Solution {
    class Pair {
        int frq;
        String word;

        Pair(int frq, String word) {
            this.frq = frq;
            this.word = word;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        HashMap<String, Integer> frqMap = new HashMap<>();

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
            (a, b) -> {
                if (a.frq != b.frq) {
                    return Integer.compare(a.frq, b.frq);
                }

                return b.word.compareTo(a.word);
            }
        );

        for (String word : words) {
            frqMap.put(word, frqMap.getOrDefault(word, 0) + 1);
        }

        for (String word : frqMap.keySet()) {
            int frq = frqMap.get(word);

            minHeap.offer(new Pair(frq, word));

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().word);
        }

        Collections.reverse(result);

        return result;
    }
}
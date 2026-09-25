class Solution {

    private int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parse(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            if (ch == ',') {
                index++;

                Set<String> next = parse(s);
                result.addAll(next);

            } else {
                Set<String> current;

                if (ch == '{') {
                    index++;

                    current = parse(s);

                    index++;
                } else {
                    current = new HashSet<>();
                    current.add(String.valueOf(ch));
                    index++;
                }

                result = concatenate(result, current);
            }
        }

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}
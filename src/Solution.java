import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // Minimal Genetic Mutation
    char[] choices = new char[] {'A', 'C', 'G', 'T'};

    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> bankSet = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        for (String str : bank) {
            bankSet.add(str);
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (cur.equals(end))
                    return step;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        String next = generateNewStr(cur, i, j);
                        if (next.equals("") || !bankSet.contains(next) || visited.contains(next)) {
                            continue;
                        }
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String generateNewStr(String str, int i, int j) {
        char curr = str.charAt(i);
        if (curr == choices[j])
            return "";
        return str.substring(0, i) + choices[j] + str.substring(i+1, 8);
    }


}

import java.util.*;

class TweetCounts {
    Map<String, TreeMap<Integer, Integer>> map;

    public TweetCounts() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        if (!map.containsKey(tweetName)) {
            map.put(tweetName, new TreeMap<Integer, Integer>());
        }
        TreeMap<Integer, Integer> tm = map.get(tweetName);
        tm.put(time, tm.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList<>();
        if (!map.containsKey(tweetName))
            return res;

        TreeMap<Integer, Integer> tm = map.get(tweetName);
        int gap = getGap(freq);
        int start = startTime;
        int end = Math.min(endTime, start+gap - 1);
        Map.Entry<Integer, Integer> entry = tm.ceilingEntry(startTime);
        while (start <= end) {
            int count = 0;
            while (entry != null && entry.getKey() <= end) {
                count += entry.getValue();
                entry = tm.higherEntry(entry.getKey());
            }
            res.add(count);
            start = end + 1;
            end = Math.min(endTime, start + gap - 1);
        }
        return res;
    }

    public int getGap(String freq) {
        if (freq.equals("minute")) {
            return 60;
        } else if (freq.equals("hour")) {
            return 60 * 60;
        } else {
            return 60 * 60 * 24;
        }
    }
}

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

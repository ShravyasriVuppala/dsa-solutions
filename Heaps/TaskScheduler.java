class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Count frequency of each task.
        int[] freq = new int[26];

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Max heap: task with the highest remaining frequency comes first.
        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                maxHeap.offer(new int[]{i, freq[i]});
            }
        }

        // Each entry:
        // [taskIndex, remainingFrequency, nextAvailableTime]
        Queue<int[]> cooldown = new LinkedList<>();

        int time = 0;

        while (!maxHeap.isEmpty() || !cooldown.isEmpty()) {

            // Move all tasks whose cooldown has expired back into the heap.
            while (!cooldown.isEmpty() && cooldown.peek()[2] <= time) {
                int[] task = cooldown.poll();
                maxHeap.offer(new int[]{task[0], task[1]});
            }

            // Execute the most frequent available task.
            if (!maxHeap.isEmpty()) {
                int[] task = maxHeap.poll();

                task[1]--;

                // If this task still has occurrences remaining,
                // put it into cooldown.
                if (task[1] > 0) {
                    cooldown.offer(
                            new int[]{task[0], task[1], time + n + 1}
                    );
                }
            }

            // Even if no task executes, this represents an idle interval.
            time++;
        }

        return time;
    }
}
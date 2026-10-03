class Solution {
    public int leastInterval(char[] tasks, int n) {
        //freq array of tasks
        int[] freq = new int[26];
        for(char c : tasks){
            freq[c - 'A']++;
        }
        //push tasks and their freq to max heap so we greedily pick most remaining tasks
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int i = 0; i < 26; i++){
            if(freq[i] > 0)
                maxHeap.offer(new int[]{i, freq[i]});
        }
        //cool down queue
        Queue<int[]> cooldown = new LinkedList<>();
        int ans = 0;
        while(!maxHeap.isEmpty() || !cooldown.isEmpty()){
            //move any task whose cooldown has expired back into the heap FIRST,
            //so it can be executed this same tick instead of wasting a tick
            if(!cooldown.isEmpty()){
                int[] task = cooldown.peek();
                if(ans >= task[2]){ //if cooling interval is exhausted
                    task = cooldown.poll();
                    maxHeap.offer(new int[]{task[0], task[1]});
                }
            }
            //if maxheap is not empty, poll the most remaining task
            if(!maxHeap.isEmpty()){
                int[] task = maxHeap.poll();
                task[1]--;
                if(task[1] > 0){
                    //more tasks remaining -> add to cool down queue to wait out n intervals
                    cooldown.offer(new int[]{task[0], task[1], ans + n + 1});
                }
            }
            ans++; //increment interval
        }
        return ans;
    }
}
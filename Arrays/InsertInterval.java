class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> ans = new ArrayList<>();
        int start = newInterval[0], end = newInterval[1];
        boolean inserted = false;  // false = still merging overlaps into start/end; true = merged interval placed, remaining intervals just get copied
        for(int[] interval : intervals){
            if(!inserted){
                if(end < interval[0]){  //if newInterval is before current interval, insert newInterval
                    ans.add(new int[]{start, end});
                    inserted = true;
                    ans.add(interval);
                }
                else if(start > interval[1]){  //if newInterval is after current interval
                    ans.add(interval);
                }
                else{
                    start = Math.min(interval[0], start);
                    end = Math.max(interval[1], end);
                }
            }
            else ans.add(interval);
        }
        if(!inserted) ans.add(new int[]{start, end});
        return ans.toArray(new int[ans.size()][]);
    }
}
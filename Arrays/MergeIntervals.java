class Solution {
    public int[][] merge(int[][] intervals) {
        //sort intervals wrt start times
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        result.add(current);
        for(int[] interval : intervals){
            //check if overlapping
            if(interval[0] <= current[1]){
                current[1] = Math.max(current[1], interval[1]);
            }
            else{
                current = interval;
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
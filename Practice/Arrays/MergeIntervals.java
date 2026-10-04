class Solution {
    public int[][] merge(int[][] intervals) {
        //sort intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] currInterval = intervals[0];
        List<int[]> result = new ArrayList<>();
        result.add(currInterval);
        for(int[] interval : intervals){
            //if start time of interval is greater than end time of currinterval -> non-overlapping
            if(interval[0] > currInterval[1]){
                currInterval = interval;
                result.add(currInterval);
            }
            else{
                //overlapping, update end time of currInterval
                currInterval[1] = Math.max(currInterval[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
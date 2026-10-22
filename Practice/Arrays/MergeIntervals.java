class Solution {
    public int[][] merge(int[][] intervals) {
        //sort based on start time
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        int[] current = intervals[0];
        ArrayList<int[]> result = new ArrayList<>();
        for(int[] interval : intervals){
            //if interval start > current interval end, add current to result
            if(interval[0] > current[1]){
                result.add(current);
                current = interval;
            }
            else { // overlapping interval
                current[1] = Math.max(current[1], interval[1]);
            }
        }
        result.add(current);
        return result.toArray(new int[result.size()][]);
    }
}
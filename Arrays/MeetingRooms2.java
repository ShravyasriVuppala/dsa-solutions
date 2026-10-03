/*
Meeting Rooms II (LeetCode 253)

Given an array of meeting time intervals `intervals` where
intervals[i] = [starti, endi], return the minimum number of conference
rooms required so that all meetings can be held without any overlap.

Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Explanation: [0,30] overlaps with both [5,10] and [15,20], so you need a
second room for whichever of those is happening at the same time, but
[5,10] and [15,20] don't overlap each other, so 2 rooms suffice.

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1
Explanation: The two meetings don't overlap, so a single room can host both.

Constraints:
0 <= intervals.length <= 10^4
0 <= starti < endi <= 10^6
*/

import java.util.Arrays;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        //Sweep line - get max no. of overlapping intervals at a given point of time
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        //sort start and end times
        Arrays.sort(start);
        Arrays.sort(end);
        //2 ptrs on start and end
        int startptr = 0, endptr = 0, rooms = 0, max = 0;
        while(startptr < n){
            if(start[startptr] < end[endptr]){
                //a meeting is starting before earliest meeting is elapsed, so increment rooms
                rooms++;
                startptr++;
            }
            else{
                //earliest meeting has ended, free one room
                rooms--;
                endptr++;
            }
            max = Math.max(max, rooms);
        }
        return max;
    }
}

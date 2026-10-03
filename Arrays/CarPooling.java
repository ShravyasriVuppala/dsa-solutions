class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        //Sweep line - no. of passengers at given point in time should be less than capacity
        //Sort trips array based on start time
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        //array with end times
        int n = trips.length;
        int[][] end = new int[n][2];
        for(int i = 0; i < n; i++){
            end[i][1] = trips[i][2];
            end[i][0] = trips[i][0];
        }
        Arrays.sort(end, (a, b) -> a[1] - b[1]);
        int startptr = 0, endptr = 0, passengers = 0;
        while(startptr < n){
            if(trips[startptr][1] < end[endptr][1]){
                //new trip has started before earliest one is dropped off; add passengers new trip
                passengers += trips[startptr][0];
                startptr++;
                if(passengers > capacity)
                    return false;
            }
            else{
                //previous trip has ended, dropped off passengers; remove passengers
                passengers -= end[endptr][0];
                endptr++;
            }
        }
        return true;
    }
}
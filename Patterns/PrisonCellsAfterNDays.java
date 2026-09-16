class Solution {
    public int cellsToBits(int[] cells){
        int state = 0;
        for(int i : cells){
            state <<= 1;
            state |= i;
        }
        return state;
    }
    public int nextDayState(int cells){
        int nextState = 0;
        for(int i = 1; i < 7; i++) {
            int left = (cells >> (i + 1)) & 1;
            int right = (cells >> (i - 1)) & 1;
            if (left == right)
                nextState |= (1 << i);
        }
        return nextState;
    }
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<Integer, Integer> state = new HashMap<>();
        int cellState = cellsToBits(cells);
        while(n > 0){
            //int cellState = cellsToBits(cells);
            //if this state is repeated, find cycle length and update n with module of cycle length
            if (state.containsKey(cellState)) {
                int cycleLength = state.get(cellState) - n;
                n %= cycleLength;
            }
            //if n becomes 0 after modulo, return cells
            if(n == 0){
                break;
            }
            state.put(cellState, n); //store current state with current n
            //compute nextday state and update cells
            cellState = nextDayState(cellState);
            n--;
        }
        //Convert bits to array
        int[] result = new int[8];
        for(int i = 7; i >= 0; i--){
            result[i] = cellState & 1;
            cellState >>= 1;
        }
        return result;
    }
}
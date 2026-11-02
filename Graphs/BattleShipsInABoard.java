class Solution {
    public int countBattleships(char[][] board) {
        // count the battleships baed on its starting cell
        int count = 0, m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                //count only if cell is start of the battleship
                //i.e., cell to its left/top is not 'X'
                if(i > 0 && board[i-1][j] == 'X'){
                    continue;
                }

                if(j > 0 && board[i][j-1] == 'X'){
                    continue;
                }

                count++;
            }
        }
        return count;
    }
}
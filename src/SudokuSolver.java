import javafx.scene.paint.*;

public class SudokuSolver {

    private boolean solve(int[][] board, int counter){

        int col = counter / board.length;
        int row = counter % board.length;

        if (col >= board.length){
            return true;
        }

        if (board[row][col] == 0) {

        for (int n = 1; n <= board.length; n++) {

            if (isValid(n,row,col, board)){
                board[row][col] = n;
                setGui(false, counter, n);

                if (solve(board,counter+1)){
                    return true;
                }

            }
            board[row][col] = 0;
            setGui(true, counter, n);

        }
        }else{
            setGui(false, counter, board[row][col]);
            return solve(board, counter + 1);
        }

        return false;
    }

    public boolean startSolving(int[][] board){

        if(!solve(board, 0)){
            return false;
        }else{
            return true;
        }
    }

    private boolean isValid(int n, int row, int col, int[][] board){

        int i;

        for (i = 0; i < board.length; i++) {
            if(board[row][i] == n){
                return false;
            }
        }

        for (i = 0; i < board.length; i++) {
            if(board[i][col] == n){
                return false;
            }
        }

        //check if block is valid

        final int blockRow = 3 * (row / 3);
        final int blockCol = 3 * (col / 3);
        return isBlockValid(n, board, blockRow, blockRow + 2, blockCol, blockCol + 2);
    }

    private boolean isBlockValid(int n, int[][] board, int starti, int stopi, int startj, int stopj){

        for (int i = starti; i <= stopi; i++) {

            for (int j = startj; j <= stopj; j++) {

                if (board[i][j] == n) {
                    return false;
                }
            }
        }

        return true;
    }

    private void printBoard(int[][] board){

        System.out.println();

        for (int[] row : board){
            System.out.print("|");
            for (int col : row){
                System.out.print(col);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void setGui(boolean wrong, int pos, int number){
        String s = Integer.toString(number);
        Color color = Color.GREEN;
        if(wrong){
            color = Color.RED;
        }
        Gui.setColorOfRect(color, pos);
        Gui.setTextOfRect(s, pos);
    }


}
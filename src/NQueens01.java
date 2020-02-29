//Scanner used to take input from user for the value of N
// Khrishawn Powell
import java.util.Scanner;
import java.util.ArrayList;
public class NQueens01 {
    static Scanner scan;
    static int N = 4;
    static ArrayList<Board> boards;

    static void printBoard(int board [][]){
        System.out.print(" ");
        // these two for loops are used to traverse the bidirectional array
        for (int j = 0; j < N; j++){
            System.out.print(j);
        }
        System.out.print("\n");
        for (int i = 0; i < N; i++){
            System.out.print(i);
            for (int j = 0; j < N; j++){
                if (board[i][j] == 0) {
                    System.out.print("_\t"); // used to show empty positions
                } else {
                    System.out.print("Q\t"); // used to position queens
                }
            }
            System.out.print("\n");
        }
    }

    public static boolean isBoardValid(int[][] board, int col, int row) {
        int i, j;
        for (i = 0; i < col; i++){    //This for loop is used to check the row, on the left hand side
            if (board[row][i] == 1)
                return false;
        }
        for (i =row, j =col; i >= 0 && j >= 0; i--, j-- ){  // Diagonal top left
            if(board[i][j] == 1)
                return false;
        }
        for (i = row, j = col; j >= 0 && i < N; i++, j--){  // Diagonal bottom left
            if (board[i][j] == 1)
                return false;
        }
        return true;

    }

    private static int[][] cloneBoard(int[][] oldBoard){ // clones old board as it carrys out the search to remember what was done before
        int[][] newBoard = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                newBoard[i][j]=oldBoard[i][j];
        return newBoard;
    }

    private static int[][] FoundBoard(){ //used to position chess board for where queens can go.
        while(!boards.isEmpty()){
            Board currentBoard = boards.get(0);
            int[][] board = currentBoard.board;
            int col = currentBoard.nextCol;


            for(int i=0;i<N;i++){
                //printBoard(board);
                //System.out.println("Checking Board Col:"  + col+ " Row:" + i);
                if(isBoardValid(board, col, i)){
                    int[][] boardToAdd = cloneBoard(board);
                    boardToAdd[i][col] = 1;
                    System.out.println("Row: " + i + "      Col:" + col);
                    printBoard(boardToAdd);
                    boards.add(new Board(boardToAdd, col+1));
                    if(col==N-1){
                        return boardToAdd;
                    }
                }
            }
            boards.remove(0);  // will remove index 0 in the queue
        }
        return null;
    }

    private static void initBoards(){
        for(int i=0;i<N;i++){
            int[][] boardToAdd = new int[N][N];
            boardToAdd[i][0] = 1;
            boards.add(new Board(boardToAdd, 1));
        }
    }

    public static void main(String[] arguments){
        scan = new Scanner(System.in);
        //User prompted to enter value of N
        System.out.println("Enter value of N!" );
        N = scan.nextInt();
        boards = new ArrayList<Board>(); // creates array ;ist for possible boards
        initBoards();
        int[][] boardFound = FoundBoard();
        if(boardFound == null){
            System.out.println("No Solution");  // if solution cannot be found user will get no solution message
        } else {
            printBoard(boardFound);
        }

    }

}
class Board {  // used to go to next possible boards
    int[][] board;
    int nextCol;
    public Board(int[][] board1, int col1){
        board = board1;
        nextCol = col1;
    }
}

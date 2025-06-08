package TicTacToe;

import ParkingLot.entities.Ticket;

import java.util.List;
import java.util.Scanner;

// Entites
interface Board {
   void askInput();
   boolean isOver();
   Integer getWinner();

}

class ConcreteBoard implements Board {
    Integer currentPlayer;
    int n;
    int numPlayers;
    boolean isOver = false;
    Integer[][] board;
    Scanner scanner;

    public ConcreteBoard(int size, int players) {
        n = size;
        board = new Integer[n][n];
        numPlayers = players;
        scanner = new Scanner(System.in);

    }
    public void askInput() {
        boolean isValidInput = false;
        if (isOver) {
            System.out.println("GAme OVER");
            return;
        }

        if (currentPlayer == null) currentPlayer = 0;
        else currentPlayer = (currentPlayer + 1) % numPlayers;

        while (!isValidInput) {

            System.out.print("Enter a move player " + currentPlayer);
            String line = scanner.nextLine(); // Reads the full line
            String[] parts = line.trim().split("\\s+");
            if (parts == null || parts.length != 2) continue;

            isValidInput = true;
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            System.out.println("You entered: " + row + " " + col);

            board[row][col] = currentPlayer;
        }

    }

   public boolean isOver() {
        // if (isOver) return true;
        // otherwise
        // go over each row, if first entry empty not over, if ot empty all other entries hsoudlb same
        // otherwise not over else over
        // go over each colum, if first entry empty not over, if other entries not same ad first not over
        // else over
        // go for both diagonal  if empty not over, otherwise if all same as first over otherwise not over
        // winner is curr
        // if not a single entry is empty game over
        // put isOVer = true if over
       return false;
    }

    public Integer getWinner() {
        if (isOver) return currentPlayer;
        else return null;
    }
}

public class TicTacToe {
    Board board;

    TicTacToe(Board b) {
        this.board = b;
    }

    void play () {

        Board board = this.board;
        while (!board.isOver()) {
            board.askInput();
        }

        int  winner = board.getWinner();
        System.out.println("Winner is " + winner);

    }
}

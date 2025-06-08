package SnakesAndLadders;

import java.util.List;
import java.util.Map;

public class SnakesAndLadders {
    List<Goti> gotis;
    Dice dice;
    int turnIndex;
    Board board;

    public class Board {
        public int size;
        List<Snakes> snakes;
        List<Ladder> ladders;
        Map<Integer, Snakes> snakeAtBoxnumber;
        Map<Integer, Ladder> ladderAtBox;
    }

    public class Snakes {
        int start, end;

    }
    public class Ladder {
        int start, end;
    }
    public interface Dice {
        public int roll();
    }
    public class ConcreteDice {
        int val;
        int size;
        public int roll() {
            // retur nrandom number b/ w 1 nad Nl
            return 1;
        }

    }

    private boolean isOver() {
        // go over each Goti and check if isWon() = true for each Gotis
        return true;
    }
    private int nextTurn(int currentTurn, int boardSize) {
        currentTurn = (currentTurn + 1) % boardSize;
         return currentTurn;
    }

    public void start() {
        while (isOver()) {
            turnIndex = nextTurn(turnIndex, board.size);
            int val = dice.roll();
            gotis.get(turnIndex).runTurn(val, board);
        }
    }

    public interface Goti {
        void runTurn(int diceVal, Board board);
    }

    public class ConcreteGoti implements Goti {
        int currentBox;
        int isWon;
        public void runTurn (int diceVal, Board board) {
            // check if diceVal is legit
            // if legit increment currentboc by diceVal
            // check if ladder at that point, check if snake at that point
            // and update currentBox;
            // if currentBox is N*N , isWon = true
        }
    }
}

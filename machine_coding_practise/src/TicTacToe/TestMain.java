package TicTacToe;
import java.util.*;
import java.io.*;

public class TestMain {
    public static void main(String[] args) throws Exception {
//        FastReader in = new FastReader();
//        PrintWriter out = new PrintWriter(System.out);
        Board b = new ConcreteBoard(10,2);

        TicTacToe game = new TicTacToe(b);
        game.play();
        // Write test code or calls here

//        out.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

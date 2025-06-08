package Logger;


import java.util.*;
import java.io.*;

public class LoggerTest {
    public static void main(String[] args) throws Exception {
//        FastReader in = new FastReader();
//        PrintWriter out = new PrintWriter(System.out);


        LoggerRateLimiter limiter = new SlidingWindowLoggerRateLimiter(3, 2);
        limiter.allowKey("1");
        limiter.allowKey("2");
        limiter.allowKey("3");
        limiter.allowKey("4");
        limiter.addLog("1", "one");
        Thread.sleep(500);
        limiter.addLog("1", "one2");
        Thread.sleep(500);
        limiter.addLog("1", "one3");
        Thread.sleep(500);
        limiter.addLog("1", "one4");
        Thread.sleep(1500);
        limiter.addLog("1", "one5");
        limiter.addLog("1", "five");


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

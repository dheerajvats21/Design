import java.util.*;
import java.io.*;

public class LRUCacheTest {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/input.txt"));
//        System.setOut(new PrintStream("src/output.txt"));

        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);


        // Write test code or calls here

        LRUCache<Integer, String> cache = new LRUCacheImpl<>(4);
        cache.put(1, "One");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");

        String x = cache.get(2);
        System.out.println(x);
        x = cache.get(1);
        System.out.println(x);

        cache.put(5, "five");

        x = cache.get(4);
        System.out.println(x);


        out.close();
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

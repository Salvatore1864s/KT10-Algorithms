import java.io.*;

public class Task2 {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int q = fs.nextInt();

        long[] values = new long[q];
        long[] mins = new long[q];
        long[] maxs = new long[q];
        int size = 0;
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < q; i++) {
            String command = fs.next();

            switch (command) {
                case "PUSH": {
                    long x = fs.nextLong();
                    values[size] = x;
                    mins[size] = size == 0 ? x : Math.min(x, mins[size - 1]);
                    maxs[size] = size == 0 ? x : Math.max(x, maxs[size - 1]);
                    size++;
                    break;
                }
                case "POP":
                    size--;
                    break;
                case "MIN":
                    out.append(mins[size - 1]).append('\n');
                    break;
                case "MAX":
                    out.append(maxs[size - 1]).append('\n');
                    break;
            }
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr, len;
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer); ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            do { c = read(); } while (c <= ' ' && c != -1);
            while (c > ' ') { sb.append((char)c); c = read(); }
            return sb.toString();
        }
        long nextLong() throws IOException { return Long.parseLong(next()); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

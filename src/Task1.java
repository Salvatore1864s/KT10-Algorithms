import java.io.*;
import java.util.*;

public class Task1 {
    static class Interval {
        long start, end;
        Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        Interval[] intervals = new Interval[n];

        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(fs.nextLong(), fs.nextLong());
        }

        Arrays.sort(intervals, (a, b) -> {
            int byEnd = Long.compare(a.end, b.end);
            if (byEnd != 0) return byEnd;
            return Long.compare(a.start, b.start);
        });

        int answer = 0;
        long lastEnd = Long.MIN_VALUE;

        for (Interval interval : intervals) {
            if (answer == 0 || interval.start >= lastEnd) {
                answer++;
                lastEnd = interval.end;
            }
        }

        System.out.println(answer);
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
        long nextLong() throws IOException {
            int c;
            do { c = read(); } while (c <= ' ' && c != -1);
            long sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long value = 0;
            while (c > ' ') { value = value * 10 + c - '0'; c = read(); }
            return value * sign;
        }
        int nextInt() throws IOException { return (int) nextLong(); }
    }
}

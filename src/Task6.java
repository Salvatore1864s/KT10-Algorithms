import java.io.*;
import java.util.*;

public class Task6 {
    static class Point {
        long x, y, distance;
        int index;

        Point(long x, long y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.distance = x * x + y * y;
        }
    }

    static int compare(Point a, Point b) {
        int cmp = Long.compare(a.distance, b.distance);
        if (cmp != 0) return cmp;
        cmp = Long.compare(a.x, b.x);
        if (cmp != 0) return cmp;
        cmp = Long.compare(a.y, b.y);
        if (cmp != 0) return cmp;
        return Integer.compare(a.index, b.index);
    }

    static void swap(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    static int partition(Point[] points, int left, int right, int pivotIndex) {
        Point pivot = points[pivotIndex];
        swap(points, pivotIndex, right);
        int store = left;

        for (int i = left; i < right; i++) {
            if (compare(points[i], pivot) < 0) {
                swap(points, store++, i);
            }
        }

        swap(points, store, right);
        return store;
    }

    static void selectFirstK(Point[] points, int k) {
        int left = 0, right = points.length - 1;
        int target = k - 1;

        while (left < right) {
            int pivotIndex = left + (right - left) / 2;
            int position = partition(points, left, right, pivotIndex);

            if (position == target) return;
            if (position < target) left = position + 1;
            else right = position - 1;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int k = fs.nextInt();

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(fs.nextLong(), fs.nextLong(), i + 1);
        }

        selectFirstK(points, k);
        Arrays.sort(points, 0, k, Task6::compare);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < k; i++) {
            Point p = points[i];
            out.append(p.x).append(' ')
               .append(p.y).append(' ')
               .append(p.index).append('\n');
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

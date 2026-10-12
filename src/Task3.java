import java.io.*;

public class Task3 {
    static int partition(int[] a, int left, int right, int pivotIndex) {
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, right);
        int store = left;

        for (int i = left; i < right; i++) {
            if (a[i] < pivot) {
                swap(a, store, i);
                store++;
            }
        }

        swap(a, store, right);
        return store;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static int select(int[] a, int target) {
        int left = 0, right = a.length - 1;

        while (left < right) {
            int pivotIndex = left + (right - left) / 2;
            int position = partition(a, left, right, pivotIndex);

            if (position == target) return a[position];
            if (position < target) left = position + 1;
            else right = position - 1;
        }

        return a[left];
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int k = fs.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        System.out.println(select(a, k - 1));
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
        int nextInt() throws IOException {
            int c;
            do { c = read(); } while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int value = 0;
            while (c > ' ') { value = value * 10 + c - '0'; c = read(); }
            return value * sign;
        }
    }
}

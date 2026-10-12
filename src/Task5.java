import java.io.*;
import java.util.*;

public class Task5 {
    static class Entry {
        int key;
        long value;
        int frequency;
        Entry(int key, long value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

    static class LFUCache {
        private final int capacity;
        private int minFrequency = 0;
        private final HashMap<Integer, Entry> entries = new HashMap<>();
        private final HashMap<Integer, LinkedHashSet<Integer>> byFrequency = new HashMap<>();

        LFUCache(int capacity) {
            this.capacity = capacity;
        }

        long get(int key) {
            Entry entry = entries.get(key);
            if (entry == null) return -1;
            increaseFrequency(entry);
            return entry.value;
        }

        void put(int key, long value) {
            Entry entry = entries.get(key);

            if (entry != null) {
                entry.value = value;
                increaseFrequency(entry);
                return;
            }

            if (entries.size() == capacity) {
                LinkedHashSet<Integer> leastRecent = byFrequency.get(minFrequency);
                int victim = leastRecent.iterator().next();
                leastRecent.remove(victim);
                if (leastRecent.isEmpty()) byFrequency.remove(minFrequency);
                entries.remove(victim);
            }

            Entry added = new Entry(key, value, 1);
            entries.put(key, added);
            byFrequency.computeIfAbsent(1, unused -> new LinkedHashSet<>()).add(key);
            minFrequency = 1;
        }

        private void increaseFrequency(Entry entry) {
            int oldFrequency = entry.frequency;
            LinkedHashSet<Integer> oldSet = byFrequency.get(oldFrequency);
            oldSet.remove(entry.key);

            if (oldSet.isEmpty()) {
                byFrequency.remove(oldFrequency);
                if (minFrequency == oldFrequency) minFrequency++;
            }

            entry.frequency++;
            byFrequency.computeIfAbsent(entry.frequency, unused -> new LinkedHashSet<>()).add(entry.key);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int capacity = fs.nextInt();
        int q = fs.nextInt();
        LFUCache cache = new LFUCache(capacity);
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < q; i++) {
            String command = fs.next();
            int key = fs.nextInt();

            if (command.equals("GET")) {
                out.append(cache.get(key)).append('\n');
            } else {
                long value = fs.nextLong();
                cache.put(key, value);
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

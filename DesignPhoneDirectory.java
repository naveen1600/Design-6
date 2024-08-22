// Time Complexity: O(1)
// Space Complexity: O(n)

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class PhoneDirectory {
    private HashSet<Integer> set;
    private Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    public int get() {
        int number = -1;
        if (!q.isEmpty()) {
            number = q.poll();
            set.remove(number);
        }
        return number;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if (!set.contains(number)) {
            q.add(number);
            set.add(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
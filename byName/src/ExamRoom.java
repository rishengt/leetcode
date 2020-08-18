/**
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
 * If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
 *
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in,
 * and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.
 * It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 *
 *
 *
 * Example 1:
 *
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 * ​​​​​​​
 *
 * Note:
 *
 * 1 <= N <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 */
import java.util.*;

/**O(n)的你自己用treeSet写就完了，，官方答案。。。。log N 的才值得大书特书。。。。。。。*/
public class ExamRoom {
    TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
    TreeSet<Integer> set = new TreeSet<>();
    int N;

    public ExamRoom(int N) {
        this.N = N;
    }

    public int seat() {
        int res = 0;
        if (set.size() == 0) {
            res = 0;
        } else {
            int first = set.first(), last = set.last();
            Integer max = map.isEmpty() ? null : map.lastKey();
            if (max == null || first >= max / 2 || N - 1 - last > max / 2) {
                if (first >= N - 1 - last) {
                    addInterval(0, first);
                    res = 0;
                } else {
                    addInterval(last, N - 1 - last);
                    res = N - 1;
                }
            } else {
                TreeSet<Integer> temp = map.get(max);
                int index = temp.first();
                int next = set.higher(index);
                int mid = (next + index) / 2;
                removeInterval(index, max);
                addInterval(index, mid - index);
                addInterval(mid, next - mid);
                res = mid;
            }
        }
        set.add(res);
        return res;
    }

    public void leave(int p) {
        Integer pre = set.lower(p);
        Integer next = set.higher(p);
        set.remove(p);
        if (next != null) {
            removeInterval(p, next - p);
        }
        if (pre != null) {
            removeInterval(pre, p - pre);
            if (next != null) {
                addInterval(pre, next - pre);
            }
        }
    }

    private void addInterval(int index, int len) {
        len -= len & 1;  // trim to even number
        map.putIfAbsent(len, new TreeSet<>());
        map.get(len).add(index);
    }

    private void removeInterval(int index, int len) {
        len -= len & 1;
        Set<Integer> temp = map.get(len);
        if (temp == null) {
            return;
        }
        temp.remove(index);
        if (temp.size() == 0) {
            map.remove(len);
        }
    }
}

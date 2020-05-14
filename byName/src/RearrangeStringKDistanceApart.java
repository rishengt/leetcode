import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a string s and an int k, rearrange the string so that every repetitive character in the string are at least distance k apart
 *
 * if the string is not rearrangable, return an empty string ""
 *
 * 贪心+哈希表的做法
 * 一个哈希表来建立字符和其出现次数之间的映射，然后需要一个堆来保存这每一堆映射，按照出现次数来排序。
 * 然后如果堆不为空我们就开始循环，我们找出k和str长度之间的较小值，然后从0遍历到这个较小值，对于每个遍历到的值，如果此时堆为空了，说明此位置没法填入字符了，
 * 返回空字符串，否则我们从堆顶取出一对映射，然后把字母加入结果res中，此时映射的个数减1，如果减1后的个数仍大于0，则我们将此映射加入临时集合v中，
 * 同时str的个数len减1，遍历完一次，我们把临时集合中的映射对由加入堆中
 */
public class RearrangeStringKDistanceApart {
    public static void main(String[] args) {
        //System.out.println(new RearrangeStringKDistanceApart().rearrangeString("abbaa", 3));
        System.out.println(new RearrangeStringKDistanceApart().rearrangeString("abbaa", 2));
        System.out.println(new RearrangeStringKDistanceApart().rearrangeString("abcaa", 2));
        System.out.println(new RearrangeStringKDistanceApart().rearrangeString("aabcadefghjk", 5));
        //System.out.println(new RearrangeStringKDistanceApart().rearrangeString("abbaa", 1));
    }
    public String rearrangeString(String str, int k) {
        if (str == null || k <= 1) return str;
        int[] array = new int[26]; /**将字母出现的次数跟字母做映射，这招经常用到，细品，强记， 巧妙的映射让我又想起墙角的N皇后啊*/
        for (int i = 0 ; i < str.length(); i++) {
            char c = str.charAt(i);
            array[c - 'a']++;/**看过来，这里就是次数映射了*/
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[1] == a[1] ? a[0] - b[0] : b[1] - a[1];/**按照出现次数排序，出现次数一样就按字母大小排咯*/
            }
        });
        for (int j = 0; j < 26; j++) {
            if (array[j] != 0) {
                heap.offer(new int[] {j, array[j]});/**j代表那个字母， array【j】代表出现的次数*/
            }
        }
        StringBuilder res = new StringBuilder();
        int len = str.length();
        while (!heap.isEmpty()) {
            int cnt = Math.min(len, k);/**这里代表string填到后半程长度不够了该怎么填， 细啊！！！！*/
            List<int[]> v = new ArrayList<>();
            for (int i = 0; i < cnt; i++) {/**这样可以把heap里面靠前的（代表有所重复的）字母按cnt的间隔排好*/
                if (heap.isEmpty()) return "";/**用光了heap里面的字母代表不可能实现每一个重复的字母都能被隔cnt那么多位*/
                int[] t = heap.poll();/**把靠前的怼出来*/
                res.append((char)('a' + t[0]));/**把字母还原并怼到sb里面*/
                if (--t[1] > 0) {/**要是加了以后字母出现次数还是比零大，怒进list*/
                    v.add(t);
                }
                len--;
            }
            for (int[] t : v) {
                heap.add(t);
            }
        }
        return res.toString();
    }
}

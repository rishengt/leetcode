import java.util.ArrayList;
import java.util.List;

/**
 * Change one Array to a new array which contains all its subarray sum
 *
 * example:
 * [1,2,3] -> [1,3,6,2,5,3]
 */
public class AllSubArraySum {
    public static void main(String[] args) {
        System.out.println(new AllSubArraySum().find(new int[]{1, 2, 3}));
    }

    public List<Integer> sum(int[] arr) {
        int len = arr.length;
        List<Integer> list = new ArrayList<>();

        int slow = 0;
        while (slow < arr.length) {
            int fast = slow;
            int temp = arr[slow];
            while (fast < len) {
                if (slow == fast) {
                    list.add(temp);
                    fast++;
                    continue;
                }
                temp += arr[fast];
                list.add(temp);
                fast++;
            }
            slow++;
        }
        return list;
    }

    public int[] find(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int temp = 0;
            for (int j = i; j < arr.length; j++) {
                temp += arr[j];
                list.add(temp);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);

        }
        return res;
    }

}

package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class ModifyElement {
    public static void main(String[] args) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{3,3});
        queue.peek()[1]--;
        System.out.println(queue.peek()[1]);
        /**并不是queue裏面的element能被直接修改，是queue存的element能被修改的話就可以被修改*/
    }
}
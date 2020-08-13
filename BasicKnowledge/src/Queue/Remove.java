package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Remove {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.remove();/**跟stack不一样，queue的remove可以不传参数（stack要么传index要么传object），不传参数的queue。remove跟poll（）效果一样*/
        queue.offer("a");/**这东西跟stack的push又不一样，stack的push是push什么返回什么，queue的offer只返回boolean。。*/
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.remove("D"));/**这跟上面的不传参数的不一样，没有会返回false，上面的没有会抛异常*/
    }
}

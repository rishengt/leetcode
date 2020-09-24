package ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class insert {
    public static void main(String[] args) {
        List<Integer> k = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        k.add(1,3);
        /** add( index, element ) 就是 insert 咯，O(n) 操作*/
        k.addAll(b);
    }
}

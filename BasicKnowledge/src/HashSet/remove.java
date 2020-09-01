package HashSet;

import java.util.HashSet;

public class remove {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.remove(1);
        System.out.println(set.size()); //0;
    }
}

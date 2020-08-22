package TreeSet;

import java.util.TreeSet;

public class treeSet {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>((a,b) -> b-a);
        for(int i : set){
            System.out.println(i);
        }
    }
}

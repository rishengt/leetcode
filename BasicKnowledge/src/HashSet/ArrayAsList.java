package HashSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayAsList {
    public static void main(String[] args) {
        HashSet<List<Integer>> set = new HashSet<>();
        HashSet<List<Character>> set2 = new HashSet<>();
        HashSet<List<String>> set3 = new HashSet<>();
        set.add(Arrays.asList(1,2,3));
        set.add(Arrays.asList(1,2,3));
        set2.add(Arrays.asList('1','2','3'));
        set2.add(Arrays.asList('1','2','3'));
        set3.add(Arrays.asList("1","2","3"));
        set3.add(Arrays.asList("1","2","3"));
        System.out.println(set.size());// 1
        System.out.println(set2.size());// 1
        System.out.println(set3.size());// 1
        System.out.println(Arrays.asList(1,2,3) == Arrays.asList(1,2,3));/**只有在Arrays.asList()括号里直接宣告东西才能用hash去重**/
        System.out.println(Arrays.asList(1,2,3).equals(Arrays.asList(1,2,3)));
    }
}

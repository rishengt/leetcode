package HashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayAsList {
    public static void main(String[] args) {
        HashSet<List<Integer>> set = new HashSet<>();
        HashSet<List<Character>> set2 = new HashSet<>();
        HashSet<List<String>> set3 = new HashSet<>();
        HashSet<List<Integer>> set4 = new HashSet<>();
        HashSet<List<user>> set5 = new HashSet<>();
        List<user> l5 = new ArrayList<>();
        l5.add(new user(1,"lky"));
        l5.add(new user(2,"trs"));
        List<user> l6 = new ArrayList<>();
        l6.add(new user(1,"lky"));
        l6.add(new user(2,"trs"));
        set5.add(l5);
        set5.add(l6);
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(2);
        l2.add(3);
        List<Character> l3 = new ArrayList<>();
        l3.add('1');
        l3.add('2');
        l3.add('3');
        List<Character> l4 = new ArrayList<>();
        l4.add('1');
        l4.add('2');
        l4.add('3');
        set.add(Arrays.asList(1,2,3));
        set.add(Arrays.asList(1,2,3));
        set.add(l1);
        set.add(l2);
        set2.add(Arrays.asList('1','2','3'));
        set2.add(Arrays.asList('1','2','3'));
        set2.add(l3);
        set3.add(Arrays.asList("1","2","3"));
        set3.add(Arrays.asList("1","2","3"));
        set4.add(l1);
        set4.add(l2);
        System.out.println(set.size());// 1
        System.out.println(set2.size());// 1
        System.out.println(set3.size());// 1
        System.out.println(set4.size());//1
        System.out.println(set5.size());//2
        System.out.println(Arrays.asList(1,2,3) == Arrays.asList(1,2,3));/**HashSet 可以用于排列顺序一样的 且里面存放的是八大基本类型的 Arraylist 以去重**/
        System.out.println(Arrays.asList(1,2,3).equals(Arrays.asList(1,2,3)));
    }

    static class user{
        int id;
        String name;
        public user(int id, String name){
            this.id = id;
            this.name = name;
        }
    }
}

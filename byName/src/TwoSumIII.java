import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * Example 1:
 *
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * Example 2:
 *
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 */


/****我的解法太弱智，机智的请看https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/twoSum%E9%97%AE%E9%A2%98%E7%9A%84%E6%A0%B8%E5%BF%83%E6%80%9D%E6%83%B3.md*/
public class TwoSumIII {
    public static void main(String[] args) {
        TwoSumIII s = new TwoSumIII();
        s.add(1);
        s.add(3);
        s.add(5);
        System.out.println(s.find(4));
        System.out.println(s.find(7));
    }
    List<Integer> list;
    Map<Integer, Integer> map;
    public TwoSumIII() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        list.add(number);
        map.put(number,list.indexOf(number));
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int i = 0; i<list.size();i++){
            if(map.containsKey(value-list.get(i))&&map.get(value-list.get(i))!=i){
                return true;
            }
        }
        return false;
    }
}

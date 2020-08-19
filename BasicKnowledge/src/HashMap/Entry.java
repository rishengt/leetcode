package HashMap;
import java.util.*;
public class Entry {
    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.put(1,Arrays.asList(1,2,3,4));
        map.put(2,new ArrayList<>());
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> e: map.entrySet()){
            if(e.getValue().size()==0){
                list.add(e.getKey());
            }
        }
        System.out.println(list);
        for(Map.Entry e: map.entrySet()){
//            System.out.println(e.size()); /** 这个你get 不到，因为你没在for loop 里面把type cast了所以现在e 的key 跟value 都变成object了，你见过哪个object有method的？？*/
        }
    }
}

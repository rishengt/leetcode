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
    }
}

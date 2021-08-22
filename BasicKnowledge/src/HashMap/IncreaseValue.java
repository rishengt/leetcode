package HashMap;

import java.util.HashMap;

public class IncreaseValue {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("lky",0);
        map.put("trs",0);
        int fk = map.get("lky");
        fk++;
        map.put("lky", fk);
        System.out.println(map.get("lky"));
        map.putIfAbsent("lky",0);
        System.out.println(map.get("lky"));
    }
}

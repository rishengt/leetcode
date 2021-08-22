import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class putToConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> map = new ConcurrentHashMap<>();
        Thread tMain = Thread.currentThread();
        new putToConcurrentHashMap().putInput(map);
        for(Map.Entry e: map.entrySet()){
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }
        tMain.sleep(300);
        System.out.println(map.size());
    }

    public void putInput(ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> map){
        Runnable r1 = () ->{
            List<String> list = new ArrayList<>();
            list.add("POSITIVE");
            list.add("NEUTRAL");
            list.add("NEGATIVE");
            for(int i = 0; i<10; i++){
                map.putIfAbsent(String.valueOf(i), new ConcurrentHashMap<>());
                map.get(String.valueOf(i)).putIfAbsent("POSITIVE",0);
                map.get(String.valueOf(i)).putIfAbsent("NEUTRAL",0);
                map.get(String.valueOf(i)).putIfAbsent("NEGATIVE",0);
                for(String s: list){
                    if(s.equals("POSITIVE")){
                        int temp = map.get(String.valueOf(i)).get("POSITIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("POSITIVE",temp);
                    }
                    if(s.equals("NEUTRAL")){
                        int temp = map.get(String.valueOf(i)).get("NEUTRAL");
                        temp++;
                        map.get(String.valueOf(i)).put("NEUTRAL",temp);
                    }
                    if(s.equals("NEGATIVE")){
                        int temp = map.get(String.valueOf(i)).get("NEGATIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("NEGATIVE",temp);
                    }
                }
            }
        };
        Runnable r2 = () ->{
            List<String> list = new ArrayList<>();
            list.add("POSITIVE");
            list.add("NEUTRAL");
            list.add("NEGATIVE");
            for(int i = 0; i<10; i++){
                map.putIfAbsent(String.valueOf(i), new ConcurrentHashMap<>());
                map.get(String.valueOf(i)).putIfAbsent("POSITIVE",0);
                map.get(String.valueOf(i)).putIfAbsent("NEUTRAL",0);
                map.get(String.valueOf(i)).putIfAbsent("NEGATIVE",0);
                for(String s: list){
                    if(s.equals("POSITIVE")){
                        int temp = map.get(String.valueOf(i)).get("POSITIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("POSITIVE",temp);
                    }
                    if(s.equals("NEUTRAL")){
                        int temp = map.get(String.valueOf(i)).get("NEUTRAL");
                        temp++;
                        map.get(String.valueOf(i)).put("NEUTRAL",temp);
                    }
                    if(s.equals("NEGATIVE")){
                        int temp = map.get(String.valueOf(i)).get("NEGATIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("NEGATIVE",temp);
                    }
                }
            }
        };
        Runnable r3 = () ->{
            List<String> list = new ArrayList<>();
            list.add("POSITIVE");
            list.add("NEUTRAL");
            list.add("NEGATIVE");
            for(int i = 0; i<10; i++){
                map.putIfAbsent(String.valueOf(i), new ConcurrentHashMap<>());
                map.get(String.valueOf(i)).putIfAbsent("POSITIVE",0);
                map.get(String.valueOf(i)).putIfAbsent("NEUTRAL",0);
                map.get(String.valueOf(i)).putIfAbsent("NEGATIVE",0);
                for(String s: list){
                    if(s.equals("POSITIVE")){
                        int temp = map.get(String.valueOf(i)).get("POSITIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("POSITIVE",temp);
                    }
                    if(s.equals("NEUTRAL")){
                        int temp = map.get(String.valueOf(i)).get("NEUTRAL");
                        temp++;
                        map.get(String.valueOf(i)).put("NEUTRAL",temp);
                    }
                    if(s.equals("NEGATIVE")){
                        int temp = map.get(String.valueOf(i)).get("NEGATIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("NEGATIVE",temp);
                    }
                }
            }
        };
        Runnable r4 = () ->{
            List<String> list = new ArrayList<>();
            list.add("POSITIVE");
            list.add("NEUTRAL");
            list.add("NEGATIVE");
            for(int i = 0; i<10; i++){
                map.putIfAbsent(String.valueOf(i), new ConcurrentHashMap<>());
                map.get(String.valueOf(i)).putIfAbsent("POSITIVE",0);
                map.get(String.valueOf(i)).putIfAbsent("NEUTRAL",0);
                map.get(String.valueOf(i)).putIfAbsent("NEGATIVE",0);
                for(String s: list){
                    if(s.equals("POSITIVE")){
                        int temp = map.get(String.valueOf(i)).get("POSITIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("POSITIVE",temp);
                    }
                    if(s.equals("NEUTRAL")){
                        int temp = map.get(String.valueOf(i)).get("NEUTRAL");
                        temp++;
                        map.get(String.valueOf(i)).put("NEUTRAL",temp);
                    }
                    if(s.equals("NEGATIVE")){
                        int temp = map.get(String.valueOf(i)).get("NEGATIVE");
                        temp++;
                        map.get(String.valueOf(i)).put("NEGATIVE",temp);
                    }
                }
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

import java.util.*;
/**
 * Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * Output: ["daniel"]
 * Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").
 * Example 2:
 *
 * Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
 * Output: ["bob"]
 * Explanation: "bob" used the keycard 3 times in a one-hour period ("21:00","21:20", "21:30").
 * Example 3:
 *
 * Input: keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
 * Output: []
 * Example 4:
 *
 * Input: keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
 * Output: ["clare","leslie"]
 *
 *
 * Constraints:
 *
 * 1 <= keyName.length, keyTime.length <= 105
 * keyName.length == keyTime.length
 * keyTime are in the format "HH:MM".
 * [keyName[i], keyTime[i]] is unique.
 * 1 <= keyName[i].length <= 10
 * keyName[i] contains only lowercase English letters.
 */
public class AlertUsingSameKeyCardThreeorMoreTimesinaOneHourPeriod {
    public static void main(String[] args) {
        System.out.println(new AlertUsingSameKeyCardThreeorMoreTimesinaOneHourPeriod().alertNames(new String[]{"leslie","leslie","leslie","clare","clare","clare","clare"}, new String[]{"13:00","13:20","14:00","18:00","18:51","19:30","19:49"}));
    }
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> ans = new ArrayList<>();
        HashMap<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<keyName.length;i++){
            map.putIfAbsent(keyName[i],new ArrayList<>());
            map.get(keyName[i]).add(getTime(keyTime[i]));
        }
        for(Map.Entry<String, List<Integer>> entry: map.entrySet()){
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            if(list.size()>=3){
                for(int i = 2; i<list.size(); i++){
                    if(list.get(i)-list.get(i-2)<=60) {
                        ans.add(entry.getKey());
                        break;
                    }
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
    public int getTime(String time){
        String[] t = time.split(":");
        return Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
    }
}

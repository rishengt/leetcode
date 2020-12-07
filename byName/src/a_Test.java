import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.math.BigInteger;
import java.util.*;

public class a_Test {
    public static void main(String[] arg) {
        System.out.println(new a_Test().maxOperations(new int[]{1,2,3,4},5));
    }
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }
        // for(int i: map.keySet()){
        //     if(map.contains)
        // }
        int ans = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            int n = entry.getKey();
            int m = entry.getValue();
            int find = k-n;
            if(map.containsKey(find)){
                if(find == n){
                    ans += m/2;
                }
                else{
                    ans +=Math.min(m,map.get(find));
                    map.remove(find);
                }
            }
        }
        return ans;
    }
}




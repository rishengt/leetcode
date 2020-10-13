import java.util.HashMap;

/**
 * Given an integer n, you must transform it into 0 using the following operations any number of times:
 *
 * Change the rightmost (0th) bit in the binary representation of n.
 * Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through 0th bits are set to 0.
 * Return the minimum number of operations to transform n into 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 0
 * Output: 0
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: The binary representation of 3 is "11".
 * "11" -> "01" with the 2nd operation since the 0th bit is 1.
 * "01" -> "00" with the 1st operation.
 * Example 3:
 *
 * Input: n = 6
 * Output: 4
 * Explanation: The binary representation of 6 is "110".
 * "110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
 * "010" -> "011" with the 1st operation.
 * "011" -> "001" with the 2nd operation since the 0th bit is 1.
 * "001" -> "000" with the 1st operation.
 * Example 4:
 *
 * Input: n = 9
 * Output: 14
 * Example 5:
 *
 * Input: n = 333
 * Output: 393
 *
 *
 * Constraints:
 *
 * 0 <= n <= 109
 */
public class MinimumOneBitOperationstoMakeIntegersZero_Recursion {
    HashMap<String, Integer> map1 = new HashMap<>();
    HashMap<String, Integer> map2 = new HashMap<>();
    public int minimumOneBitOperations(int n) {
        return dfs(Integer.toBinaryString(n));
    }
    public int dfs(String n){
        if(n.equals("0")) return 0;
        if(n.equals("1")) return 1;
        if(map1.containsKey(n)){
            return map1.get(n);
        }
        if(n.charAt(0) == '1'){
            String temp = "1";
            for(int i = 2; i<n.length(); i++){
                temp+="0";
            }
            int k = 1+helper(n.substring(1))+dfs(temp);
            map1.put(n,k);
            return k;
        }else{
            int k = dfs(n.substring(1));
            map1.put(n,k);
            return k;
        }
    }

    public int helper(String n){
        if(n.equals("0")) return 1;
        if(n.equals("1")) return 0;
        if(map2.containsKey(n)){
            return map2.get(n);
        }
        if(n.charAt(0) == 1){
            int k = dfs(n.substring(1));
            map2.put(n,k);
            return k;
        }else{
            String temp = "1";
            for(int i = 2; i<n.length(); i++){
                temp+="0";
            }
            int k = 1+ helper(n.substring(1))+dfs(temp);
            map2.put(n,k);
            return k;
        }
    }
}

//dfs -> [xxxxx] -> [00000]
//[1xxxx] -> [11000] -> [01000] -> [0(1000)]
//
//helper: [xxxx] -> [1000]
//    [1xxx] ->[1(xxx)]->[1000]
//    [0xxx] ->[xxx]->0(100)->1100->1000
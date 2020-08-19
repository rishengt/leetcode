/**
 * Given a string s containing only digits. Return all possible valid IP addresses that can be obtained from s. You can return them in any order.
 *
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single points and cannot have leading zeros.
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "1111"
 * Output: ["1.1.1.1"]
 * Example 4:
 *
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 * Example 5:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3000
 * s consists of digits only.
 */
import java.util.*;
public class RestoreIPAddresses {
    public static void main(String[] args) {
//        System.out.println(new RestoreIPAddresses().restoreIpAddresses("25525511135"));
//        System.out.println(new RestoreIPAddresses().restoreIpAddresses("0000"));
//        System.out.println(new RestoreIPAddresses().restoreIpAddresses("1111"));
//        System.out.println(new RestoreIPAddresses().restoreIpAddresses("010010"));
//        System.out.println(new RestoreIPAddresses().restoreIpAddresses("101023"));
        System.out.println(new RestoreIPAddresses().restoreIpAddresses("011011"));
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        addPoint(s,0,ans,0,"");
        return ans;
    }
    public void addPoint(String s, int count, List<String> ans, int start, String temp){
        if(count == 4){ /** 按段来分出口。。。。*/
            if(start == s.length())
                ans.add(temp);
            return;
        }
        for(int i = 1; i<=3;i++){
            if(start + i>s.length()) break;
            if(i!=1 && s.charAt(start) == '0') break; /**要是你是0开头截取的又超过一位，那么你就不用考虑了*/
            String cut = s.substring(start, start+i);
            if(Integer.valueOf(cut) <= 255){
                addPoint(s,count+1, ans,start+i,temp+cut+(count==3? "":"."));
            }
        }
    }
}

import java.util.Arrays;

/**
 * There is a room with n bulbs, numbered from 0 to n-1, arranged in a row from left to right. Initially all the bulbs are turned off.
 *
 * Your task is to obtain the configuration represented by target where target[i] is '1' if the i-th bulb is turned on and is '0' if it is turned off.
 *
 * You have a switch to flip the state of the bulb, a flip operation is defined as follows:
 *
 * Choose any bulb (index i) of your current configuration.
 * Flip each bulb from index i to n-1.
 * When any bulb is flipped it means that if it is 0 it changes to 1 and if it is 1 it changes to 0.
 *
 * Return the minimum number of flips required to form target.
 *
 *
 *
 * Example 1:
 *
 * Input: target = "10111"
 * Output: 3
 * Explanation: Initial configuration "00000".
 * flip from the third bulb:  "00000" -> "00111"
 * flip from the first bulb:  "00111" -> "11000"
 * flip from the second bulb:  "11000" -> "10111"
 * We need at least 3 flip operations to form target.
 * Example 2:
 *
 * Input: target = "101"
 * Output: 3
 * Explanation: "000" -> "111" -> "100" -> "101".
 * Example 3:
 *
 * Input: target = "00000"
 * Output: 0
 * Example 4:
 *
 * Input: target = "001011101"
 * 001111111
 * 001000000
 * 001011111
 * 00101
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= target.length <= 10^5
 * target[i] == '0' or target[i] == '1'
 */
public class BulbSwitcherIV {
    public static void main(String[] args) {
        System.out.println(new BulbSwitcherIV().minFlips("001011101"));
    }

    public int minFlips(String target) {/**我TM不服，凭什么！！！！*/
        char[] s = target.toCharArray();
        int ret = 0;
        for(int i = 0;i < s.length;i++){
            if(i > 0 && s[i-1] != s[i]){/**要是前后不一样，那肯定就是翻了一次，我能理解*/
                ret++;
            }
            if(i == 0 && s[i] == '1'){/**corner case， 一开始11怎么办？品*/
                ret++;
            }
        }
        return ret;
    }


    /************************************LC 超时************************************************************************/
    public int minFlipsII(String target) {
       char[] temp = new char[target.length()];
        Arrays.fill(temp,'0');
       String k = String.valueOf(temp);
       int ans = 0;
       if(k.equals(target)) return 0;
       for(int i = 0; i< temp.length; i++){
           if(temp[i] != target.charAt(i)){
               flip(i,temp);
               ans++;
           }
       }
       return ans;
    }

    public void flip(int i, char[] target){
        for(int k = i; k< target.length; k++){
            target[k] ^=1;
        }
    }
}

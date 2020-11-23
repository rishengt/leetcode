/**
 * Given an array of integers arr of even length n and an integer k.
 *
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 *
 * Return True If you can find a way to do that or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * Example 4:
 *
 * Input: arr = [-10,10], k = 2
 * Output: true
 * Example 5:
 *
 * Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
 * Output: true
 */
public class CheckIfArrayPairsAreDivisiblebyk {
    /**
     * Idea :
     * Given 2 nums 'a' and 'b':
     * If a % k == x and b % k == k - x :
     * then (a + b) is divisible by k
     *
     * Proof :
     *
     * a % k == x
     * b % k == k - x
     * (a + b) % k = ((a + b)%k)%k = (a%k + b%k)%k = (x + k - x)%k = k%k = 0
     * Hence, (a + b) % k == 0 and (a + b) is divisible by k.
     * Approach :
     *
     * Keep count of remainders of all elements of arrays
     * frequency[0] keeps all elements divisible by k, so these elements can form group with themselves.
     * Hences total number of such elements such be even.
     * for every element with remainder of i there should be a element with remainder of k-i.
     * Hence, frequency[i] should be equal to frequency[k-i]
     */

    public static void main(String[] args) {
        System.out.println(new _CheckIfArrayPairsAreDivisiblebyk().canArrange(new int[]{1,2,3,4,5,10,6,7,8,9},5));
    }
    public boolean canArrange(int[] arr, int k) {
        int[] frequency = new int[k];
        for(int num : arr){
            num %= k;
            if(num < 0) num += k;//when arr[i] is negative, transfer to positve remainder.
            frequency[num]++;
        }
        if(frequency[0]%2 != 0) return false;

        for(int i = 1; i <= k/2; i++)
            if(frequency[i] != frequency[k-i]) return false;

        return true;
    }
}

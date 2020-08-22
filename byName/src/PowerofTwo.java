/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: 218
 * Output: false
 */
public class PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        if(n<=0) return false;
        while(n%2 == 0) n/=2;
        return n == 1;
    }

    /**众所周知，一个数如果是2的指数级别，那么它的二进制形式有且只有一个1。。那么。。。。*/
    public boolean isPowerOfTwoII(int n){
        if(n<=0) return false;
        return (n&(n-1)) == 0;
    }
}

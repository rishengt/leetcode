/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class Pow_x_n {
    public static void main(String[] args) {
        System.out.println(new Pow_x_n().myPow(2.0,10));
        System.out.println(new Pow_x_n().myPow(2.10000, 3));
        System.out.println(new Pow_x_n().myPow(2.00000, -2));
    }
    public double quickMul(double x, int n){
        if(n == 0) return 1.0;
        if(n == 1) return x;
        double y = quickMul(x,n/2); /**又学到一招，recursion代码能只调用一次就只调用一次，能大大提高代码运行速度*/
        if(n%2 == 0) return y*y;
        return x*y*y;
    }

    public double myPowII(double x, int n) {
        if(n == 0) return 1.0;
        return n<0?1/quickMul(x,-n):quickMul(x,n);
    }

    /********总体思路是一样的，忘记了可以看你自己手机上那个leetcode 软件的题解******/
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        /**because INT_MIN is -2147483648(2^-31) but INT_MAX is 2147483647(2^31-1) ,so n = -n is failed!!*/
        if(n == Integer.MIN_VALUE){
            x = x * x;
            n = n/2;
        }
        if(n < 0) {
            n = -n;
            x = 1/x;
        }

        return (n%2 == 0) ? myPow(x * x, n/2) : x *  myPow(x * x, n/2);
    }
}

package Bit;

public class DetermindEven {
    public static void main(String[] args) {
        /**用bit operator 来判断一个数是不是 even number*/
        int i = 2;
        int j = 3;
        System.out.println((i&1) != 1); /**众所周知，一个东西如果是偶数的话，它二进制的最后以为肯定不为1，当你那它去 & 1的时候，它最后一位的0 和 1最后一位的1会得到0，不可能得出1*/
        System.out.println(((i*3)&1) != 1);
        System.out.println((j&1) != 1);
        System.out.println(9999&1); /** 1*/
        System.out.println(9998&1);/** 0*/

        /**总结，基数&1 得 1。 偶数&1 得 0*/
    }
}

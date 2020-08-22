package Bit;

public class RemoveLast1 {
    public static void main(String[] args) {
        /**用 n&(n-1) 可以把n用二进制表示的最后一个1变成0**/
        System.out.println(Integer.toBinaryString(6));//0110
        System.out.println(Integer.toBinaryString(6&(6-1)));//0100
    }
}

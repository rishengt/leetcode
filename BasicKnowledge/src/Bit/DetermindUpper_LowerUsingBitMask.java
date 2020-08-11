package Bit;

public class DetermindUpper_LowerUsingBitMask {
    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString('a'));//0110 0001
//        System.out.println(Integer.toBinaryString('A'));//0100 0001
//        System.out.println(Integer.toBinaryString('z'));//0111 1010
//        System.out.println(Integer.toBinaryString('Z'));//0101 1010
        new DetermindUpper_LowerUsingBitMask().getCase('a');
        new DetermindUpper_LowerUsingBitMask().getCase('A');
        System.out.println(new DetermindUpper_LowerUsingBitMask().isUpper('a'));
        System.out.println(new DetermindUpper_LowerUsingBitMask().isUpper('A'));

    }
    /**
     * 看上面，众所周知（🐶），一个字母是不是大小写，看他二进制的前面第三位（或者从最后右往左数，由0开始，第五位），如果是1，那么就是小写，如果为0，那么就是大写。。。。。所以在ASCII里面，小写字母要比大写的大！
     * 那么，我么怎么来用bit mask利用这一特质来分辨大小写呢？？请看下面表演
     */

    public void getCase(char c){
        int b = 0b00100000;/**众所周知（🐶），你想用二进制表示一个数，只要在前面加个0b或者0B就好了*/
//        c &= b;
        if(((c>>5)&1) == 0) System.out.println("Upper"); /**从最right开始算*/
        else System.out.println("Lower");
    }

    public boolean isUpper(char c){
        int b = 0x20;/**众所周知（🐶），0010 0000 因为前面四个数变十进制是2，后面四个数变十进制是0，所以也可以写成0x20；这种常见（🐶）写法叫hexadecimal。。*/
        c &= b;
        if(c == 0) return true;
        return false;
    }
}

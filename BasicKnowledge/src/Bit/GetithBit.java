package Bit;

/**
 * 判断一个二进制数的第ith位是否为1（所有二进制数的计数方式都是从右往左第0⃣️位开始算的）
 */
public class GetithBit {
    public static void main(String[] args) {
        System.out.println(new GetithBit().getith(0b0100,2));
        System.out.println(new GetithBit().getithII(0b0001,0));
    }
    /**有两种方法，一，你自身这个数>>i 位然后 & 1， 要是不为0⃣️，代表你的第i位是一；*/
    public boolean getith(int b, int i){
        return (((b>>i)&1) !=0);
    }

    /**二，你拿 1 << i 位，然后跟原数比，要是不为0， 原数的第i位为一；*/
    public boolean getithII(int b, int i){
        return ((b&(1<<i)) != 0);
    }
}

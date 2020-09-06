package Math.bigInteger;
import java.math.*;
public class bigint {


    /**BigInteger 不能用binary1的运算符操作，会报错**/
    public static void main(String[] args) {
        /**你想要造BigInteger只能给个String形式的Integer 它**/
        BigInteger k = new BigInteger("12345");
        /**BigInteger 类自带了很多方法，你只能用它自带的方法进行加减乘除*/
        k.add(new BigInteger("1234"));
        k.mod(new BigInteger("134"));

        /**不过用得到BigInteger的算法题应该挺少的，而且用了一般也会错的，数据很大的时候它有一定丢失的可能*/
    }
}

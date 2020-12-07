package BigInteger;

import java.math.BigInteger;

public class newBigInteger {
    public static void main(String[] args) {
        BigInteger k = new BigInteger("12");
        BigInteger n = new BigInteger("10101110",2);
        System.out.println(n);
        n = n.mod(k);
        System.out.println(n);
        n.toString();
        System.out.println(Integer.MAX_VALUE);
    }

}

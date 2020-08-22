package Math;
import java.util.*;
public class random {
    public static void main(String[] args) {
        System.out.println(new random().getRandomNumber(-10,10));
    }

    /***** 下面的都是在一个 range 里面 随意创造数的 方式 ********************/
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}

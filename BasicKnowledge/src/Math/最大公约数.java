package Math;

public class 最大公约数 {
    public static void main(String[] args) {
        System.out.println(new 最大公约数().gcd(9,15));
    }
    public int gcd(int a, int b){
        return b>0? gcd(b, a%b) : a;
    }
}

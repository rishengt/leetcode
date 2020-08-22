package Bit;

public class Or {
    public static void main(String[] args) {
        System.out.println(true | false);
        System.out.println(false | true);
        System.out.println(true || false);
        System.out.println(false || true);
        boolean k = true;
        System.out.println(k |= false);
    }
}

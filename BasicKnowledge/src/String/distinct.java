package String;

public class distinct {
    public static void main(String[] args) {
        String s1= "trsalky";
        String s2= "trsalkytrsalky";
        System.out.println(s2.chars().distinct().count());
    }
}

package StringBuilder;

public class replace {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("1123456");
        sb.replace(0,1, "k ");
        System.out.println(sb.toString());
    }
}

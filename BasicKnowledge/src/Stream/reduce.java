package Stream;

public class reduce {
    public static void main(String[] args) {
        String fk = "lky.csv", fk2 = "trsalky";

        System.out.println(fk.lastIndexOf(".") == -1? "":fk.substring(fk.lastIndexOf(".")+1));
        System.out.println(fk2.lastIndexOf(".") == -1? "":fk2.substring(fk2.lastIndexOf(".")+1));
    }
}

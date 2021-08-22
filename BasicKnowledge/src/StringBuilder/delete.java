package StringBuilder;

public class delete {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("-trsalky");
        sb.delete(1,2);
        sb.deleteCharAt(2);
        sb.insert(0,'1');
        System.out.println(sb.toString());
        sb.deleteCharAt(0);
    }
}

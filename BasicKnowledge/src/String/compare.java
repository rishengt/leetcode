package String;

public class compare {
    public static void main(String[] args) {
        /**正数证明前面的比较大，负数证明前面的比较小**/
        /**空的字符串应该就是最小的了，其他按ASCII 来对比*/
        System.out.println("".compareTo(" "));
        System.out.println(" ".compareTo("1"));
        System.out.println(" ".compareTo("a"));
        System.out.println("1".compareTo("a"));
        System.out.println("a".compareTo("A"));
        System.out.println("1".compareTo("2"));
        System.out.println("-1".compareTo("0"));
    }
}

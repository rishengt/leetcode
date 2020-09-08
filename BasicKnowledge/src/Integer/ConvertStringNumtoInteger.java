package Integer;

public class ConvertStringNumtoInteger {
    public static void main(String[] args) {
        String s = "2";
        int k = Integer.valueOf(2);
        System.out.println(k+1);
        String bit = "01000101";
        System.out.println(Integer.parseInt(bit,2));/**这也不是第一次遇到了，后面的数字告诉了前面的string是什么样的进制，parse过来输出都是10进制的*/
    }
}

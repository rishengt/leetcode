package Bit;

public class QijiyinQiao {
    public static void main(String[] args) {
        /**利用或 '｜'操作和空格' '将英文转为小写*/
        System.out.println(('a' | ' ') == 'a');//true
        System.out.println(('A' | ' ') == 'a');//true

        /**利用与'&'操作和下划线'_'将英文转为大写*/
        System.out.println(('b' & '_') == 'B');//true
        System.out.println(('B' & '_') == 'B');//true

        /**利用异或'^'操作和空格进行英文大小写互换*/
        System.out.println(('d' ^ ' ') == 'D');//true
        System.out.println(('D' ^ ' ') == 'd');//true

        /**用异或'^'判断两个数是否异号*/
        System.out.println((-1^3)<0);//true
        System.out.println((-1^-2)<0);//false

        /**不用临时变量交换两个数*/
        int a = 1, b = 2;
        a ^=b;
        b ^=a;
        a ^=b;
        System.out.println(a);//2
        System.out.println(b);//1

        /**加一*/
        System.out.println(-~1);//2

        /**减一*/
        System.out.println(~-1);//0

        /**
         * if a ^ b = c, then a ^ c = b;
         */
    }
}

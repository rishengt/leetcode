package String;

/**
 * https://dzone.com/articles/java-string-format-examples
 * 个人觉得String.format()其实记不住太多，只能要用的时候自己去👆的网址看了。。。
 */

public class format {
    public static void main(String[] args) {
        String one = "trs";
        String two = "lky";

        System.out.println(String.format("%s : %s", one,two)); //trs : lky
        System.out.println(String.format("%2d : %2d", 1,0));//  1 :  0  数字前面空两格
    }
}

package Regex;

public class Regex {
    public static void main(String[] args) {
        /********** \\W 好像就 _ underscore去不掉 其他的好像很猛的样子*/
        System.out.println("clone:".replaceAll("\\W",""));//clone
        System.out.println("clon_e".replaceAll("\\W",""));//clon_e
        System.out.println("clon:e".replaceAll("\\W",""));//clone

        String sk = "dfkjg{sdjflsd}".replaceAll("[^{}]","");//{}
        String sk1 = "dfkjg{sdjflsd}".replaceAll("[{}]","");
        System.out.println(sk);
        System.out.println(sk1);
    }
}

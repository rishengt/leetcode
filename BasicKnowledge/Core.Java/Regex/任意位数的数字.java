public class 任意位数的数字 {
    public static void main(String[] args) {
        String fk = "<img width =   \"900\" height=\"988\" class=\"image\" >" + "cnmcnmcnm traslkgj sdfkjasdlfjas" + "<img width=\"1898\" height=\"988\" class=\"image\" >";
//        fk = fk.replaceAll("<img[^>]*width=\"([0-9]*)\"", "<img width=\"100%\"");
        System.out.println(fk);
        String number = "wb";
        System.out.println(number.substring(0,1));
        String pattern=".*width\\s*=\\s*\"(([7-9]\\d{2})|([1-9]\\d{3,}))\".*";
        String pattern2 = "width\\s*=\\s*\"([0-9]*)\"";
        if(fk.matches(pattern)) {
            fk = fk.replaceAll("width\\s*=\\s*\"(([7-9]\\d{2})|([1-9]\\d{3,}))\"", "");
        }
        System.out.println(fk);
    }
}

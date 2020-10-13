public class GrayCode {
    public static void main(String[] args) {
        System.out.println(new GrayCode().cnm(2));
    }
    int a = 1;
    public int cnm(int a){
        this.a = a;
        System.out.println(this.a);
        return a;
    }
}

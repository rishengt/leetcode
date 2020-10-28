public class DivisableByThree {
    public static void main(String[] args) {
        System.out.println(new DivisableByThree().isDivisable("23"));
        System.out.println(new DivisableByThree().isDivisable("0081"));
        System.out.println(new DivisableByThree().isDivisable("022"));
    }
    public int isDivisable(String s){
        int ans = 0;
        int k = Integer.valueOf(s);

        for(int i = 0; i<s.length(); i++){
            String c = s;
            for(int j = 0; j<=9; j++){
                s = s.substring(0,i)+j+s.substring(i+1);

                if(Integer.valueOf(s) != k && Integer.valueOf(s)%3 == 0 ){
//                    System.out.println(s);
                    ans++;
                }
            }
            s = c;
        }
        return Integer.valueOf(s) % 3 == 0? ans+1:ans;
    }
}

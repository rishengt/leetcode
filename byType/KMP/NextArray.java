public class NextArray {
    public static void main(String[] args) {
        System.out.println(new NextArray().KMP("abababc","ababc"));
        System.out.println(new NextArray().KMP("ababa","bab"));
    }

    /**Use KMP to return the first index of the the string pattern appearing in the string**/
    public int KMP(String s, String p){
        int next[] = new int[p.length()+1];
        next[0] = -1;
        for(int i = 1, j = 0; i<p.length(); i++){
            while(j>0 && p.charAt(i)!=p.charAt(j)){
                j = next[j];
            }
            if(p.charAt(i) == p.charAt(j)){
                j++;
            }
            next[i+1] = j;
        }

        for(int i = 0, j = 0; i<s.length(); i++){
            while(j>0 && s.charAt(i) != p.charAt(j)){
                j = next[j];
            }
            if(s.charAt(i) == p.charAt(j)){
                j++;
            }
            if(j == p.length()){
                return i-j+1;
            }
        }
        return -1;
    }
}

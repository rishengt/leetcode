public class singleton {
    private static singleton instance;
    private singleton(){}
    public static synchronized singleton getInstance(){
        if(instance == null){
            instance = new singleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(new singleton().isPalindrome("abab"));
    }

    public boolean isPalindrome(String s){
        int head = 0, tail = s.length()-1;
        char cHead, cTail;
        while(head <= tail){
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if(cHead != cTail){
                return false;
            }else{
                head++;
                tail--;
            }
        }
        return true;
    }
}

/**
 * Given a string of words, each separated by a space, and a limit on the number of characters that can be put on one line (line width),
 * return an array of lines with the original words in order such that no line is longer than the given line width
 * and each line fits as many words as possible. No words can be split.
 */
import java.util.*;
import java.util.stream.Stream;

public class WordWrap {
    public static void main(String[] args) {
        String[] ans = new WordWrap().wordWrap("This is a test string to be broken into multiple lines",20);
        for(String s: ans)
            System.out.println(s);
    }
    public String[] wordWrap(String s, int min){
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if(i == s.length()-1){
                list.add(sb.toString());
                break;
            }
            if(s.charAt(i)!=' '){
                sb.append(s.charAt(i));
            }else{
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        String[] words = new String[list.size()];
        for(int i = 0; i<words.length; i++){
            words[i] = list.get(i);
        }
        int n = s.length()/min + 1;
        if(s.length()%min == 0){
            n = s.length()/min;
        }
        String[] ans = new String[n];
        int count = 0;
        sb = new StringBuilder();
        for(int i = 0; i< words.length; i++){
            if(count == n-1){
                for(int j = i; j<words.length; j++){
                    sb.append(words[j]);
                    sb.append(" ");
                }
                ans[count] = sb.toString().trim();
            }
            if(sb.length() > min){
                int index = sb.lastIndexOf(words[--i]);
                sb.delete(index,sb.length());
                ans[count++] = sb.toString().trim();
                sb = new StringBuilder();
                i--;
            }
            else{
                sb.append(words[i]);
                sb.append(" ");
            }
        }
        return ans;
    }
}

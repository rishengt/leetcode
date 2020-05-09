import java.util.*;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class ParenthesesGenerate {
    public static void main(String[] args) {
        System.out.println(new ParenthesesGenerate().generateParenthesis(3));
    }
    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            sb.append("(");
            sb.append(")");
        }
        char[] kao = sb.toString().toCharArray();
        Arrays.sort(kao);
        List<String> ans = new ArrayList<>();
        backtrack(kao,ans,new ArrayList<>(), new ArrayList<>());
        return ans;
    }

    public void backtrack(char[] s, List<String> ans, List<Character> temp, List<Integer> index){
        if(temp.size() == s.length){
            StringBuilder sb = new StringBuilder();
            for(char c : temp){
                sb.append(c);
            }
            String parentheses = sb.toString();
            if(isValid(parentheses)){
                ans.add(parentheses);
            }
        }
        for(int i = 0; i<s.length; i++){
            if(index.contains(i)) continue;
            if(i>0&&!index.contains(i-1)&&s[i-1]==s[i])continue;
            index.add(i);
            temp.add(s[i]);
            backtrack(s,ans,temp,index);
            temp.remove(temp.size()-1);
            index.remove(index.size()-1);
        }
    }

    public boolean isValid(String s){
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i< s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                char b = stack.isEmpty()? '*' : stack.pop();
                if(b!= map.get(s.charAt(i))) return false;
            }else{
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}

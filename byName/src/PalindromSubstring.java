/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class PalindromSubstring {
    public static void main(String[] args) {
        System.out.println(new PalindromSubstring().longestPalindrome("babad"));
        System.out.println(new PalindromSubstring().longestPalindrome("cbbd"));
        System.out.println(new PalindromSubstring().longestPalindrome("d"));
    }

    public String longestPalindrome(String s){
        boolean[][] dp = new boolean[s.length()][s.length()];
        int compare = Integer.MIN_VALUE;
        String ans="";
        for(int i = 0; i<s.length();i++){
            for(int j = 0; j<=i; j++){ /**注意j的边界。。。。。*/
                dp[j][i] = s.charAt(i)==s.charAt(j)&&(i-j<=2||dp[j+1][i-1]);/**括号里的顺序也是要命的，因为刚开始i=0，i-1会炸如果你写前面的话*/
                if(dp[j][i]){
                    if(i-j+1>compare){
                        compare = Math.max(compare,i-j+1);
                        ans = s.substring(j,i+1);
                    }
                }
            }
        }
        return ans;
    }

    public String longestPalindromeII(String s){
        int start = 0, end = 0, maxLen = 0;
        for(int i = 0; i<s.length(); i++){
            int len1 = expandFromCenter(s,i,i);/**奇数长度的string，单个中心*/
            int len2 = expandFromCenter(s,i,i+1);/**偶数长度的string，两个中心*/
            int tempMaxLen = Math.max(len1,len2);
            if(tempMaxLen>maxLen){
                maxLen = tempMaxLen;
                start = i - (tempMaxLen-1)/2;/**因为目前 i 是中心，所以 要找start你要减去长度的一半*/
                end = i+tempMaxLen/2;/**这里细节一点都不比dp少啊，一个不注意就挂了*/
            }
        }
        return s.substring(start,end+1);
    }

    public int expandFromCenter(String s, int i, int j){
        while(i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }
        return j-i-1;/**这里的细节也很重要。。。*/
    }
}

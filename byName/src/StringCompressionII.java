/**
 * Run-length encoding is a string compression method that works by replacing consecutive identical characters
 * (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run).
 * For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
 *
 * Notice that in this problem, we are not adding '1' after single characters.
 *
 * Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
 *
 * Find the minimum length of the run-length encoded version of s after deleting at most k characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabcccd", k = 2
 * Output: 4
 * Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6.
 * Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5,
 * for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d.
 * Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
 * Example 2:
 *
 * Input: s = "aabbaa", k = 2
 * Output: 2
 * Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
 * Example 3:
 *
 * Input: s = "aaaaaaaaaaa", k = 0
 * Output: 3
 * Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * 0 <= k <= s.length
 * s contains only lowercase English letters.
 */
public class StringCompressionII {
    public static void main(String[] args) {
        System.out.println(new StringCompressionII().getLengthOfOptimalCompression("aaabcccd",2));
        System.out.println(new StringCompressionII().getLengthOfOptimalCompression("aabbaa",2));
        System.out.println(new StringCompressionII().getLengthOfOptimalCompression("aaaaaaaaaaa",0));
    }

    /**第一印象时我会这样设计DP方案：dp[i][k]表示前i个字母里删除k个所能得到的最优解（即最短的行程长度编码）。突破口应该是考虑第k个删除的字母的位置j在哪里，所以状态转移方程大致是：

     dp[i][k] = max{dp[j-1][k-1] + s[j+1:i]的行程长度编码} for j=1,2,...i
     但是我们在跑第二个例子 s = "aabbaa", k = 2 的时候会发现问题：我们删除了中间的bb，则第一段aa和最后一段的aa会拼接起来。而上述的表达式中完全没有考虑到拼接的处理。

     换句话说，dp[j-1][k-1]对应的原码，和s[j+1:i]这段原码，其run-length encode不是简单的长度相加关系，而是有可能拼接在一起，形成更短的run-length encode.
     为了便于处理这种“拼接关系”，我们需要提供更多的信息接口。所以会有以下的想法：
        令dp[i][k][ch][num]表示前i个字母、删除k个字母、最后的字母是ch、最后的字母连续出现的次数是num，所对应的最优解（即最短的行程长度编码）。

     那么求解dp[i][k][ch][num]的关键是找到哪合法的前驱状态dp[?][?][?][?]可以转移到它。实际写代码的过程中，发现这样实现比较复杂。
     我们可以用另外一种DP的写法，就是看dp[i][k][ch][num]能够影响哪些后继节点。
     事实上，它直接影响的就是dp[i+1][?][?][?]的状态，我们只要讨论dp[i+1][?][?][?]的取值可能性即可。

     1. 如果第i+1个元素我们是要删除的。那么dp[i][k][ch][num]的结果原封不动地传递给了dp[i+1][k+1][ch][num].
        也就是前i+1个元素里、我们删除k+1个字母、最后的字母是ch、最后的字母连续出现的次数是num，有一种解就是与dp[i][k][ch][num]的状态完全相同。
     2. 如果第i+1个元素我们要保留，说明我们有机会更新dp[i+1][k][s[i]-'a'][?]的值。
        注意，既然保留了第i+1个字母，显然最后的字母就是s[i+1]，所以我们只需要确定最后一个问号即可。
        这个“连续”数目显然取决于s[i+1]是否和ch是否相同。如果不同，那么说明无法拼接上，所以我们直接可以更新的是 dp[i+1][k][s[i]-'a'][1]。
        怎么更新？就是dp[i][k][ch][num]+1，即run-length encode单纯地加上1.
     3. 如果第i+1个元素我们要保留，并且s[i+1]和ch相同，那么说明dp[i][k][ch][num]的最后字母ch可以再拼接一个，也就是说我们可以更新的是dp[i][k][ch][num+1]。
        怎么更新，是简单地在dp[i][k][ch][num]基础上加1吗？并不一定。其实需要根据num分情况讨论（附上了例子）来确定增加的run-length encode的长度：

     if (num==1) add = 1;  // e.g: a -> a2
     else if (num>=2 && num<=8) add = 0; // e.g: a3->a4
     else if (num==9) add = 1; // e.g: a9->a10;
     else if (num>=10) add = 0; // e.g: a10->a11;
     最终的结果是要求在所有n个字母中删去K个字母，所以需要再dp[n][K][?][?]中挑选一个最小值。

     另外需要注意的一个技巧是，我们不需要把第四个维度开到100，事实上num>=10之后，即使再拼接相同的字母，run-length encode也都不会再改变。
     所以我们可以把所有num>=10的状态都归为同一个状态，来节省空间的开辟。
     */
    public int getLengthOfOptimalCompression(String s, int K) {
        int dp[][][][] = new int[101][101][27][11];
        int n = s.length();
        s = "$"+s;
        for(int i = 0; i<=n;i++){
            for(int k = 0; k<=K; k++){
                for(int ch = 0; ch<=26; ch++){
                    for(int num = 0; num<=10; num++){
                        dp[i][k][ch][num] = Integer.MAX_VALUE;
                        //dp[i][k][ch][num]: the optimal solution for s[1:i]
                        //with k digits removed, last letter as ch, the consecutive number of ch as num;
                    }
                }
            }
        }
        /**这里的意义是什么。。。是你没有东西可以删的时候的最优解*/
        dp[0][0][26][0] = 0;

        for(int i = 0; i<n; i++){
            /** 这里的min也是很难理解。。。。*/
            for(int k = 0; k<=Math.min(K,i); k++){
                for(int ch = 0; ch <=26; ch++){
                    for(int num = 0; num <= 10; num++){
                        int cur = dp[i][k][ch][num];
                        if(cur ==Integer.MAX_VALUE) continue;

                        //delete s[i+1]
                        dp[i+1][k+1][ch][num] = Math.min(dp[i+1][k+1][ch][num], cur);

                        //keep s[i+1]
                        if(s.charAt(i+1)-'a' == ch){
                            int add = 0;
                            if(num == 1) add = 1; // a -> a2
                            else if (num >= 2 && num<=8) add = 0; //a3 -> a4
                            else if (num == 9) add =1; // a9->a10
                            else if (num == 10) add = 0; // a10 -> a11
                                    /** 这里的min也是很难理解。。。。难道？事实上num>=10之后，即使再拼接相同的字母，run-length encode也都不会再改变。*/
                            dp[i+1][k][ch][Math.min(num+1,10)] = Math.min(dp[i+1][k][ch][Math.min(num+1,10)], cur+add);
                        }
                        else{
                            dp[i+1][k][s.charAt(i+1)-'a'][1] = Math.min(dp[i+1][k][s.charAt(i+1)-'a'][1], cur+1);
                        }
                    }
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for(int ch = 0; ch <=26; ch++){
            for(int num = 0; num <=10; num++){
                ret = Math.min(ret,dp[n][K][ch][num]);
            }
        }
        return ret;
    }
}

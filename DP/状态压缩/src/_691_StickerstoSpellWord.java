import java.util.Arrays;

/**
 * We are given N different types of stickers. Each sticker has a lowercase English word on it.
 *
 * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
 *
 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 *
 * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
 *
 * Example 1:
 * Input:
 * ["with", "example", "science"], "thehat"
 * Output:
 * 3
 * Explanation:
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target string.
 *
 * Example 2:
 * Input:
 * ["notice", "possible"], "basicbasic"
 * Output:
 * -1
 * Explanation:
 * We can't form the target "basicbasic" from cutting letters from the given stickers.
 * Note:
 *
 * stickers has length in the range [1, 50].
 * stickers consists of lowercase English words (without apostrophes).
 * target has length in the range [1, 15], and consists of lowercase English letters.
 * In all test cases, all words were chosen randomly from the 1000 most common US English words,
 * and the target was chosen as a concatenation of two random words.
 * The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
public class _691_StickerstoSpellWord {
    public static void main(String[] args) {
        System.out.println(new _691_StickerstoSpellWord().minStickers(new String[]{"with","example","science"},"thehat"));
//        System.out.println(new _691_StickerstoSpellWord().minStickers(new String[]{"notice","possible"},"basicbasic"));
    }
    /**
     * 设置状态数组dp[i],dp的大小是 N = 2^n, 其中n是target的大小。怎么理解？将dp的索引i进行二进制拆解，i的每一个bit表示的是target对应位置的字符是否得到了满足。
     * 比如n=3时，dp数组的大小N=8，对应的状态有 000,001,010,011,100,101,110,111. 举个例子，i=3 (即011)表示target的末两位的字符得到了满足，
     * 但第一位的字符还没有得到满足。dp[i]表示在状态i下，需要的sticker的最少数目。
     *
     * 注意：这种状态的排列有一个非常好的性质。任何状态，只可能由位于其前面的状态转移得到，不可能从后面的状态转移得到。
     * 比如i=3(即 011)这个状态，只可能从i=0,1,2转移过来（通过使用某些合适的sticker）；
     * 再比如i=4(即100)这个状态，只可能从i=0转移过来。这种状态转移的性质，非常适合dp根据i从前往后的遍历。
     *
     * 所以，dp的大循环就是 for (state=0; state<(1<<n); state++).
     * 对于该状态state，我们尝试每一个sticker[k]，计算状态i经过sticker[k]的帮助后得到的状态new_state（注意已经分析过new_state肯定是大于state的），
     * 那么dp[new_state]就可以得到更新dp[new_state]=min(dp[new_state], dp[state]+1)
     *
     * 所有的状态i都遍历过之后，答案的输出就是 dp[N-1]
     */
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int N = 1<<n;
        int[] dp = new int[N];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int state = 0; state<N; state++){
            if(dp[state] != Integer.MAX_VALUE){ /**思考或debug一下为什么要不等于MAX, 不等于max代表你是从上一个state起跳的，妙啊。。。。*/
                for(String str: stickers){
                    int new_state = findNextState(state,target,str);
                    dp[new_state] = Math.min(dp[new_state], dp[state]+1);/**这里又为啥是dp[state]+1 啊？？？*/
                }
            }
        }
        return dp[N-1] == Integer.MAX_VALUE? -1:dp[N-1];/**这个仔细想想就可以理解，减一以后变成了二进制11111。。。全满一的状态*/
    }

    public int findNextState(int state, String target, String str){
        int n = target.length();
        for(char c: str.toCharArray()){
            //loop over each character in target, if equals to ch and not filled, then set as filled
            for(int k = 0; k<n; k++){
                if(target.charAt(k) == c && ((state >> k)&1) == 0){/**这里不用怀疑你在bit里面的理解，因为Guan神的是反过来的。。*/
                    state = state+(1<<k);
                    break;
                }
            }
        }
        return state;
    }
}

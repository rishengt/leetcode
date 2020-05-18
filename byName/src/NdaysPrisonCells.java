import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 */
public class NdaysPrisonCells {
    public static void main(String[] args) {
        System.out.println(new NdaysPrisonCells().prisonCellsAfterNdays(new int[]{0,1,0,1,1,0,0,1},7));
    }
    public int[] afterNdays(int[] cells, int N){
        if(cells==null || cells.length==0 || N<=0) return cells;
        boolean hasCycle = false;
        int cycle = 0;
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<N;i++){
            int[] next = nextDay(cells);
            String key = Arrays.toString(next);
            if(!set.contains(key)){ //store cell state
                set.add(key);
                cycle++;
            }
            else{ //hit a cycle
                hasCycle = true;
                break;
            }
            cells = next;
        }
        if(hasCycle){
            N%=cycle;
            for(int i=0;i<N;i++){
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    public int[] nextDay(int[] cells){
        int[] ans = new int[cells.length];
        for(int i = 1; i< cells.length -1; i++){ /**注意这样设置i是因为首尾两个cell都只有一个邻居。。*/
            if(cells[i-1] == cells[i+1]){
                ans[i] = 1;
            }else{
                ans[i] = 0;
            }
        }
        return ans;
    }

/***********************************************************位运算都他妈想一天了还是没搞懂，算了吧*****************************************************/
    public int[] prisonCellsAfterNdays(int[] cells, int N){
        Map<Integer, Integer> seen = new HashMap();

        // state  = integer representing state of prison
        int state = 0;
        for (int i = 0; i < 8; ++i) {
            if (cells[i] > 0);
                /**这一步很神奇的就弄了个state， 我也不知道它具体的意义是嘛。。。。
                 * 但是就是没当你cells是一的时候，他就把这个一往前怼i位，然后用state 亦或一下它， 亦或是相反得1。。
                 * 做出来的效果呢，就是把原数反转过来了， 如
                 * 01011001 倒过来变成
                 * 10011010
                 */
                state ^= 1<<i; /**state = state^(1<<i), 天啊菜🐔你连这个写法都看不懂，吃屎啦你*/
        }
        // While days remaining, simulate a day
        while (N > 0) {
            // If this is a cycle, fast forward by
            // seen.get(state) - N, the period of the cycle.
            if (seen.containsKey(state)) {
                N %= seen.get(state) - N;
            }
            seen.put(state, N);

            if (N >= 1) {
                N--;
                state = nextDay(state);
            }
        }
        // Convert the state back to the required answer.
        int[] ans = new int[8];
        for (int i = 0; i < 8; ++i) {
            if (((state >> i) & 1) > 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    public int nextDay(int state) {
        int ans = 0;
        // We only loop from 1 to 6 because 0 and 7 are impossible,
        // as those cells only have one neighbor.
        for (int i = 1; i <= 6; ++i) {
            if (((state >> (i-1)) & 1) == ((state >> (i+1)) & 1)) { /**&1 是只有1和1才得1*/
                ans ^= 1 << i;
            }
        }
        return ans;
    }
}

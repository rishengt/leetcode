import java.util.List;

/**
 * Aladdin wants to travel around the world and will choose a circular path to fly on his magical carpet. The carpet needs enough magic to take him from
 * one place to another. He knows that after traveling some distance, he can find a magic source that will enable the carpet to travel a further distance.
 *
 * There are n magical sources along the circular path numbered from 0 to n-1. Initially, the carpet has no magic and Aladdin can use a portal to jump
 * to any magical source and start his journey. The carpet consumes units of magic equal to the units of distance travelled. He needs to choose a point
 * to start his journey that will allow him to complete his journey. Determine the lowest index of the starting points from which Aladdin can start his
 * journey and visit all of the places in the circular path in order. If there is no solution, return -1.
 *
 * For example, there are n = 4 sources of magic along his route: magic = [3,2,5,4] and dist = [2,3,4,2]. The first attempt is starting at the first source,
 * magic[0] = 3. He transports there without cost and collects 3 units of magic. The distance to the next point is dist[0] = 2. It takes 2 units of magic
 * to get there and he collects magic[1] = 2 units upon arrival, so he has 3-2+2 = 3 units of magic after making his first carpet ride.
 * Continuing along the journey:
 *      3-dist[1] + magic[2] = 3-3+5 = 5
 *      5-dist[2] + magic[3] = 5-4+4 = 5
 *      5-distp[3] = 5-2 = 3
 * At this point, he is back to the first source. Because he can complete his journey starting at source magic[0], there is no reason to continue with the
 * analysis so its index, 0, is returned. To illustrate a point from the same example, if he starts at position 2, where magic[1] =2 and dist[1] = 3,
 * he will not be able to proceed to the next point because the distance is greater than his magic units. Note that the list is circular, so from
 * magic[3] in this example, the next source on the path is magic[0].
 *
 * Function Description
 * Complete the function optimalPoint in the editor below. The function must return an integer that denotes the minimum index of magic from which he can
 * start a successful journey. If no such starting point exists, return -1.
 *
 * optimalPoint has the following parameter(s):
 *      magic[magic[0], ... magic[n-1]]: an array of integers where magic[i] denotes the amount of magic in the ith source.
 *      dist[dist[0], ... dist[n-1]] : an array of integers where dist[i] denotes the distance to the next magical source.
 */
public class AladdinAndHisCarpet {
    public static void main(String[] args) {
        System.out.println(new AladdinAndHisCarpet().optimalPoint(new int[]{2,4,5,2}, new int[]{4,3,1,3}));
    }

    public static int optimalPoint(List<Integer> magic, List<Integer> dist) {
        // Write your code here
        int pos = -1, curr = 0, total = 0;
        for (int i = 0; i < magic.size(); i++) {
            int diff = magic.get(i) - dist.get(i);
            curr += diff;
            total += diff;
            if (curr < 0) {
                curr = 0;
                pos = i;
            }
        }
        if (total >= 0)
            return pos + 1;
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int total = 0;
        int start = 0;
        int sum = 0;

        // 当前的油量比要花费的油量大，就可以当做起点，否则不能当做起点。

        for (int i = 0; i< gas.length;i++)
        {
            total += gas[i] - cost[i];/**total 是总消耗减去剩下的总路程，不管怎么跑都死的*/
            sum += gas[i] - cost[i];/**用来判断一个点能不能当起点的，当前的油量比要花费的油量大，就可以当做起点，否则不能当做起点。*/
            if (sum < 0)
            {
                start = i+1;
                sum = 0;
            }
        }

        return total < 0? -1 : start;
    }

    /**** 这道题就是LC 的 gas station， 我自己的写法还是能过的， 但是太他妈慢了。。。。。 *****************************************/
    /******************************************************************************************************************/
    public int optimalPoint(int[] magic, int[] dist){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i< magic.length; i++){
            if(canFinish(magic,dist,i)){
                min = Math.min(min, i);
            }
        }
        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }

    public boolean canFinish(int[] magic, int[] dist, int i){
        boolean[] visited = new boolean[magic.length];
        int energy = magic[i];
        int cost = dist[i];
        int left = 0;
        while (true) {
            left = energy - cost;
            if(left < 0 && !visited[i]) return false;
            if(visited[i]) return true;
            if(i!=magic.length - 1){
                visited[i] = true;
                energy = left + magic[++i];
                cost = dist[i];
            }
            else if(i == magic.length - 1) {
                visited[i] = true;
                i = 0;
                energy = left + magic[i];
                cost = dist[i];
            }
        }
    }
}

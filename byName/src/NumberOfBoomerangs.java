import java.util.HashMap;

/**
 * Given n points in the plane that are all pairwise distinct,
 * a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k
 * (the order of the tuple matters).
 *
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000]
 * (inclusive).
 *
 * Example:
 *
 * Input:
 * [[0,0],[1,0],[2,0]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
        System.out.println(new NumberOfBoomerangs().numberOfBoomerangs(new int[][]{{0,0},{1,0},{2,0}}));
    }

    public int numberOfBoomerangs(int[][] nums){
        int res = 0;
        for(int i = 0; i< nums.length; i++){/**先确定一个原始点i**/
            HashMap<Integer, Integer> map = new HashMap<>();/**hashmap要在第一个forloop里面创建因为每一个点对应其他点都不同的，要用一个全新的map*/
            for(int j = 0; j< nums.length; j++){
                if(nums[i] == nums[j]) continue;/**遍历剩下所有的点并把距离当key以及此距离出现次数当value存进hashmap*/
                int a = nums[i][0]-nums[j][0];
                int b = nums[i][1]-nums[j][1];
                int c = a*a+b*b;
                map.put(c,map.getOrDefault(c,0)+1);
            }
            for(int value : map.values()){
                res += value*(value-1); /****这里就很关键了，靠记， 这是你自己实验出来的规矩啊，不要怀疑傻逼！！好吧实在忘了就画图吧杀卵*/
            }
        }
        return res;
    }
}

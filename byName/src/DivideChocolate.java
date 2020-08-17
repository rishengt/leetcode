/**
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the
 * array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces
 * using K cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your
 * friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 * Example 1:
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 *
 * Example 2:
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 *
 * Example 3:
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 * Constraints:
 *
 *     0 <= K < sweetness.length <= 10^4
 *     1 <= sweetness[i] <= 10^5
 */
public class DivideChocolate {
    public static void main(String[] args) {
        System.out.println(new DivideChocolate().divideChoco(new int[]{1,2,3,4,5,6,7,8,9},5));
        System.out.println(new DivideChocolate().divideChoco(new int[]{5,6,7,8,9,1,2,3,4},8));
        System.out.println(new DivideChocolate().divideChoco(new int[]{1,2,2,1,2,2,1,2,2},2));
    }
    public int divideChoco(int[] sweetness, int K){
        if(K == 0) return 0;
        int left = sweetness[0], right = 0;
        for(int s: sweetness){
            right += s;
            left = Math.min(left,s);
        }
        while(left<right){
            int mid = (left+right+1)>>>1; /**这里的加一怎么真么精髓??????*/
            if(isOK(sweetness,K,mid)){
                left = mid;
            }else{
                right = mid-1;
            }
        }
        return left;
    }
    boolean isOK(int[]sweetness, int K, int sum){
        int temp = 0; int count = 0;
        for(int i = 0; i<sweetness.length; i++){
            temp += sweetness[i];
            if(temp>= sum){
                count++;
                temp = 0;
            }
        }
        return count>=K+1;
    }
}

import java.util.HashSet;
import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 *
 *
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 *
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleinHistogram {
    public static void main(String[] args) {
        //System.out.println(new LargestRectangleinHistogram().largestRectangleArea(new int[]{2,1,5,6,2,3}));
        //System.out.println(new LargestRectangleinHistogram().largestRectangleAreaII(new int[]{2,1,5,6,2,3}));
        System.out.println(new LargestRectangleinHistogram().largestRectangleAreaIII(new int[]{2,1,5,6,2,3}));
    }
    /**最直观也是最慢的解法n^2，让你自己写一边不过分吧， 代码掌控能力严重不足，对数组保存下表的领悟十分差劲*/
    public int largestRectangleArea(int[] heights) {
        HashSet<Integer> set = new HashSet<>();
        for(int height : heights) set.add(height);
        int maxArea = 0;
        for(int height : set){
            int width = 0;
            int maxWidth = 1;/**这里也是很好的，再次也至少有1为宽嘛*/
            for(int j = 0; j< heights.length; j++){
                if(heights[j]>= height){
                    width++;
                }else{
                    maxWidth = Math.max(width,maxWidth);
                    width = 0;
                }
            }
            maxWidth = Math.max(width,maxWidth);/**这一步也是非常关键的，要是没有进去else呢，【1，1】细品。。。*/
            maxArea = Math.max(maxArea, height*maxWidth);
        }
        return maxArea;
    }

    /**妈的这种histogram的都被你们玩出🌹来了，但数组保存东西的意义真的好牛逼，好帅，好想领悟，让我想到了N皇后*/
    public int largestRectangleAreaII(int[] heights){
        int area = 0;
        if(heights.length == 0 || heights == null) return area;
        int[] leftLessMin = new int[heights.length];/**用来存左边第一个比当前柱子小的柱子的下表的数组*/
        leftLessMin[0] = -1;/**第一条柱子左边没有比它更小的柱子了**/
        for(int i = 1; i<heights.length; i++){
            int l = i-1;/**先从身边的开始一路往左找*/
            while(l>=0 && heights[l]>=heights[i]){/**这里妈的就是降维的精髓了，但凡我们左边的柱子有比我们高或等高的，我们左边最小的边界可以直接用它们的，细品，贪心？？*/
                l = leftLessMin[l];
            }
            leftLessMin[i] = l;/**要么经过whileloop找到了l，要么我们自己就是最小的*/
        }
        int[] rightLessMin = new int[heights.length];/**右边的同理。。。。。*/
        rightLessMin[heights.length-1] = heights.length;
        for(int i = heights.length-2; i>=0; i--){/**必须要这样遍历以为你第一个初始化的是最右边的，不这样的话数组根本付值不了，细品菜🐔*/
            int r = i+1;
            while(r<heights.length && heights[r]>=heights[i]){
                r = rightLessMin[r];
            }
            rightLessMin[i] = r;
        }

        for(int i = 0; i< heights.length; i++){
            /**这里的减一就顺理成章了因为不管是最左边的-1或是最右边的height。length我们都设置了多一位，细品*/
            area = Math.max(area, ((rightLessMin[i]-leftLessMin[i]-1)*heights[i]));
        }
        return area;
    }

    /**stack也被你们玩出🌹，牛逼。我对stack的掌握也是差得一匹，很烦，算法好难*/
    public int largestRectangleAreaIII(int[] heights){
        /**只能说用stack解这道题真的精妙绝伦，边弹出边排序边记录，牛逼！！！！！*/
        Stack<Integer> stack = new Stack<>();/**但是，stack里面存的应该是height的index而不是height本身，因为如果你存的是本体，当你弹出去一个最长的，整体宽度也减一了，细品*/
        int area = 0;
        int p = 0; /**工具p,代码掌控能力啊！！！*/
        while(p<heights.length){
            if(stack.isEmpty()){
                stack.push(p);
                p++;
            }else{
                if(heights[p]>heights[stack.peek()]){
                    stack.push(p);
                    p++;
                }else{
                    int height = heights[stack.pop()];/**你把height比你大的在你左边的都弹走了*/
                    int leftLessMin = stack.isEmpty()? -1:stack.peek();/**剩下的自然就是离你最近又恰好比你小的了*/
                    int rightLessMin = p;
                    area = Math.max(area,((rightLessMin-leftLessMin-1)*height));
                }
            }
        }
        while (!stack.isEmpty()){
            int height = heights[stack.pop()];/**因为存的是映射的index关系而不是height本身，所以。。。。。。细品吧少年*/
            int leftLessMin = stack.isEmpty()? -1:stack.peek();
            int rightLessMin = heights.length;
            area = Math.max(area,((rightLessMin-leftLessMin-1)*height));
        }
        return area;
    }
}

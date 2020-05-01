/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */

public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        System.out.println(solution.trapII(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    public int trapI(int[] height){
        if(height.length<=1) return 0;
        int ans = 0;
        for(int i = 0; i< height.length; i++){
            int left = 0;
            int right = 0;
            for(int j=i; j<height.length; j++){
                right = Math.max(height[j],right);
            }
            for(int j = i; j>=0; j--){
                left = Math.max(height[j],left);
            }

            ans+=Math.min(left,right)-height[i];
        }
        return ans;
    }

    public int trapII(int[] height){
        if(height.length<=1) return 0;
        int ans = 0;
        int leftindex = 0;
        int rightindex = height.length-1;
        int leftstartheight = height[leftindex];
        int rightstartheight = height[rightindex];
        while(leftindex<=rightindex){
            leftstartheight = Math.max(leftstartheight,height[leftindex]);
            rightstartheight = Math.max(rightstartheight, height[rightindex]);
            if(leftstartheight<rightstartheight){
                ans+= leftstartheight - height[leftindex];
                leftindex++;
            }else{
                ans+=rightstartheight-height[rightindex];
                rightindex--;
            }
        }
        return ans;
    }
}

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 */
public class FindUniqueInArray {
    public int findUnique(int[] array){
        if(array == null || array.length == 0) return -1;
        int ans = 0; /**0 异或 任何数 等于 任何数*/
        for(int i = 0; i< array.length; i++){
            ans ^= array[i]; /** 任何数 异或 它自己 等于 0*/
        }
        return ans;
    }
}

import java.util.Arrays;
import java.util.List;

/**
 * Given two sorted arrays, merge them to form a single, sorted array with all items in non-decreasing order.
 */
public class Merge2Arrays {
    public static void main(String[] args) {
        Merge2Arrays m = new Merge2Arrays();
        int[] list = m.mergeArrays(new int[]{}, new int[]{});
        for (int i : list){
            System.out.println(i);
        }

    }
    int[] mergeArrays(int[] a, int[] b){
        int[] ans = new int[a.length+b.length];
        int i = 0, j = 0, k = 0;
        for(;k<ans.length;k++){
            if(i == a.length && j == b.length) return ans;
            else if(i == a.length && j!=b.length){
                ans[k] = b[j++];
            }
            else if(j == b.length && i!=a.length){
                ans[k] = a[i++];
            }
            else {
                if (a[i] < b[j]) {
                    ans[k] = a[i++];
                } else {
                    ans[k] = b[j++];
                }
            }
        }
        return ans;
    }
}

/**传说中的单轴快排
 *
 * 恶心人的菜🐔双轴快排都不要求你掌握了单轴你他妈总该给老子记住吧，草！
 * **/

public class SinglePivotSort {

    public static void main(String[] args) {
        int[] array = new int[]{3,3,6,7,9,10,1,2,2,1};
        new SinglePivotSort().sort(array,0,array.length-1);
        for(int i = 0; i< array.length; i++){
            System.out.println(array[i]);
        }
    }

    public void sort(int[] nums, int start, int end){
        if (start < end) {
            int pivot = nums[start];

            int i = start + 1, j = end;
            while (i <= j) {
                while (i <= j && nums[i] < pivot)
                    i++;
                while (i <= j && nums[j] >= pivot)
                    j--;
                if (i <= j) {
                    swap(nums, i, j);
                }
            }
            swap(nums, start, j);// 将中心点交换到中间。
            sort(nums, start, j - 1);// 中心点左半部分递归
            sort(nums, j + 1, end);// 中心点右半部分递归
        }
    }

    private void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

}

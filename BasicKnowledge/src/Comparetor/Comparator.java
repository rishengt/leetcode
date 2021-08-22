package Comparetor;

import java.util.Arrays;

public class Comparator {
    public static void main(String[] args) {
        int[][] mentors = new int[2][];
        Arrays.sort(mentors, java.util.Comparator.<int[], Integer>comparing(a->a[0]).thenComparing(a->a[1]).thenComparing(a->a[2]));
        Arrays.sort(mentors, (o1, o2) -> {
            int i = 0;
            while(o1[i] == o2[i]&&i<o1.length-1){
                i++;
            }
            return Integer.compare(o1[i],o2[i]);
        });
        Arrays.sort(mentors,new java.util.Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int i = 0;
                while(o1[i] == o2[i]&&i<o1.length-1){
                    i++;
                }
                return Integer.compare(o1[i],o2[i]);
            }
        });
    }
}

// Time Complexity: O(NW)
import java.util.ArrayList;

public class repetitionKnapsack {
     public static int[] dprK(int N, int W, int[] value, int[] weight) {
         ArrayList<Integer> temp = new ArrayList<Integer>();
         int[] opt = new int[W + 1];
         int max = 0;
         opt[0] = 0;
         for (int j = 1; j < W + 1; j++) {
             for (int i = 1; i < N + 1; i++) {
                 if(weight[i] <= j) {
                     opt[j] = Math.max(opt[j-1], opt[j-weight[i]] + value[i]);
                     temp.add(opt[j]);
                 }
                 for(int k = 0; k < temp.size(); k++) {
                     if (temp.get(k) > max) {
                         max = temp.get(k);
                     }
                 }
             }
             opt[j] = max;
             temp.clear();
             max = 0;
         }
         return opt;
     }
     
     public static void main(String[] args) {
         int N_large = 6;
         int W_large = 2000;
         int[] value_large = {0, 874, 620, 345, 369, 360, 470};
         int[] weight_large = {0, 580, 1616, 1906, 1942, 50, 294};
    
         int[] res_large = dprK(N_large, W_large, value_large, weight_large);
         for (int i = 0; i < res_large.length; i++) {
             System.out.println(res_large[i]);
         }
         System.out.println("The maximum value is " + res_large[W_large]);
         
         int N_midterm = 4;
         int W_midterm = 10;
         int[] value_midterm = {0, 30, 14, 16, 9};
         int[] weight_midterm = {0, 6, 3, 4, 2};
         
         int[] res_midterm = dprK(N_midterm, W_midterm, value_midterm, weight_midterm);
         for (int i = 0; i < res_midterm.length; i++) {
             System.out.println(res_midterm[i]);
         }
         System.out.println("The maximum value is " + res_midterm[W_midterm]);
     }
}


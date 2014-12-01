public class binaryKnapsacks {
    public static int[][] dpBK(int N, int W, int[] value, int[] weight) {
        //二维dp初始化, f[i][j]代表前i件物品放到一个重量为j的背包中可以得到的最大value
        int[][] f = new int[N+1][W+1];
        f[0][0] = 0;
    
        for(int i = 1; i < N + 1; i++) {
            f[i][0] = 0;
        }
    
        for(int j = 1; j < W + 1; j++) {
            f[0][j] = 0;
        }
        
        //递归式, 当只能放一个物品，逐渐从1开始增加背包限重，二维table记录数据.
        //1) 如果第i件物品不放入背包中，那么问题就转换为：将前i - 1件物品放到重量为W的背包中，带来的收益f[i - 1][j]
        //2) 如果第i件物品能放入背包中，那么问题就转换为：将前i - 1件物品放到重量为W - weight[i]的背包中，带来的收益f[i - 1][j - weight[i]] + cost[i]
        for (int i = 1; i < N + 1; i++) {
            for(int j = 1; j < W + 1; j++) {
                if(weight[i] > j) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - weight[i]] + value[i]);
                }
                    
            }
        }
        return f;
    }
    public static void main(String[] args) {
        //一共5个物品
        int N = 5;
        //重量上限是11
        int W = 11;
        //输入value - weight表格
        //int[] value = new int[N + 1];
        //int[] weight = new int[N + 1];
        int[] value = {0, 1, 6, 18, 22, 28};
        int[] weight = {0, 1, 2, 5, 6, 7};
        int[][] res = dpBK(N, W, value, weight);
        for (int i = 0; i < N + 1; i++){
            System.out.println("");
            for(int j = 0; j < W + 1; j++) {
                if (res[i][j]> 10)
                    System.out.print("" + res[i][j] + "  ");
                else
                    System.out.print(" " + res[i][j] + "  ");
            }
        }
        System.out.println("");

        System.out.println(" The maximum value is " + res[N][W]);
    }
}
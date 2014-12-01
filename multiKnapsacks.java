public class multiKnapsacks {
    public static int[][][] dpBK(int N, int W, int Z,int[] value, int[] weight, int[] volume) {
        int[][][] f = new int[N+1][W+1][Z+1];
        f[0][0][0] = 0;
        
        for(int i = 1; i < N + 1; i++) {
            f[i][0][0] = 0;
        }
        
        for(int j = 1; j < W + 1; j++) {
            f[0][j][0] = 0;
        }
        
        for(int k = 1; k < Z + 1; k++) {
            f[0][0][k] = 0;
        }

        for (int i = 1; i < N + 1; i++) {
            for(int j = 1; j < W + 1; j++) {
                for (int k = 1; k < Z + 1; k++) {
                    if(weight[i] > j || volume[i] > k) {
                        f[i][j][k] = f[i - 1][j][k];
                    } else {
                        f[i][j][k] = Math.max(f[i - 1][j][k], f[i - 1][j - weight[i]][k - volume[i]] + value[i]);
                    }
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
        //体积上限是15
        int Z = 15;
        int[] value = {0, 1, 6, 18, 22, 28};
        int[] weight = {0, 1, 2, 5, 6, 7};
        int[] volume = {0, 1, 10, 3, 2, 2};
        int[][][] res = dpBK(N, W, Z, value, weight, volume);
        for (int i = 0; i < N + 1; i++){
            System.out.println("");
            System.out.println("Layer " + i);
            for(int j = 0; j < W + 1; j++) {
                System.out.println("");
                for(int k = 0; k < Z + 1; k++) {
                    if (res[i][j][k]> 10)
                        System.out.print("" + res[i][j][k] + "  ");
                    else
                        System.out.print(" " + res[i][j][k] + "  ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("The maximum value is " + res[N][W][Z]);
    }
}
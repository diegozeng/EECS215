import java.util.ArrayList;

class adjacentMatrix {
    int [][] Graph;
    char[] Str;
}

class hopPair {
    int key;
    int value;
}

public class dvp {
    public static int MAX = 65536; //use 65536 to represent positive infinity
    public static ArrayList<hopPair> shortestPath(int[][] Graph) {
        ArrayList<ArrayList<hopPair>> res = findHop(Graph);
        ArrayList<Integer> tempKey = new ArrayList<Integer>();
        hopPair temp1 = new hopPair();
        int nodeNum = Graph.length;
        int[][] dptable = new int[nodeNum][nodeNum];
        int[] dis = new int[dptable.length];
        ArrayList<hopPair> result = new ArrayList<hopPair>();
        dptable[0][0] = 0;
        for(int i = 1; i < nodeNum; i++) {
            dptable[0][i] = 0;
        }
        
        for(int j = 1; j < nodeNum; j++) {
            dptable[j][0] = MAX;
        }
        
        for(int i = 1; i < nodeNum; i++) {
            for(int j = 1;  j < nodeNum; j++) {
                dptable[j][i] = Math.min(dptable[j][i-1], getMin(dptable, res, i, j).value);
                if(i == nodeNum-1) {
                    tempKey.add(getMin(dptable, res, 6, j).key);
                }
            }
            
        }
        
        for(int k = 1; k < dptable.length; k++) {
            dis[k] = dptable[k][dptable.length-1];
            temp1.value = dis[k];
            temp1.key = tempKey.get(k-1);
            result.add(temp1);
            temp1 = new hopPair();
        }
        return result;
    }
    
    // Obtain hop of each node. In this case hop = {{}, {2->3,3->3,4->4}, {5->2},{2->10,5->1},{6->5},{4->1,6->1,0->2},{0->5}}
    private static ArrayList<ArrayList<hopPair>> findHop(int[][] G) {
        ArrayList<ArrayList<hopPair>> res = new ArrayList<ArrayList<hopPair>>();
        ArrayList<hopPair> nodeHop = new ArrayList<hopPair>();
        hopPair temp = new hopPair();
        for (int i = 0; i < G.length; i++) {
            nodeHop = new ArrayList<hopPair>();
            for (int j = 0; j < G.length;j++) {
                if((G[i][j] != MAX) && (G[i][j] != 0)) {
                    temp = new hopPair();
                    temp.key = j;
                    temp.value = G[i][j];
                    nodeHop.add(temp);
                }
            }
            res.add(nodeHop);
        }
        return res;
    }
    
    private static hopPair getMin(int[][] dpt, ArrayList<ArrayList<hopPair>> result, int i, int j) {
        int minhop = Integer.MAX_VALUE;
        hopPair temp = new hopPair();
        for(int m = 0; m < result.get(j).size(); m++) {
            temp.value = result.get(j).get(m).value + dpt[result.get(j).get(m).key][i-1];
            if(temp.value < minhop) {
                minhop = temp.value;
                temp.key = result.get(j).get(m).key;
            }
        }
        temp.value = minhop;
        return temp;
    }
    
    public static void print(int[][] G) {
        for (int i = 0; i < G.length;i++) {
            for (int j = 0; j < G.length;j++) {
                System.out.print(G[i][j]);
            }
            System.out.println("");
        }
    }
    
    public static void main(String[] args) {
        int cnt;
        //import graph by adjacent matrix.
        int[][][] Graph = {{{0,MAX,MAX,MAX,MAX,MAX,MAX},{MAX,0,3,3,4,MAX,MAX},{MAX,MAX,0,MAX,MAX,2,MAX},{MAX,MAX,10,0,MAX,1,MAX},{MAX,MAX,MAX,MAX,0,MAX,5},{2,MAX,MAX,MAX,1,0,1},{5,MAX,MAX,MAX,MAX,MAX,0}},{{0,MAX,3,3,4,MAX,MAX},{MAX,0,MAX,MAX,MAX,MAX,MAX},{MAX,MAX,0,MAX,MAX,2,MAX},{MAX,MAX,10,0,MAX,1,MAX},{MAX,MAX,MAX,MAX,0,MAX,5},{MAX,2,MAX,MAX,1,0,1},{MAX,5,MAX,MAX,MAX,MAX,0}},{{0,MAX,MAX,MAX,MAX,2,MAX},{MAX,0,MAX,MAX,MAX,MAX,MAX},{3,MAX,0,3,4,MAX,MAX},{10,MAX,MAX,0,MAX,1,MAX},{MAX,MAX,MAX,MAX,0,MAX,5},{MAX,2,MAX,MAX,1,0,1},{MAX,5,MAX,MAX,MAX,MAX,0}},{{0,MAX,MAX,10,MAX,1,MAX},{MAX,0,MAX,MAX,MAX,MAX,MAX},{3,MAX,0,3,4,MAX,MAX},{MAX,MAX,MAX,0,MAX,2,MAX},{MAX,MAX,MAX,MAX,0,MAX,5},{MAX,2,MAX,MAX,1,0,1},{MAX,5,MAX,MAX,MAX,MAX,0}},{{0,MAX,MAX,MAX,MAX,MAX,5},{MAX,0,MAX,MAX,MAX,MAX,MAX},{4,MAX,0,3,3,MAX,MAX},{MAX,MAX,MAX,0,MAX,2,MAX},{MAX,MAX,MAX,10,0,1,MAX},{1,2,MAX,MAX,MAX,0,1},{MAX,5,MAX,MAX,MAX,MAX,0}},{{0,2,MAX,MAX,MAX,1,1},{MAX,0,MAX,MAX,MAX,MAX,MAX},{MAX,MAX,0,3,3,4,MAX},{2,MAX,MAX,0,MAX,MAX,MAX},{1,MAX,MAX,10,0,MAX,MAX},{MAX,MAX,MAX,MAX,MAX,0,5},{MAX,5,MAX,MAX,MAX,MAX,0}},{{0,5,MAX,MAX,MAX,MAX,MAX},{MAX,0,MAX,MAX,MAX,MAX,MAX},{MAX,MAX,0,3,3,4,MAX},{MAX,MAX,MAX,0,MAX,MAX,2},{MAX,MAX,MAX,10,0,MAX,1},{5,MAX,MAX,MAX,MAX,0,MAX},{1,2,MAX,MAX,MAX,1,0}}};
        //Node index to adjacent matrix.
        char[][] str = {{'t', 's', 'a', 'b', 'c', 'd', 'e'},{'s', 't', 'a', 'b', 'c', 'd', 'e'},{'a', 't', 's', 'b', 'c', 'd', 'e'},{'b', 't', 's', 'a', 'c', 'd', 'e'}, {'c', 't', 's', 'a', 'b', 'd', 'e'},{'d', 't', 's', 'a', 'b', 'c', 'e'},{'e', 't', 's', 'a', 'b', 'c', 'd'}};
        adjacentMatrix test = new adjacentMatrix();
        for(int k = 0; k < str.length; k++) {
            cnt = 0;
            System.out.println("----when starting node is " + str[0][k] + ", The results are shown in the form [Destination][Next Hop][Distance]----");
            for(int j = 0; j < Graph.length;j++) {
                test.Graph = Graph[j];
                ArrayList<hopPair> result = shortestPath(test.Graph);
                for(int i = 0; i<result.size();i++) {
                    if(str[j][i+1] == str[0][k]) {
                        if(result.get(i).value != MAX) {
                            System.out.println("["+ str[j][0] + "] " + "[" + str[j][result.get(i).key] + "] " + "[" + result.get(i).value + "]");
                            cnt++;
                        }
                    }
                }
            }
            if (cnt == 0) {
                System.out.println("Node " + str[0][k] + " cannot access any other nodes in the graph!");
            }
        }
    }
}

import java.util.*;

public class ConsecutiveKElement {
    public static void main(String[] args) {
        System.out.println(new ConsecutiveKElement().itsThere(5,new int[][]{{3,5,5,9,5},{4,3,2,1,8},{9,4,3,1,9},{8,4,7,6,4},{1,2,5,9,1}},4));
        System.out.println(new ConsecutiveKElement().itsThere(4,new int[][]{{1,3,2,3},{4,5,3,6},{7,3,8,3},{3,9,10,11}},4));
    }
    List<Integer> list;
    public int itsThere(int N,int[][] grid, int k){
        if(N*N<k) return -1;
        list = new ArrayList<>();
        boolean visited[][] = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                dfs(N,grid,grid[i][j],k,i,j,visited);
            }
        }
        if(list.size() == 0) return -1;
        Collections.sort(list);
        System.out.println(list);
        return list.get(0);
    }
    public void dfs(int N, int[][] grid, int value, int k, int x, int y, boolean[][] visited){
        if(x>=N || x<0 || y>= N || y<0 || grid[x][y] != value){
            return;
        }
        if(visited[x][y]) return;
        visited[x][y] = true;
        if(k == 1){
            list.add(value);
            return;
        }
        if(grid[x][y] == value){
            dfs(N,grid,value,k-1,x+1,y,visited);
            dfs(N,grid,value,k-1,x-1,y,visited);
            dfs(N,grid,value,k-1,x,y+1,visited);
            dfs(N,grid,value,k-1,x,y-1,visited);
            dfs(N,grid,value,k-1,x+1,y+1,visited);
            dfs(N,grid,value,k-1,x-1,y-1,visited);
            dfs(N,grid,value,k-1,x+1,y-1,visited);
            dfs(N,grid,value,k-1,x-1,y+1,visited);
        }
        visited[x][y] = false;
    }
}

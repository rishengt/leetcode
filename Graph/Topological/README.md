# 拓扑排序有两种模板
### 1.BFS
这种应该是最常用的，是必须要掌握的，假设你有n个node并且知道vertex
```java
public boolean noCycle(){
    ArrayList<Integer> Graph[] = new ArrayList[n];
    int degree[] = new int[n];
    int count = 0;
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 0; i<n;i++){
        Graph[i] = new ArrayList<>();
    }
    for(int i : degree){
        if(i == 0){
            queue.offer(i);
            count++;
        }
    }
    while(!queue.isEmpty()){
        int temp = queue.poll();
        for(int child: Graph[temp]){
            degree[child]--;
            if(degree[child] == 0){
                queue.offer(child);
                count++;
            }
        }
    }
    
    return count == n;
}
```

### 2.DFS
这种我掌握得不是很好，这个要维护一个visited数组，我DFS一直就挺弱的
```java
public boolean hasCycke(int[] nodes, ArrayList<Integer> Graph[]){
    boolean visited[] = new boolean[nodes.length];
    for(int node : nodes){
        if(dfs(Graph,visited, node)==false) return false;
    }
    return true; 
}

public boolean dfs(ArrayList<Integer>[] Graph, boolean[] visited, int cur){
    /**要是二次访问代表有环，证明不能遍历所有node*/
    if(visited[cur]) return false;
    visited[cur] = true;
    for(int child : Graph[cur]){
        if(dfs(Graph,visited,child) == false) return false;
    }
    visited[cur] = false;
    return true;
}
```
    
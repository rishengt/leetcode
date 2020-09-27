Union Find两大核心方法：find 跟 union  
Union Find一开始都是有一条parent的，list也好图也好array也罢，里面的element自已连自己并且互不相连  
```java
    int find(int x, int[] parent){
    if(x != parent[x]) parent[x] = find(parent[x],parent);
    return parent[x];
}
    void union(int a, int b){
    int rootA = find(a);
    int rootB = find(b);
    if(rootA != rootB){
        parent[rootA] = rootB;
    }
}
```

[模板](/byName/src/UnionFind.java)
Dijkstra算法是最常用的找出最短路徑的算法  

通常使用一个priorityqueue，queue里面存的是int[]数组，数组表示为跟你连接的点和点之间的weight，  
根据题目需求来排queue顶上的是什么weight。。。  
比如你有四个点[[1,2,3],[1,3,4],[2,4,1],[3,4,1]], 分别表示1跟2相连，weight为3.....问你由1到4的最短路径  
priorityqueue里面存的就是[x1,x2],x1为当前点能去的点，x2为weight，而且是按weight从小到大排

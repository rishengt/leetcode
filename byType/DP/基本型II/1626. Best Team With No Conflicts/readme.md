就是一道非常經典的最長上升子序列的問題，不過要先排序，然後實現的過程可能有很多細節需要注意，經常敗在不知道要用變量去存最大值這點上。。。  

```java
int ans = 0;//細節
for(int i = 0; i<array.length; i++){
    dp[i] = array[i][1];
    for(int j = 0; j<i; j++){//j<i,細節
        if(array[i][1]>=array[j][1]){
            dp[i] = Math.max(dp[i], dp[j]+array[i][1]);
        }       
    }
    ans = Math.max(dp[i],ans); //細節
}
```
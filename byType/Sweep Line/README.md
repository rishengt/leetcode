差分数组：给区间[l, r]中的每个数加上c：B[l] += c, B[r + 1] -= c  
然后答案数组就是prefix sum的ans[i] += ans[i-1];
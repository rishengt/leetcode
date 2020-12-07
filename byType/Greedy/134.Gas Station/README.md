每道贪心都十分精妙。。

这道题要想到total gas 要大于等于 total cost，才能环绕一圈。

假设total gas 真的大于等于 total cost, 那么起点怎么找呢？ 因为可以环绕一周，所以起点必然存在，那么可以贪心地进行尝试。

要是由A点走不到B点(目前为止tank里的油比不上cost，注意是目前)，那么我们可以从B+1点出发继续往下找，这样只需遍历数组一次就能得到答案了。

[Leecode Link](https://leetcode.com/problems/gas-station/)
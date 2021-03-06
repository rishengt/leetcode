我们先想一个简单的问题，如何通过rand10()实现rand7()？因为rand10()能够等概率地得到[0,1,2,3,4,5,6,7,8,9]，假如永远不会出现7,8,9的话那岂不就是rand7()了？因此一个简单的想法就是，如果rand10()得到了7,8,9就retry，直至rand10()出现的数在[0,6]范围内，然后把答案输出。

OK，接下来我们思考如何通过rand7()实现rand10()？有人会想，通过两次rand7()再相加，得到均匀分布的[0,1,2,3,4,5,6,7,8,9,10,11,12,13]，再对出现大于10的结果时进行retry就可以了。这个思路很正确，但是可惜的是，对于两个独立同均匀分布的随机变量X,Y，它们的加和不是均匀分布，而是二项分布。我们无法通过两次rand7()得到[0,13]均匀分布的随机数。

但是，我们可以用两次rand7()得到[0,48]均匀分布的随机数！我们考虑一个包含两位digit的七进制数ab，并且令a和b都是由rand7()得到的。那么ab代表了七进制的[00,66]之间连续、等概率的分布。转化为十进制的话，就是[0,48]之间连续、等概率的分布。利用之前的retry大法，对于X~U[0,48]的随机变量，当大于等于40时就进行retry，可以得到Y~U[0,39]。进一步，Y%10~U[0,9]，就得到了我们需要的答案!

可以计算，得到一个rand10()所需要调用的rand7()的次数的期望是2.45。

[LeetCode Link](https://leetcode.com/problems/implement-rand10-using-rand7/)  
[Guan神Link](https://leetcode.com/problems/implement-rand10-using-rand7/)
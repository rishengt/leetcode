最基本的用法  
```java
/***** 下面的都是在一个 range 里面 随意创造数的 方式 ********************/
    public int getRandomNumber(int min, int max) {
        /**你变成int的话最大最小都要各扩大一位，不然那个数永远出不来的*/
        max ++;
        min --;/**这个是当你是负数的时候才要用*/
        return (int) ((Math.random() * (max-min) + min));
    }
```
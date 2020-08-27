package TreeMap;

import java.util.*;

public class ceilling {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>((a,b) -> b.charAt(0)-a.charAt(0));
        map.ceilingKey(""); /**ceiling 是返回 最小的大于等于 参数 key 的key， higher 只能大于*/
        map.higherEntry("");
        map.remove("");/**大概所有java集合类都会有remove方法吧*/
    }
}

KMP总的来说就是字符匹配算法，当你在遍历一条String然后跟另外一条String去匹配的时候，很多重复的字符段我们不需要重复比对，  
那么我们就用一个next数组去记录最多遍历到哪里然后退回到那个位置。  

```java
public int KMP(String s, String p){
        int next[] = new int[p.length()+1];
//        next[0] = -1;
        for(int i = 1, j = 0; i<p.length(); i++){
            while(j>0 && p.charAt(i)!=p.charAt(j)){
                j = next[j];
            }
            if(p.charAt(i) == p.charAt(j)){
                j++;
            }
            next[i+1] = j;
        }

        for(int i = 0, j = 0; i<s.length(); i++){
            while(j>0 && s.charAt(i) != p.charAt(j)){
                j = next[j];
            }
            if(s.charAt(i) == p.charAt(j)){
                j++;
            }
            if(j == p.length()){
                return i-j+1;
            }
        }
        return -1;
    }
```
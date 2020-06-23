import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an array of strings names of size n. You will create n folders in your file system such that, at the ith minute,
 * you will create a folder with the name names[i].
 *
 * Since two files cannot have the same name, if you enter a folder name which is previously used,
 * the system will have a suffix addition to its name in the form of (k),
 * where, k is the smallest positive integer such that the obtained name remains unique.
 *
 * Return an array of strings of length n where ans[i] is the actual name the system will assign to the ith folder when you create it.
 *
 *
 *
 * Example 1:
 *
 * Input: names = ["pes","fifa","gta","pes(2019)"]
 * Output: ["pes","fifa","gta","pes(2019)"]
 * Explanation: Let's see how the file system creates folder names:
 * "pes" --> not assigned before, remains "pes"
 * "fifa" --> not assigned before, remains "fifa"
 * "gta" --> not assigned before, remains "gta"
 * "pes(2019)" --> not assigned before, remains "pes(2019)"
 * Example 2:
 *
 * Input: names = ["gta","gta(1)","gta","avalon"]
 * Output: ["gta","gta(1)","gta(2)","avalon"]
 * Explanation: Let's see how the file system creates folder names:
 * "gta" --> not assigned before, remains "gta"
 * "gta(1)" --> not assigned before, remains "gta(1)"
 * "gta" --> the name is reserved, system adds (k), since "gta(1)" is also reserved, systems put k = 2. it becomes "gta(2)"
 * "avalon" --> not assigned before, remains "avalon"
 * Example 3:
 *
 * Input: names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
 * Output: ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
 * Explanation: When the last folder is created, the smallest positive valid k is 4, and it becomes "onepiece(4)".
 * Example 4:
 *
 * Input: names = ["wano","wano","wano","wano"]
 * Output: ["wano","wano(1)","wano(2)","wano(3)"]
 * Explanation: Just increase the value of k each time you create folder "wano".
 * Example 5:
 *
 * Input: names = ["kaido","kaido(1)","kaido","kaido(1)"]
 * Output: ["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
 * Explanation: Please note that system adds the suffix (k) to current name even it contained the same suffix before.
 *
 *
 * Constraints:
 *
 * 1 <= names.length <= 5 * 10^4
 * 1 <= names[i].length <= 20
 * names[i] consists of lower case English letters, digits and/or round brackets.
 */
public class MakingFileNamesUnique {
    public static void main(String[] args) {
        String[] ans = new MakingFileNamesUnique().getFolderNames(new String[]{"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"});
        String[] ansII = new MakingFileNamesUnique().getFolderNames(new String[]{"onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"});
        for(String s : ans){
            System.out.println(s);
        }
    }
        public String[] getFolderNames(String[] names) {
            int n = names.length;
            HashMap<String, Integer> map = new HashMap<>();
            HashSet<String> all = new HashSet<>();
            String[] ret = new String[n];
            for(int i = 0;i < n;i++){
                if(all.add(names[i])){/**这里妈的是精髓，HashSet的add返回的是boolean，就跟HashMap的put返回东西一样，妈的你好菜啊傻逼*/
                    ret[i] = names[i];/**真正去重的是set，map只是记录重复次数的工具map*/
                }else{
                    int g = map.getOrDefault(names[i], 0);
                    while(true){
                        g++;
                        String nm = names[i] + "(" + g + ")";
                        if(all.add(nm)){
                            ret[i] = nm;
                            map.put(names[i], g);/**记录重复次数的工具map*/
                            break;
                        }
                    }
                }
            }
            return ret;
        }

/*************************************************我自己的方法LC 倒数第二会超时，上面的方法优胜在哪？****************************/
    public String[] getFolderNamesII(String[] names) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] ans = new String[names.length];
        for(int i = 0; i< names.length; i++){
            if(!map.containsKey(names[i])){
                map.put(names[i],0);
                ans[i] = names[i];
            }else{
                int j = map.get(names[i]);
                while(true){
                    j++;
                    String cnm = names[i] + "(" + j + ")";
                    if(!map.containsKey(cnm)){
                        map.put(cnm,0);
                        ans[i] = cnm;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}

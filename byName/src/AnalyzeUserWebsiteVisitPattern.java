/**
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.
 * (The websites in a 3-sequence are not necessarily distinct.)
 * Find the 3-sequence visited by the largest number of users.
 * If there is more than one solution, return the lexicographically smallest such 3-sequence.
 *
 * Example 1:
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10],
 * website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 *
 * Note:
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 * It is guaranteed that there is at least one user who visited at least 3 websites.
 * No user visits two websites at the same time
 */
import java.util.*;
public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // utwRecords 根据用户名username组合三个数组 <username, <timestamp, website>>
        // 注意内部使用TreeMap 是为了排序 时间戳timestamp的按顺序排列
        HashMap<String, TreeMap<Integer, String>> utwRecords = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            if (!utwRecords.containsKey(username[i])) {
                utwRecords.put(username[i], new TreeMap<Integer, String>());
            }
            utwRecords.get(username[i]).put(timestamp[i], website[i]);
        }

        // uwRecords 提取utwRecords中除时间戳timestamp的数据
        // 用户名username以及对应访问的网页website(按时间戳排序)
        HashMap<String, ArrayList<String>> uwRecords = new HashMap<>();
        for (String name : utwRecords.keySet()) {
            uwRecords.put(name, new ArrayList<String>());
            for (Integer time : utwRecords.get(name).keySet()) {
                uwRecords.get(name).add(utwRecords.get(name).get(time));
            }
        }

        // cntWP 存储所枚举的每一条网页访问路径 并计录其出现的次数
        // <网页访问路径(3个网页名称合成1条字符串), 出现次数>
        HashMap<String, Integer> cntWP = new HashMap<>();
        // maxCnt 网页访问路径出现最多次数为
        int maxCnt = 0;
        // res 最多次数的网页访问路径(3个网页名称合成1条字符串)
        String res = new String();
        for (String name : uwRecords.keySet()) {
            // len 当前用户所访问的网页数
            int len = uwRecords.get(name).size();
            // single 存储当前用户所访问网页的访问路径 并去重
            HashSet<String> single = new HashSet<>();
            for (int i = 0; i < len-2; i++) {
                for (int j = i+1; j < len-1; j++) {
                    for (int k = j+1; k < len; k++) {
                        // 网页访问路径格式: A-->B-->C
                        String cur = (new StringBuilder()).append(uwRecords.get(name).get(i))
                                .append("->")
                                .append(uwRecords.get(name).get(j))
                                .append("->")
                                .append(uwRecords.get(name).get(k)).toString();
                        single.add(cur);
                    }
                }
            }

            // 遍历当前用户所访问网络的路径 存储并给访问路径计数
            for (String str : single) {
                if (!cntWP.containsKey(str)) {
                    cntWP.put(str, 0);
                }
                cntWP.put(str, cntWP.get(str)+1);

                int curCnt = cntWP.get(str);
                // 当该访问路径出现次数curCnt大于最大访问次数maxCnt
                //      那么结果路径res就是该路径
                // 或者
                // 当该访问路径出现次数curCnt等于最大访问次数maxCnt 且字典序小于原出现次数最多的路径res
                //      那么结果路径res就是该路径
                if (curCnt > maxCnt | (curCnt == maxCnt && str.compareTo(res) < 0)) {
                    maxCnt = curCnt;
                    res = str;
                }
            }
        }

        // ans 存储所求结果访问路径的3个网页
        // 其实也可以使用 Arrays.asList(res.split("->"))
        ArrayList<String> ans = new ArrayList<>();
        for (String str : res.split("->")) {
            ans.add(str);
        }

        return ans;
    }
}

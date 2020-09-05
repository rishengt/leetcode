/**
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    public static void main(String[] args) {
        System.out.println(new TaskScheduler().leastInterval(new String[]{"A","A","A","B","B","B"},2));
        System.out.println(new TaskScheduler().leastInterval(new String[]{"A","A","A","B","B","B"},0));
        System.out.println(new TaskScheduler().leastInterval(new String[]{"A","A","A","A","A","A","B","C","D","E","F","G"},2));
    }
    /**
     * 如果A出现的次数是最多的，那么我们应该尽量密集地去安排，A x x x A x x x A
     * 若果有多个跟A一样出现次数最多的，那么我们可以把它与A并排且把它们合并，然后吞掉一个空位 AB XX AB XX AB
     * 如果空位比剩余的多，那么代表我们可以全部塞完并且要用idle来填充剩下的。
     *
     * empty = count(mostFrequentElement)-1 * (n-(count(AllmostFrequentElement)-1));
     * if(empty > count(NotMostFrequentElement)
     *
     * ans = idle + count(tasks);
     */
    public int leastInterval(String[] task, int n) {
        char[] tasks = new char[task.length];
        for(int i = 0; i<tasks.length; i++) tasks[i] = task[i].charAt(0);
        int count[] = new int[26];
        int max = 0;
        for(char a: tasks){
            count[a-'A']++;
            max = Math.max(count[a-'A'],max);
        }
        int numOfMostFrequent = 0;
        int totaltask = 0;
        for(int i: count){
            if(i == max) numOfMostFrequent++;
            totaltask +=i;
        }
        int idle = (max-1) * (n-(numOfMostFrequent-1));
        int ans = 0;
        if(idle> totaltask-(max*numOfMostFrequent)) return idle - (totaltask-(max*numOfMostFrequent)) + totaltask;
        else return totaltask;
    }
}
